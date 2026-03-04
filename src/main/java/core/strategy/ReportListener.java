package core.strategy;

public interface ReportListener {
    void onAssertPass (String message);
    void onAssertFail (String message, byte[] screenshotBytes);
    void onStepInfo (String message);
}
