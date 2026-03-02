package core.base;

import config.ConfigReader;
import core.base.mobile.MobileDriverFactory;
import core.base.ui.UiDriverFactory;
import core.strategy.DriverStrategy;
import listeners.DriverListeners;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger log = LoggerFactory.getLogger(DriverFactory.class);

    public static void setDriver (WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver(){
        if (driver.get() != null) {
            log.info("====================> CLEANING DRIVER <====================");
            driver.get().quit();;
            driver.remove();
        }
    }

    public static WebDriver setupDriver(String platform) {

        DriverStrategy driverManager;

        switch (platform.toLowerCase()) {
            case "android":
                driverManager = new MobileDriverFactory();
                break;
            default:
                driverManager = new UiDriverFactory();
        }

        return driverManager.initDriver();
    }

    public void tearDown(){
        DriverFactory.quitDriver();
    }
}
