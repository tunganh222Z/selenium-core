package uiEngine.enums;

/**
 * Định nghĩa sẵn danh sách (CHROME, EDGE, FIREFOX, SAFARI) để tránh lỗi gõ sai (typo) khi truyền tham số.
 */
public enum BrowserTypes {
    CHROME("chrome"), EDGE("edge"), FIREFOX("firefox"), SAFARI("safari");
    private final String browser;

    BrowserTypes(String browser){
        this.browser = browser;
    }

    public String getBrowser(){
        return browser;
    }
}