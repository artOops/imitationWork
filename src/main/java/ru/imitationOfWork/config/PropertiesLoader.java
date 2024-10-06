package ru.imitationOfWork.config;

import ru.imitationOfWork.util.ErrorText;

import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    private final Properties properties = new Properties();
    private final static String FILE_NAME = "application.properties";

    public Properties loadProperty() {
        if (properties.isEmpty()) {
            try {
                properties.load(PropertiesLoader.class.getClassLoader().getResourceAsStream(FILE_NAME));
            } catch (IOException e) {
                System.out.println(ErrorText.FAILED_READ_FILE_PROPERTIES);
            }
        }
        return properties;
    }
}