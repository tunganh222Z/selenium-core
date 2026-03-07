package listeners;

public interface ReportListener {
    void onAssertPass(String message);
    void onAssertFail(String message, byte[] screenshotBytes);
    void onStepInfo(String message);
    void onApiStep(String stepName, String requestBody, String responseBody);
}
