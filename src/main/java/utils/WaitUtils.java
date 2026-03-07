package utils;

import config.ConfigReader;
import core.CoreManager;
import uiEngine.enums.WaitStrategy;
import uiEngine.interfaces.WaitHub;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils implements WaitHub {
    private WebDriverWait wait;

    @Override
    public WebElement applyWait(By locator, WaitStrategy waitStrategy) {
         WebDriverWait wait = new WebDriverWait(CoreManager.getDriver(), Duration.ofSeconds(ConfigReader.getInt("timeout")));

         switch (waitStrategy) {
             case CLICKABLE -> {
                 return waitForClickable(wait ,locator);
             }

             case VISIBLE -> {
                 return waitForVisible(wait, locator);
             }

             case PRESENCE -> {
                return waitForPresence(wait, locator);
             }

             default -> {
                return CoreManager.getDriver().findElement(locator);
             }
         }
    }

    @Override
    public WebElement waitForVisible(WebDriverWait wait, By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Override
    public WebElement waitForClickable(WebDriverWait wait, By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    @Override
    public WebElement waitForPresence(WebDriverWait wait, By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
