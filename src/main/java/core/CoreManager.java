package core;

public class CoreManager {
    private static final ThreadLocal<TestContext> context = new ThreadLocal<>();

    public static TestContext getContext() {
        if (context.get() == null) {
            context.set(new TestContext());
        }
        return context.get();
    }

    public static void unload() {
        if (context.get().getDriver() != null) {
            context.get().getDriver().quit();
        }
        context.remove();
    }
}
