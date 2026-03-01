package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop = new Properties();

    static {
        String env = System.getProperty("env", "dev");

        InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config/" + env + ".properties");
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config for env : " + env);
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}
