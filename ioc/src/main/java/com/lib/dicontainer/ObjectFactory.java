package com.lib.dicontainer;


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
            System.out.println("Ошибка создания объекта типа " + implClass);
            return null;
        }
    }
}
