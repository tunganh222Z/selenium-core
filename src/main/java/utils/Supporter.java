package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Supporter {

    public static InputStream readResourceStreamFile (ClassLoader classLoader, String fileName) throws IOException {
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        return inputStream;
    }

    public static String readJson2String(InputStream inputStream) throws IOException {
        return new String(inputStream.readAllBytes());
    }
}
