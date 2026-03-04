//package mbbank.auto.keywords;
//
//import com.google.common.collect.ImmutableList;
//import com.google.common.collect.Lists;
//import com.intuit.karate.core.ScenarioEngine;
//import io.appium.java_client.AppiumBy;
//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.PerformsTouchActions;
//import io.appium.java_client.TouchAction;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.nativekey.AndroidKey;
//import io.appium.java_client.android.nativekey.KeyEvent;
//import io.appium.java_client.ios.IOSDriver;
//import io.appium.java_client.touch.LongPressOptions;
//import io.appium.java_client.touch.offset.ElementOption;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.When;
//import mbbank.auto.base.utils.Commons;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.Point;
//import org.openqa.selenium.Rectangle;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Pause;
//import org.openqa.selenium.interactions.PointerInput;
//import org.openqa.selenium.interactions.Sequence;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.math.BigDecimal;
//import java.net.MalformedURLException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.Duration;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
//import io.appium.java_client.clipboard.HasClipboard;
//import org.openqa.selenium.JavascriptExecutor;
//
//public class MobileActions {
//    private final ScenarioEngine engine;
//    private final BaseMobileActions mobileActions;
//
//    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
//
//    public static AppiumDriver getDriver() {
//        return driver.get();
//    }
//
//    public static void setDriver(AppiumDriver appiumDriver) {
//        driver.set(appiumDriver);
//    }
//    public static void removeDriver() {
//        driver.remove();
//    }
//
//    private static final ThreadLocal<String> currentDevice = new ThreadLocal<>();
//
//    public static String getCurrentDevice() {
//        return currentDevice.get();
//    }
//
//    public static void setCurrentDevice(String deviceName) {
//        currentDevice.set(deviceName);
//    }
//
//    public MobileActions() {
//        this.engine = ScenarioEngine.get();
//        this.mobileActions = BaseMobileActions.create();
//    }
//
//    @And("mobile connect to server")
//    public void mobileConnectToServer() throws MalformedURLException {
//        setDriver(mobileActions.driver());
//    }
//
//    @Given("mobile connect to server with platform (.+)")
//    public void mobileOpenUrl(String platform) {
//    }
//
//    @When("mobile ask if locator (.+) is displayed and store the answer in (.+) variable")
//    public void mobileIsDisplayed(String locator, String variableName) {
//        locator = Commons.getStringVar(engine, locator);
//        engine.setVariable(variableName, getDriver().findElement(mobileActions.getByLocator(locator)).isDisplayed());
//    }
//
//    @When("mobile ask if locator (.+) is enabled and store the answer in (.+) variable")
//    public void mobileIsEnabled(String locator, String variableName) {
//        locator = Commons.getStringVar(engine, locator);
//        engine.setVariable(variableName, getDriver().findElement(mobileActions.getByLocator(locator)).isEnabled());
//    }
//
//    @When("mobile ask if locator (.+) is selected and store the answer in (.+) variable")
//    public void mobileIsSelected(String locator, String variableName) {
//        locator = Commons.getStringVar(engine, locator);
//        engine.setVariable(variableName, getDriver().findElement(mobileActions.getByLocator(locator)).isSelected());
//    }
//
//    @When("mobile get attribute (.+) from (.+) locator and store it in (.+) variable")
//    public void mobileGetValueOfAttribute(String name, String locator, String varName) {
//        locator = Commons.getStringVar(engine, locator);
//        mobileWaitForElementDisplayed(locator);
//        engine.setVariable(varName, getDriver().findElement(mobileActions.getByLocator(locator))
//                .getAttribute(Commons.getStringVar(engine, name)));
//    }
//
//    @When("Get number amount from variable (.+)")
//    public void mobileGetnumber(String name){
//        name = Commons.getStringVar(engine, name);
//        name = name.split("\n")[1];
//        String res = String.valueOf(BigDecimal.valueOf(Long.parseLong(name.replaceAll(",",""))));
//        engine.setVariable(name,res);
//        System.out.println(name);
//    }
//
//    @When("Get only number amount from variable (.+)")
//    public void mobileGetOnlyNumber(String name){
//        name = Commons.getStringVar(engine, name);
//        name = name.split("\n")[1]; // Extracts the second line
//        name = name.split(" ")[0]; // Extracts only the number part before the space (removes VND)
//        String res = String.valueOf(BigDecimal.valueOf(Long.parseLong(name.replaceAll(",", ""))));
//        engine.setVariable(name, res);
//        System.out.println(name);
//    }
//
//
//    @When("mobile click on locator (.+)")
//    public void mobileClick(String locator) {
//        locator = Commons.getStringVar(engine, locator);
//        mobileWaitForElementDisplayed(locator);
//        mobileWaitForElementClickable(locator);
//        getDriver().findElement(mobileActions.getByLocator(locator)).click();
//    }
//
//    //    @When("mobile tap on locator (.+)")
//    @When("mobile tap on locator (.+)")
//    public void mobileTap(String locator) {
//
//
//        locator = Commons.getStringVar(engine, locator);
//        mobileWaitForElementClickable(locator);
//
//        WebElement element = getDriver().findElement(mobileActions.getByLocator(locator));
//
//        // Define pointer input for touch
//        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
//
//        //var tapPoint = new Point(101, 565);
//        var tapPoint = new Point(element.getLocation().getX() + element.getSize().getWidth() / 2, element.getLocation().getY() + element.getSize().getHeight() / 2);
//        var tap = new Sequence(finger, 0);
//        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
//                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
//        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
//        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
//        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
//        getDriver().perform(Arrays.asList(tap));
//    }
//
//    @When("mobile tap with sizing on locator (.+) and ratioX value (.+) and ratioY value (.+)")
//    public void mobileTapWithSizing(String locator, String ratioX, String ratioY) {
//
//
//        locator = Commons.getStringVar(engine, locator);
//        ratioX = Commons.getStringVar(engine, ratioX);
//        ratioY = Commons.getStringVar(engine, ratioY);
//        double ratioXVal = Double.parseDouble(Commons.getStringVar(engine, ratioX));
//        double ratioYVal = Double.parseDouble(Commons.getStringVar(engine, ratioY));
//        mobileWaitForElementClickable(locator);
//
//        WebElement element = getDriver().findElement(mobileActions.getByLocator(locator));
//
//        // Define pointer input for touch
//        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
//
//        //var tapPoint = new Point(101, 565);
//        var tapPoint = new Point((int)((element.getLocation().getX() + element.getSize().getWidth() / 2.0) * ratioXVal), (int)((element.getLocation().getY() + element.getSize().getHeight() / 2.0) * ratioYVal + 2));
////        Sequence tap = new Sequence(finger, 1);
////        tap.addAction(finger.createPointerMove(Duration.ofMillis(150), PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
////        System.out.println("XXX "+tapPoint.x);
////        System.out.println("YYY "+tapPoint.y);
////        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
////        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
////        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
////        getDriver().perform(Arrays.asList(tap));
//
//        Sequence tap2 = new Sequence(finger, 0)
//                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y))
//                .addAction(finger.createPointerDown(0))
//                .addAction(finger.createPointerUp(0));
//        getDriver().perform(Arrays.asList(tap2));
//
//    }
//
//    @When("mobile tap element with locator (.+) related to page wrap")
//    public void mobileTapElementWithLocator(String targetLocator) {
//        targetLocator = Commons.getStringVar(engine, targetLocator);
//        String shrunkLayerLocator = "xpath=//XCUIElementTypeApplication[@name=\"MB Prime Test\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther";
//
//        WebElement targetElement = getDriver().findElement(mobileActions.getByLocator(targetLocator));
//        List<WebElement> shrunkLayerElements = getDriver().findElements(mobileActions.getByLocator(shrunkLayerLocator));
//
//        if (shrunkLayerElements.size() > 1) {
//            targetElement.click();
//            System.out.println("Directly tapped target element (screen assumed fixed)");
//        } else {
//            WebElement shrunkLayerElement = shrunkLayerElements.get(0);
//
//            Dimension screenSize = getDriver().manage().window().getSize();
//            int screenWidth = screenSize.getWidth();
//            int screenHeight = screenSize.getHeight();
//
//            Point layerLocation = shrunkLayerElement.getLocation();
//            Dimension layerSize = shrunkLayerElement.getSize();
//
//            int targetCenterX = targetElement.getLocation().getX() + targetElement.getSize().getWidth() / 2;
//            int targetCenterY = targetElement.getLocation().getY() + targetElement.getSize().getHeigh