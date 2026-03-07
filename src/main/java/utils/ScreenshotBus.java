package utils;

import core.CoreManager;
import core.ui.interfaces.ScreenshotHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotBus {

    private static ThreadLocal<ScreenshotHandler> handler = new ThreadLocal<>();

    public static void register(ScreenshotHandler screenshotHandler) {
        handler.set(screenshotHandler);
    }

    public static void publish(byte[] screenshot) {
        if (handler.get() != null) {
            handler.get().handle(screenshot);
        }
    }

    public static void clear() {
        handler.remove();
    }

    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) CoreManager.getContext().getDriver())
                .getScreenshotAs(OutputType.BYTES);
    }
}
