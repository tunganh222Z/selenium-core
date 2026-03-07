package core;

import listeners.DriverListeners;
import org.openqa.selenium.WebDriver;
import uiEngine.interfaces.ReportListener;

import java.util.ArrayList;
import java.util.List;

public class TestContext {
    private WebDriver driver;
    private ReportListener reportListener;
    private List<Throwable> softErrors = new ArrayList<>();

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
}
