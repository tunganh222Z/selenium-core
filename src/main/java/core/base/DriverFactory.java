package core.base;

import config.ConfigReader;
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

    public static void initDriver() {
        WebDriver rawDriver = getBrowser();
        driver.set(setDriverListener(rawDriver));
        log.info("====================> INITIALIZING DRIVER - thread {} <====================", Thread.currentThread().getId());

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    private static WebDriver getBrowser(){
        log.info("====================> RUNNING ENV : {} ", System.getProperty("env", "dev"));
        String browser = ConfigReader.get("browser").toLowerCase();
        log.info(String.format("BROWSER ====================> %s", browser));
        switch (browser) {
            case "firefox" :
                return new FirefoxDriver();
            default:
                return new ChromeDriver();
        }
    }

    public static WebDriver setDriverListener(WebDriver rawDriver) {
        WebDriverListener listener = new DriverListeners();

        return new EventFiringDecorator(listener)
                        .decorate(rawDriver);

    }

    public static WebDriver getDriver(){
        return driver.get();
    }

    public static void quitDriver(){
        if (driver.get() != null) {
            log.info("====================> CLEANING DRIVER <====================");
            driver.get().quit();;
            driver.remove();
        }
    }

    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) getDriver())
                .getScreenshotAs(OutputType.BYTES);
    }
}
