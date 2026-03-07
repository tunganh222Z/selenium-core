package uiEngine.baseAction;

import uiEngine.enums.WaitStrategy;
import uiEngine.interfaces.WaitHub;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

public class BaseActions {
    private static WaitHub waitUtils = new WaitUtils();

    public static void setWaitUtils(WaitHub customWaitUtils) {
        waitUtils = customWaitUtils;
    }

    public static void click(By locator) {
        WebElement element = waitUtils.applyWait(locator, WaitStrategy.CLICKABLE);
        element.click();
    }

    public static void sendKeys (By locator, String keys) {
        WebElement element = waitUtils.applyWait(locator, WaitStrategy.VISIBLE);
        element.sendKeys(keys);
    }
}
