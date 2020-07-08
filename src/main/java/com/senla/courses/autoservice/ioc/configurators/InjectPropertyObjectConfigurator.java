package com.senla.courses.autoservice.ioc.configurators;

import com.senla.courses.autoservice.ioc.ApplicationContext;
import com.senla.courses.autoservice.ioc.annotations.InjectProperty;
import com.senla.courses.autoservice.ioc.interfaces.ObjectConfigurator;
import com.senla.courses.autoservice.utils.ConsoleHelper;
import com.senla.courses.autoservice.utils.PropertyUtil;

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
            ConsoleHelper.writeMessage("Ошибка конфигурации объекта");
        }
    }
}
