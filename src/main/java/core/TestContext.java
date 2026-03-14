package core;

import config.ConfigReader;
import listeners.DriverListeners;
import org.openqa.selenium.WebDriver;
import listeners.ReportListener;

import java.util.ArrayList;
import java.util.List;

public class TestContext {
    private WebDriver driver;
    private ReportListener reportListener;
    private List<Throwable> softErrors = new ArrayList<>();
    private ConfigReader configReader;

    public WebDriver getDriver() {
        return this.driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = DriverListeners.setDriverListener(driver);
    }

    public ReportListener getReportListener() {
        return this.reportListener;
    }

    public void setReportListener(ReportListener reportListener) {
        this.reportListener = reportListener;
    }

    public List<Throwable> getSoftErrorsList () {
        return this.softErrors;
    }

    public void setConfigReader(ConfigReader configReader) {
        this.configReader = configReader;
    }

    public ConfigReader getConfigReader() {
        return this.configReader;
    }
}
