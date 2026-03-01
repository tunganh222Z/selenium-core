package core.base;

import core.baseAction.BaseAction;
import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;
    protected BaseAction actions;

    protected BasePage() {
        this.driver = DriverFactory.getDriver();
        this.actions = new BaseAction(driver);
    }
}
