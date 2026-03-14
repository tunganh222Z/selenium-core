package core.mobile.driver;

import config.ConfigReader;
import core.CoreManager;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;

public class MobileOptionsManager {

    public static UiAutomator2Options getAndroidOptions() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(CoreManager.getContext().getConfigReader().get("mobile.device.name"))
                .setPlatformVersion(CoreManager.getContext().getConfigReader().get("mobile.platform.version"))
                .setAppPackage(CoreManager.getContext().getConfigReader().get("mobile.app.package"))
                .setAppActivity(CoreManager.getContext().getConfigReader().get("mobile.app.activity"))
                .setAutomationName("UiAutomator2")
                .setNoReset(true);
        return options;
    }

    public static XCUITestOptions getIOSOptions() {
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(CoreManager.getContext().getConfigReader().get("mobile.device.name"))
                .setApp(CoreManager.getContext().getConfigReader().get("mobile.app.path"))
                .setAutomationName("XCUITest");
        return options;
    }
}
