package uiEngine.enums;

public enum FileType {
    DEFAULT("config-default.properties"),
    DEV("config-dev.properties"),
    STAGING("config-staging.properties"),
    PROD("config-prod.properties");

    private final String fileName;
    FileType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
