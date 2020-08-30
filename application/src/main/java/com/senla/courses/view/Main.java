package com.senla.courses.view;

import com.lib.dicontainer.Application;
import com.lib.dicontainer.annotations.InjectByType;
import com.lib.dicontainer.annotations.Singleton;
import com.lib.utils.ConsoleHelper;
import com.lib.utils.PropertyUtil;
import com.senla.courses.autoservice.controller.GarageController;
import com.senla.courses.autoservice.controller.MasterController;
import com.senla.courses.autoservice.controller.OrderController;
import com.senla.courses.autoservice.dao.jdbcdao.DbJdbcConnector;
import com.senla.courses.autoservice.dao.dbqueries.DbQueries;
import com.senla.courses.autoservice.dao.interfaces.IGarageDao;
import com.senla.courses.autoservice.dao.interfaces.IMasterDao;
import com.senla.courses.autoservice.dao.interfaces.IOrderDao;
import com.senla.courses.autoservice.service.interfaces.IMasterService;
import com.senla.courses.autoservice.service.interfaces.IOrderService;
import com.senla.courses.autoservice.model.enums.OrderStatus;
import com.senla.courses.autoservice.service.interfaces.IGarageService;
import com.senla.courses.view.menu.MenuController;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.Month;

@Singleton
public class Main {
    @InjectByType
    private static OrderController orderController;
    @InjectByType
    private static MasterController masterController;
    @InjectByType
    private static GarageController garageController;
    @InjectByType
    private static IOrderService orderService;
    @InjectByType
    private static IMasterService masterService;
    @InjectByType
    private static IGarageService garageService;
    @InjectByType
    private static IOrderDao orderDao;
    @InjectByType
    private static IMasterDao masterDao;
    @InjectByType
    private static IGarageDao garageDao;
    @InjectByType
    private static MenuController menuController;


    public static void main(String[] args) throws SQLException{
        createServices();
        createDataBase();
        //loadObjects();
        fillInTestData();
        menuController.run();
    }

    private static void loadObjects() {
        masterController.loadState();
        garageController.loadState();
        orderController.loadState();
    }

    private static void createServices() {
        PropertyUtil.loadConfig("application/src/main/resources/config.properties");
        Application.run("com");
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

    private static void createDataBase() {
        try {
            Connection connection = DbJdbcConnector.getConnection();
            Statement dataQuery = connection.createStatement();
            dataQuery.execute(DbQueries.CREATE_SCHEMA_QUERY);
            dataQuery.execute(DbQueries.CREATE_GARAGE_TABLE_QUERY);
            dataQuery.execute(DbQueries.CREATE_GARAGE_PLACE_TABLE_QUERY);
            dataQuery.execute(DbQueries.CREATE_ORDER_TABLE_QUERY);
            dataQuery.execute(DbQueries.CREATE_MASTER_TABLE_QUERY);
        } catch (SQLException ex) {
            ConsoleHelper.writeMessage("Ошибка соединения с базой данных");
        }
    }

}
