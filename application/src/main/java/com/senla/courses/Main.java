package com.senla.courses;

import com.senla.courses.autoservice.model.enums.OrderStatus;
import com.senla.courses.autoservice.service.interfaces.IGarageService;
import com.senla.courses.autoservice.service.interfaces.IMasterService;
import com.senla.courses.autoservice.service.interfaces.IOrderService;
import com.senla.courses.view.menu.MenuController;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;
import java.time.Month;


@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.senla.courses"})
@PropertySource("classpath:config.properties")
public class Main {

    private static IOrderService orderService;
    private static IMasterService masterService;
    private static IGarageService garageService;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        masterService = (IMasterService) applicationContext.getBean("masterService");
        garageService = (IGarageService) applicationContext.getBean("garageService");
        orderService = (IOrderService) applicationContext.getBean("orderService");
        MenuController menuController = (MenuController) applicationContext.getBean("menuController");
        fillInTestData();
        menuController.run();
    }

    private static void fillInTestData() {
        masterService.addMaster(1, "Evgeniy", 3);
        masterService.addMaster(2, "Alex", 2);
        masterService.addMaster(3, "Ivan", 5);

        garageService.addGarage(1, "Orel-Moskovskaya-22");
        garageService.addGarage(2, "Orel-Naugorskaya-20");

        garageService.addGaragePlace(1, 1, "Car lift", 8);
        garageService.addGaragePlace(1, 2, "Pit", 12);
        garageService.addGaragePlace(1, 3, "Car lift", 8);
        garageService.addGaragePlace(1, 4, "Car lift", 8);

        orderService.addOrder(1, LocalDateTime.of(2020, Month.JUNE, 1, 11, 0),
                LocalDateTime.of(2020, Month.JUNE, 1, 12, 0),
                LocalDateTime.of(2020, Month.JUNE, 1, 13, 0),
                "Oil change", 1000, 1, 1, "Evgeniy", OrderStatus.ACCEPTED);
        orderService.addOrder(2, LocalDateTime.of(2020, Month.MAY, 31, 13, 0),
                LocalDateTime.of(2020, Month.MAY, 31, 14, 0),
                LocalDateTime.of(2020, Month.MAY, 31, 15, 0),
                "Tire fitting", 300, 1, 2, "Alex", OrderStatus.ACCEPTED);
        orderService.addOrder(3, LocalDateTime.of(2020, Month.MAY, 31, 10, 0),
                LocalDateTime.of(2020, Month.MAY, 31, 11, 0),
                LocalDateTime.of(2020, Month.MAY, 31, 12, 0),
                "Diagnostics", 500, 1, 3, "Ivan", OrderStatus.ACCEPTED);
    }

}
