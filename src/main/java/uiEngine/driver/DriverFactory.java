package uiEngine.driver;

import config.ConfigReader;
import core.CoreManager;
import uiEngine.enums.TargetType;
import org.openqa.selenium.WebDriver;

/**
 * Quyết định logic khởi tạo (Local hay Remote, Browser gì) dựa trên thông tin từ ConfigurationManager.
 */
public class DriverFactory {

    public static void initDriver() {
        TargetType targetType = TargetType.valueOf(ConfigReader.get("target").toUpperCase());
        WebDriver driver = TargetFactory.createInstance(targetType);
        CoreManager.getContext().setDriver(driver);
    }
}
