package listeners;

import core.CoreManager;
import utils.ScreenshotBus;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;

public class DriverListeners implements WebDriverListener {

    private static final Logger log = LoggerFactory.getLogger(DriverListeners.class);

    public static WebDriver setDriverListener(WebDriver rawDriver) {
        WebDriverListener listener = new DriverListeners();
        return new EventFiringDecorator(listener)
                .decorate(rawDriver);
    }

    @Override
    public void beforeClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(CoreManager.getContext().getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @Override
    public void afterClick(WebElement element) {
        log.info("Click to element ==========> {}", element);
    }

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        log.info("Finding element ==========> {}", locator);
    }

    @Override
    public void beforeGet(WebDriver driver, String url) {
        CoreManager.getContext().getReportListener().onStepInfo("Open URL ==========> " + url);
        log.info("Open URL ==========> {}", url);
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        log.info("Send key to Element ==========> {} + \"{}\"", element, keysToSend);
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        log.error("Driver error in method ==========> {}", method.getName());
        byte[] screenshot = ScreenshotBus.takeScreenshot();
        CoreManager.getContext().getReportListener().onAssertFail("Fail on Step : ", screenshot);
    }
}
