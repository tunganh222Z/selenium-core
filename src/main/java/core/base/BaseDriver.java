package core.base;

public class BaseDriver {

    public void setup() {
        DriverFactory.initDriver();
    }

    public void tearDown(){
        DriverFactory.quitDriver();
    }
}
