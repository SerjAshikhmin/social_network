package com.lib.dicontainer.configurators;

import com.lib.dicontainer.ApplicationContext;
import com.lib.dicontainer.annotations.InjectProperty;
import com.lib.dicontainer.interfaces.ObjectConfigurator;
import com.lib.utils.PropertyUtil;

import java.lang.reflect.Field;
import java.util.Map;

public class InjectPropertyObjectConfigurator implements ObjectConfigurator {

    private Map<String, String> properties;

    public InjectPropertyObjectConfigurator() {
        properties = PropertyUtil.getProperties();
    }

    @Override
    public void configure(Object obj, ApplicationContext context) {
        Class<?> implClass = obj.getClass();
        try {
            for (Field field : implClass.getDeclaredFields()) {
                InjectProperty propertyAnnotation = field.getAnnotation(InjectProperty.class);
                String propertyValue;
                if (propertyAnnotation != null) {
                    propertyValue = properties.get(field.getName());
                    field.setAccessible(true);
                    if (field.getType() == boolean.class) {
                        field.set(obj, Boolean.parseBoolean(propertyValue));
                    } else {
                        field.set(obj, propertyValue);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка конфигурации объекта");
        }
    }

}
