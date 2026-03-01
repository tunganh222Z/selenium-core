package core.context;

import core.strategy.ScreenshotHandler;

import java.util.function.Consumer;

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
}
