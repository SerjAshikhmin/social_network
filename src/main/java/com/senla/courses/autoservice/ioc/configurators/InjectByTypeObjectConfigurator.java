package com.senla.courses.autoservice.ioc.configurators;

import com.senla.courses.autoservice.ioc.ApplicationContext;
import com.senla.courses.autoservice.ioc.interfaces.ObjectConfigurator;
import com.senla.courses.autoservice.utils.ConsoleHelper;
import com.senla.courses.autoservice.ioc.annotations.InjectByType;

import java.lang.reflect.Field;

public class InjectByTypeObjectConfigurator implements ObjectConfigurator {

    @Override
    public void configure(Object obj, ApplicationContext context) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectByType.class)) {
                try {
                    field.setAccessible(true);
                    Object fieldValue = context.getObject(field.getType());
                    field.set(obj, fieldValue);
                } catch (Exception e) {
                    ConsoleHelper.writeMessage("Ошибка инициализации поля");
                }
            }
        }
    }
}
