package com.senla.courses.autoservice.ioc;

import com.senla.courses.autoservice.ioc.annotations.Singleton;
import com.senla.courses.autoservice.ioc.interfaces.ObjectConfigurator;
import com.senla.courses.autoservice.utils.ConsoleHelper;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private static List<ObjectConfigurator> configurators = new ArrayList<>();

    public static void run(String packageToScan) {
        Config config = new Config(packageToScan);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setObjectFactory(objectFactory);

        for (Class<? extends ObjectConfigurator> aClass: context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            try {
                configurators.add(aClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Ошибка загрузки конфигураторов");
            }
        }

        createSingletonsByAnnotation(config, context, Singleton.class);

        for (Object obj : context.getCache().values()) {
            configurators.forEach(objectConfigurator -> objectConfigurator.configure(obj, context));
        }
    }

    private static void createSingletonsByAnnotation(Config config, ApplicationContext context, Class<? extends Annotation> annotation) {
        for (Class<?> aClass : config.getScanner().getTypesAnnotatedWith(annotation)) {
            context.getObject(aClass);
        }
    }

}
