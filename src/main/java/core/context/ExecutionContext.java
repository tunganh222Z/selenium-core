package core.context;

import lombok.Data;

@Data
public class ExecutionContext {
    private static final ThreadLocal<Integer> timeout = new ThreadLocal<>();

    public static void setTimeout(int value) {
        timeout.set(value);
    }

    public static int getTimeout() {
        return timeout.get();
    }

    public static void clear() {
        timeout.remove();
    }
}
