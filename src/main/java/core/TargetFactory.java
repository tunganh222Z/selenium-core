package core;

import config.ConfigReader;
import core.enums.BrowserTypes;
import core.enums.PlatFormType;
import core.enums.TargetType;
import core.mobile.driver.MobileOptionsManager;
import core.ui.driver.BrowserOptionsManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.EnumUtils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Chuyên xử lý logic nếu kết nối tới Selenium Grid, Docker, hoặc Cloud testing (BrowserStack, SauceLabs).
 * Mục tiêu: Xây dựng class TargetFactory làm nhiệm vụ sinh ra instance của WebDriver dựa trên môi trường thực thi (Local hoặc Remote). Nó sẽ đóng vai trò trung gian, lấy cấu hình từ ConfigReader và lấy options từ BrowserOptionsManager để lắp ráp thành chiếc xe hoàn chỉnh.
 *
 * Yêu cầu nghiệp vụ (Business Requirements):
 *
 * Phân nhánh rõ ràng (Execution Targets):
 *
 * Chạy Local: Khởi chạy trình duyệt trực tiếp trên máy vật lý. Tận dụng sức mạnh phần cứng cục bộ, ví dụ như dàn PC i5 thế hệ 12 đi kèm card RTX 3060 Ti, để render UI giao diện web cực kỳ mượt mà và trực quan khi debug script.
 *
 * Chạy Remote: Khởi chạy thông qua Selenium Grid hoặc các dịch vụ Cloud (như BrowserStack, LambdaTest). Yêu cầu phải trả về một đối tượng RemoteWebDriver.
 *
 * Tiêu thụ ConfigReader: Tự động lấy các giá trị target (local/remote), browser (chrome/edge), và gridUrl từ file cấu hình.
 *
 * Bắt lỗi URL: Khi khởi tạo RemoteWebDriver, URL của Selenium Grid truyền vào có thể bị sai định dạng (ví dụ thiếu http://). Bắt buộc phải handle lỗi MalformedURLException và ném ra một thông báo rõ ràng để người dùng biết họ cấu hình sai URL ở đâu.
 */
public class TargetFactory {
    public static WebDriver createInstance() {
        PlatFormType platFormType = EnumUtils.getFrom(PlatFormType.class, ConfigReader.get("platform"),PlatFormType.WEB);
        TargetType targetType = EnumUtils.getFrom(TargetType.class, ConfigReader.get("targetType"),TargetType.LOCAL);
        try {
            URL url = new URL(ConfigReader.get("remoteURL"));
            switch (platFormType) {
                case ANDROID -> {
                    return createAndroidDriver(url, MobileOptionsManager.getAndroidOptions());
                }

                case IOS -> {
                    return createIOSDriver(url, MobileOptionsManager.getIOSOptions());
                }
            }
            return switchWebType(targetType);
        } catch (MalformedURLException mURLe) {
            throw new RuntimeException("Appium Server URL wrong format");
        }
    }

    private static WebDriver switchWebType(TargetType targetType) {
        switch (targetType) {
            case LOCAL -> {
                return createLocalDriver();
            }
            case REMOTE -> {
                return createRemoteDriver();
            }
            default -> {
                return createLocalDriver();
            }
        }
    }

    private static AndroidDriver createAndroidDriver(URL url, UiAutomator2Options options) {
        AndroidDriver adrDriver = new AndroidDriver(url, options);
        return adrDriver;
    }

    private static IOSDriver createIOSDriver(URL url, XCUITestOptions options) {
        IOSDriver iosDriver = new IOSDriver(url, options);
        return iosDriver;
    }

    private static WebDriver createLocalDriver() {
        String browser = ConfigReader.get("browser").toUpperCase();
        BrowserTypes browserType = BrowserTypes.valueOf(browser);
        boolean isHeadless = ConfigReader.getBoolean("headless");
        WebDriver driver;

        switch (browserType) {
            case CHROME :
                driver = new ChromeDriver(BrowserOptionsManager.getChromeOptions(isHeadless));
                break;
            case FIREFOX:
                driver = new FirefoxDriver(BrowserOptionsManager.getFirefoxOptions(isHeadless));
                break;
            case EDGE:
                driver = new EdgeDriver(BrowserOptionsManager.getEdgeOptions(isHeadless));
                break;
            default:
                throw new IllegalArgumentException("Browser type is not supported : " + browser);
        }

        return driver;
    }

    private static WebDriver createRemoteDriver(){
        String browser = ConfigReader.get("browser").toUpperCase();
        BrowserTypes browserType = BrowserTypes.valueOf(browser);
        boolean isHeadless = ConfigReader.getBoolean("headless");
        WebDriver driver;
        String gridUrl = ConfigReader.get("gridUrl");

        try {
            URL url = new URL(gridUrl);
            switch (browserType) {
                case CHROME :
                    driver = new RemoteWebDriver(BrowserOptionsManager.getChromeOptions(isHeadless));
                    break;
                case FIREFOX:
                    driver = new RemoteWebDriver(BrowserOptionsManager.getFirefoxOptions(isHeadless));
                    break;
                case EDGE:
                    driver = new RemoteWebDriver(BrowserOptionsManager.getEdgeOptions(isHeadless));
                    break;
                default:
                    throw new IllegalArgumentException("Browser type is not supported : " + browser);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("🔥 LỖI CẤU HÌNH: Đường dẫn Selenium Grid URL không hợp lệ: [" + gridUrl + "]. Vui lòng kiểm tra lại file properties!", e);
        }


        return driver;
    }
}
