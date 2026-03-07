package uiEngine.driver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Nơi cấu hình ChromeOptions, EdgeOptions (headless, disable-gpu, set window size...).
 */
public class BrowserOptionsManager {

    public static ChromeOptions getChromeOptions(boolean isHeadless) {
        ChromeOptions options = new ChromeOptions();
        if (isHeadless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        return options;
    }

    public static FirefoxOptions getFirefoxOptions(boolean isHeadless) {
        FirefoxOptions options = new FirefoxOptions();
        if (isHeadless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--start-maximized");
        return options;
    }

    public static EdgeOptions getEdgeOptions(boolean isHeadless) {
        EdgeOptions options = new EdgeOptions();
        if (isHeadless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--start-maximized");
        return options;
    }
}
