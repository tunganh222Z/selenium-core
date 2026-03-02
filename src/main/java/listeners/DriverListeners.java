package listeners;

import core.base.DriverFactory;
import core.context.ScreenshotBus;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DriverListeners implements WebDriverListener {

    private static final Logger log = LoggerFactory.getLogger(DriverListeners.class);

    public static WebDriver setDriverListener(WebDriver rawDriver) {
        WebDriverListener listener = new DriverListeners();
        return new EventFiringDecorator(listener)
                .decorate(rawDriver);
    }

    @Override
    public void beforeClick(WebElement element) {
        log.info("Click to element ==========> {}", element);
    }


    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        log.info("Finding element ==========> {}", locator);
    }

    @Override
    public void beforeGet(WebDriver driver, String url) {
        log.info("Open URL ==========> {}", url);
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        log.error("Driver error in method ==========> {}", method.getName());
        byte[] screenshot = ScreenshotBus.takeScreenshot();
        ScreenshotBus.publish(screenshot);
    }
}
