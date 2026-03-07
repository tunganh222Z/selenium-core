package utils;

import java.lang.reflect.Field;

public final class EnumUtils {
    private EnumUtils () {}

    public static <T extends Enum<T>> T getFrom(
            Class<T> enumClass,
            String value,
            T defaultValue
    ) {
        if (value == null) {
            return defaultValue;
        }

        try {
            Field[] fields = enumClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.isEnumConstant()) {
                    if (field.getName().equalsIgnoreCase(value)) {
                        return Enum.valueOf(enumClass, value);
                    }
                }
            }
        } catch (SecurityException se) {
            se.printStackTrace();
        }
        return defaultValue;
    }
}
