package core.baseAction;

import core.context.ExecutionContext;
import core.strategy.WaitStrategy;
import org.openqa.selenium.*;
import utils.WaitUtils;

public class BaseAction {

    private WebDriver driver;
    private WaitStrategy waitStrategy;

    public BaseAction(WebDriver driver) {
        this.driver = driver;
        this.waitStrategy =
                new WaitUtils(driver,
                        ExecutionContext.getTimeout());
    }

    public BaseAction(WebDriver driver, WaitStrategy waitStrategy) {
        this.driver = driver;
        this.waitStrategy = waitStrategy;
    }

    public void click(By locator) {
        waitStrategy.waitForClickable(locator).click();
    }

    public void type(By locator, String text) {
        WebElement element = waitStrategy.waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }
}
