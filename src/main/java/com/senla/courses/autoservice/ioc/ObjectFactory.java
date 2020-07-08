package com.senla.courses.autoservice.ioc;

import com.senla.courses.autoservice.utils.ConsoleHelper;


public class ObjectFactory {

    private final ApplicationContext context;

    public ObjectFactory(ApplicationContext context) {
        this.context = context;

    }

    public <T> T createObject(Class<T> implClass) {
        try {
            T obj = implClass.getDeclaredConstructor().newInstance();
            return obj;
        } catch (Exception e) {
            ConsoleHelper.writeMessage("Ошибка создания объекта типа " + implClass);
            return null;
        }
    }
}
