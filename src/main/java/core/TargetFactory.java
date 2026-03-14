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

public class TargetFactory {
    public static WebDriver createInstance() {
        PlatFormType platFormType = EnumUtils.getFrom(PlatFormType.class, CoreManager.getContext().getConfigReader().get("platform"),PlatFormType.WEB);
        TargetType targetType = EnumUtils.getFrom(TargetType.class, CoreManager.getContext().getConfigReader().get("targetType"),TargetType.LOCAL);

        try {
            switch (platFormType) {
                case ANDROID -> {
                    URL url = new URL(CoreManager.getContext().getConfigReader().get("remoteURL"));
                    return createAndroidDriver(url, MobileOptionsManager.getAndroidOptions());
                }

                case IOS -> {
                    URL url = new URL(CoreManager.getContext().getConfigReader().get("remoteURL"));
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
        String browser = CoreManager.getContext().getConfigReader().get("browser");
        BrowserTypes browserType = BrowserTypes.valueOf(browser);
        boolean isHeadless = CoreManager.getContext().getConfigReader().getBoolean("headless");
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
        String browser = CoreManager.getContext().getConfigReader().get("browser").toUpperCase();
        BrowserTypes browserType = BrowserTypes.valueOf(browser);
        boolean isHeadless = CoreManager.getContext().getConfigReader().getBoolean("headless");
        WebDriver driver;
        String gridUrl = CoreManager.getContext().getConfigReader().get("gridUrl");

        try {
            URL url = new URL(gridUrl);
            switch (browserType) {
                case CHROME :
                    driver = new RemoteWebDriver(url,BrowserOptionsManager.getChromeOptions(isHeadless));
                    break;
                case FIREFOX:
                    driver = new RemoteWebDriver(url,BrowserOptionsManager.getFirefoxOptions(isHeadless));
                    break;
                case EDGE:
                    driver = new RemoteWebDriver(url,BrowserOptionsManager.getEdgeOptions(isHeadless));
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
