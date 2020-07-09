package com.senla.courses.autoservice.ioc.interfaces;

import com.senla.courses.autoservice.ioc.ApplicationContext;

public interface ObjectConfigurator {
    void configure(Object obj, ApplicationContext context);
}
