package com.senla.courses;

import com.senla.courses.autoservice.controller.GarageController;
import com.senla.courses.autoservice.controller.MasterController;
import com.senla.courses.autoservice.controller.OrderController;
import com.senla.courses.autoservice.dao.interfaces.IGarageDao;
import com.senla.courses.autoservice.dao.interfaces.IGaragePlaceDao;
import com.senla.courses.autoservice.dao.interfaces.IMasterDao;
import com.senla.courses.autoservice.dao.interfaces.IOrderDao;
import com.senla.courses.autoservice.model.enums.OrderStatus;
import com.senla.courses.autoservice.service.interfaces.IGarageService;
import com.senla.courses.autoservice.service.interfaces.IMasterService;
import com.senla.courses.autoservice.service.interfaces.IOrderService;
import com.senla.courses.view.menu.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;

@Component
public class AppStarter {

    @Autowired
    private OrderController orderController;
    @Autowired
    private MasterController masterController;
    @Autowired
    private GarageController garageController;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IMasterService masterService;
    @Autowired
    private IGarageService garageService;
    @Autowired
    private IOrderDao orderDao;
    @Autowired
    private IMasterDao masterDao;
    @Autowired
    private IGarageDao garageDao;
    @Autowired
    private IGaragePlaceDao garagePlaceDao;
    @Autowired
    private MenuController menuController;

    public void run() {
        //loadObjects();
        fillInTestData();
        menuController.run();
    }

    private void loadObjects() {
        masterController.loadState();
        garageController.loadState();
        orderController.loadState();
    }

    private void fillInTestData() {
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
