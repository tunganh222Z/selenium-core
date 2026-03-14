package core.ui.driver;

import config.ConfigReader;
import core.CoreManager;
import core.TargetFactory;
import org.openqa.selenium.WebDriver;

public class DriverFactory {

    public static void initDriver() {
        WebDriver driver = TargetFactory.createInstance();
        CoreManager.getContext().setDriver(driver);
    }

    public static void getConfig() {
        CoreManager.getContext().setConfigReader(new ConfigReader().getConfigProperties());
    }
}
