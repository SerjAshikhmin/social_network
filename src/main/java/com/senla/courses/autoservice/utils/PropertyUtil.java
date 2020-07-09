package com.senla.courses.autoservice.utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyUtil {
    private static Map<String, String> properties;

    public static void loadConfig() {
        try {
            File configFile = new File("src/main/resources/config.properties");
            Properties config = new Properties();
            config.load(new FileReader(configFile));
            properties = new HashMap<>();
            for (String propertyName : config.stringPropertyNames()) {
                properties.put(propertyName, config.getProperty(propertyName));
            }
        } catch (FileNotFoundException e) {
            ConsoleHelper.writeMessage("Файл настроек не найден");
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Ошибка ввода/вывода");
        }
    }

    public static String getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    public static Map<String, String> getProperties() {
        return properties;
    }
}
