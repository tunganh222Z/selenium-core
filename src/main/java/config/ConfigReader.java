package config;

import utils.Supporter;

import java.io.IOException;
import java.util.Properties;

import static constants.FrameworkConstants.CONFIG_ENV_PATH;
import static constants.FrameworkConstants.DEFAULT_CONFIG;

public class ConfigReader {
    private Properties prop = new Properties();
    private Properties sysProps = System.getProperties();
    private ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    public ConfigReader getConfigProperties() {
        try {
            prop.load(Supporter.readResourceStreamFile(classLoader, DEFAULT_CONFIG + ".properties"));
            prop.load(Supporter.readResourceStreamFile(classLoader, CONFIG_ENV_PATH + "config-dev.properties"));
        } catch (IOException e) {
            throw new RuntimeException("ConfigProperties is not correct");
        }
        return this;
    }

    public String get(String key) {
        String value = sysProps.getProperty(key);

        if (value != null) {
            return value;
        }
        return prop.getProperty(key);
    }

    public int getInt(String key) {
        String value = get(key);

        if (value != null) {
            return Integer.parseInt(value);
        }
        throw new RuntimeException("Config key does not exists : " + key);
    }

    public boolean getBoolean(String key) {
        String value = get(key);

        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return false;
    }
}
