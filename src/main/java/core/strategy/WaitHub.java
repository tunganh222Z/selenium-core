package core.strategy;

import core.enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface WaitHub {
    WebElement applyWait(By locator, WaitStrategy strategy);
    WebElement waitForVisible(WebDriverWait wait, By locator);
    WebElement waitForClickable(WebDriverWait wait, By locator);
    WebElement waitForPresence(WebDriverWait wait, By locator);
}
