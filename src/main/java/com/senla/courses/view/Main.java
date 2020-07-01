package com.senla.courses.view;

import com.senla.courses.autoservice.controller.GarageController;
import com.senla.courses.autoservice.controller.MasterController;
import com.senla.courses.autoservice.controller.OrderController;
import com.senla.courses.autoservice.dao.GarageDao;
import com.senla.courses.autoservice.dao.MasterDao;
import com.senla.courses.autoservice.dao.OrderDao;
import com.senla.courses.autoservice.dao.interfaces.IGarageDao;
import com.senla.courses.autoservice.dao.interfaces.IMasterDao;
import com.senla.courses.autoservice.dao.interfaces.IOrderDao;
import com.senla.courses.autoservice.service.interfaces.IMasterService;
import com.senla.courses.autoservice.service.interfaces.IOrderService;
import com.senla.courses.autoservice.model.enums.OrderStatus;
import com.senla.courses.autoservice.service.GarageService;
import com.senla.courses.autoservice.service.MasterService;
import com.senla.courses.autoservice.service.OrderService;
import com.senla.courses.autoservice.service.interfaces.IGarageService;
import com.senla.courses.autoservice.utils.ConsoleHelper;
import com.senla.courses.view.menu.MenuController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Properties;

public class Main {

    private static IOrderService orderService;
    private static IMasterService masterService;
    private static IGarageService garageService;
    private static OrderController orderController;
    private static MasterController masterController;
    private static GarageController garageController;
    private static Properties config;

    public static void main(String[] args) {
        loadConfig();
        createServices();
        loadObjects();
        //fillInTestData();
        MenuController menuController = new MenuController(config);
        menuController.run();
    }

    public static OrderController getOrderController() {
        return orderController;
    }

    public static MasterController getMasterController() {
        return masterController;
    }

    public static GarageController getGarageController() {
        return garageController;
    }

    private static void loadConfig() {
        try {
            File configFile = new File("src/main/resources/config.properties");
            config = new Properties();
            config.load(new FileReader(configFile));
        } catch (FileNotFoundException e) {
            ConsoleHelper.writeMessage("Файл настроек не найден");
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Ошибка ввода/вывода");
        }
    }

    private static void loadObjects() {
        masterController.loadState();
        garageController.loadState();
        orderController.loadState();
    }

    private static void createServices() {
        IMasterDao masterDao = new MasterDao();
        IGarageDao garageDao = new GarageDao();
        IOrderDao orderDao = new OrderDao();

        masterService = new MasterService(masterDao, orderDao);
        garageService = new GarageService(garageDao, masterService);
        orderService = new OrderService(orderDao, masterService, garageService);

        masterController = new MasterController(masterService);
        garageController = new GarageController(garageService);
        orderController = new OrderController(orderService);
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
