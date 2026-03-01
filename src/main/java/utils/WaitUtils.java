package utils;

import core.strategy.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils implements WaitStrategy {
    private WebDriver driver;
    private int timeout;

    public WaitUtils(WebDriver driver, int timeout) {
        this.driver = driver;
        this.timeout = timeout;
    }

    @Override
    public WebElement waitForVisible(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Override
    public WebElement waitForClickable(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
