package core.base.mobile;

import core.strategy.DriverStrategy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebDriver;

import java.net.URL;

public class MobileDriverFactory implements DriverStrategy {

    @Override
    public WebDriver initDriver() {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Android Emulator");
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setApp("/path/to/app.apk");

        try {
            return new AndroidDriver(
                    new URL("http://127.0.0.1:4723"),
                    options
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
