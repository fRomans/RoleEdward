package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static Properties getProperty(String file) {
        Properties properties = new Properties();
        try (InputStream in = PropertyReader.class.getClassLoader().getResourceAsStream(file)) {
            properties.load(in);
            return properties;
        } catch (IOException e) {
            return null;
        }
    }
}
