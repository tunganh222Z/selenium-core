package core.strategy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface WaitStrategy {
    WebElement waitForVisible(By locator);
    WebElement waitForClickable(By locator);
}
