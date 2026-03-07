package uiEngine.actions;

import core.CoreManager;
import uiEngine.context.ScreenshotBus;

import java.util.List;

/**
 * Hàm bọc lại TestNG Assert/AssertJ. Tự động chụp ảnh màn hình và đính kèm vào report nếu Assert fail.
 */
public class ValidateManager {

    public static void assertEquals(String actual, String expected) {
        if (!checkEquals(actual,expected)) {
            throw new AssertionError("❌ FAIL: " );
        }
    }

    public static void softAssertEquals (String actual, String expected) {
        String step = String.format("Validate => %s <= || => %s <=", actual, expected);
        CoreManager.getListener().onStepInfo(step);
        if (!checkEquals(actual,expected)) {
            if (CoreManager.getListener() != null) {
                CoreManager.getSoftErrors().add(new AssertionError("❌ FAIL: " + step ));
            }
        }
    }

    private static boolean checkEquals(String actual, String expected) {
        String step = String.format("Validate => %s <= || => %s <=", actual, expected);
        CoreManager.getListener().onStepInfo(step);
        if (actual.equals(expected)) {
            if (CoreManager.getListener() != null ){
                CoreManager.getListener().onAssertPass("✅ PASS: " + step);
            }
            return true;
        } else {
            if (CoreManager.getListener() != null) {
                CoreManager.getListener().onAssertFail("❌ FAIL: " + step, ScreenshotBus.takeScreenshot());
            }
            return false;
        }
    }

    public static void assertAll() {
        List<Throwable> errors = CoreManager.getSoftErrors();
        boolean hasErrors = !errors.isEmpty();

        if (hasErrors) {
            throw new AssertionError("List assertion Errors : " + errors.toString());
        }
        errors.clear();
    }
}
