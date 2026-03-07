package core.ui.driver;

import core.CoreManager;
import core.TargetFactory;
import org.openqa.selenium.WebDriver;

/**
 * Quyết định logic khởi tạo (Local hay Remote, Browser gì) dựa trên thông tin từ ConfigurationManager.
 */
public class DriverFactory {

    public static void initDriver() {
        WebDriver driver = TargetFactory.createInstance();
        CoreManager.getContext().setDriver(driver);
    }
}
