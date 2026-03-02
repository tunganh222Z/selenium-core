package core.base.ui;

import config.ConfigReader;
import core.base.DriverFactory;
import core.strategy.DriverStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static listeners.DriverListeners.setDriverListener;

public class UiDriverFactory implements DriverStrategy {
    private static final Logger log = LoggerFactory.getLogger(DriverFactory.class);
    WebDriver driver;

    public WebDriver getBrowser() {
        log.info("====================> RUNNING ENV : {} ", System.getProperty("env", "dev"));
        String browser = ConfigReader.get("browser").toLowerCase();
        log.info(String.format("BROWSER ====================> %s", browser));
        switch (browser) {
            case "firefox" :
                driver =  new FirefoxDriver();
            default:
                driver =  new ChromeDriver();
        }
        return driver;
    }

    @Override
    public WebDriver initDriver() {
        driver = getBrowser();
        log.info("====================> INITIALIZING DRIVER - thread {} <====================", Thread.currentThread().getId());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver = setDriverListener(driver);
        return driver;
    }
}
