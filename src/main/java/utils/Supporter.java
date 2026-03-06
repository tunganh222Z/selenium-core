package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Supporter {

    public static InputStream readResourceStreamFile (ClassLoader classLoader, String fileName) throws IOException {
        Properties prop = new Properties();

        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        return inputStream;
    }
}
