package com.lib.dicontainer.configurators;


import com.lib.dicontainer.ApplicationContext;
import com.lib.dicontainer.annotations.InjectByType;
import com.lib.dicontainer.interfaces.ObjectConfigurator;

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
                    System.out.println("Ошибка инициализации поля");
                }
            }
        }
    }
}
