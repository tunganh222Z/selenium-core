package core;

import uiEngine.interfaces.ReportListener;
import listeners.DriverListeners;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Chứa ThreadLocal<WebDriver> để giữ driver độc lập cho từng luồng chạy song song.
 */
public class CoreManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<ReportListener> listener = new ThreadLocal<>();
    private static final ThreadLocal<List<Throwable>> softErrors = ThreadLocal.withInitial(() -> new ArrayList<>());

    // Getters & Setters cho Listener
    public static ReportListener getListener() { return listener.get(); }
    public static void setListener(ReportListener l) { listener.set(l); }

    // Quản lý Soft Errors
    public static List<Throwable> getSoftErrors() { return softErrors.get(); }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver webDriver){
        webDriver = DriverListeners.setDriverListener(webDriver);
        driver.set(webDriver);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
