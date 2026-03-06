package core.driver;

import core.config.ConfigReader;
import core.enums.BrowserTypes;
import core.enums.TargetType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Quyết định logic khởi tạo (Local hay Remote, Browser gì) dựa trên thông tin từ ConfigurationManager.
 */
public class DriverFactory {

    public static void initDriver() {
        TargetType targetType = TargetType.valueOf(ConfigReader.get("target").toUpperCase());
        WebDriver driver = TargetFactory.createInstance(targetType);
        CoreManager.setDriver(driver);
    }
}
