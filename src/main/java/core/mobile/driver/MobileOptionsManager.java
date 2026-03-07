package core.mobile.driver;

import config.ConfigReader;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;

public class MobileOptionsManager {

    public static UiAutomator2Options getAndroidOptions() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(ConfigReader.get("mobile.device.name"))
                .setPlatformVersion(ConfigReader.get("mobile.platform.version"))
                .setAppPackage(ConfigReader.get("mobile.app.package"))
                .setAppActivity(ConfigReader.get("mobile.app.activity"))
                .setAutomationName("UiAutomator2")
                .setNoReset(true);
        return options;
    }

    public static XCUITestOptions getIOSOptions() {
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(ConfigReader.get("mobile.device.name"))
                .setApp(ConfigReader.get("mobile.app.path"))
                .setAutomationName("XCUITest");
        return options;
    }
}
