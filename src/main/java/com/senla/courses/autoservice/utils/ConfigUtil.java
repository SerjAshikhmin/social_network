package com.senla.courses.autoservice.utils;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigUtil {
    private Map<String, Boolean> properties;

    public ConfigUtil(Properties config) {
        properties = new HashMap<>();
        for (String propertyName : config.stringPropertyNames()) {
            properties.put(propertyName, Boolean.parseBoolean(config.getProperty(propertyName)));
        }
    }

    public Map<String, Boolean> getProperties() {
        return properties;
    }
}
