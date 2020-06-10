package com.senla.courses.autoservice.controller;

import com.senla.courses.autoservice.dao.GarageDao;
import com.senla.courses.autoservice.dao.MasterDao;
import com.senla.courses.autoservice.dao.OrderDao;
import com.senla.courses.autoservice.dao.interfaces.IGarageDao;
import com.senla.courses.autoservice.dao.interfaces.IMasterDao;
import com.senla.courses.autoservice.dao.interfaces.IOrderDao;
import com.senla.courses.autoservice.utils.ConsoleHelper;
import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.GaragePlace;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.model.enums.OrderStatus;
import com.senla.courses.autoservice.service.GarageService;
import com.senla.courses.autoservice.service.MasterService;
import com.senla.courses.autoservice.service.OrderService;
import com.senla.courses.autoservice.service.interfaces.IGarageService;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class Main {

    private static OrderService orderService;
    private static MasterService masterService;
    private static IGarageService garageService;

    public static void main(String[] args) {
        createServices();

        // добавить/удалить мастера
        Master firstMaster = new Master(1, "Evgeniy", 3);
        Master secondMaster = new Master(2, "Alex", 2);
        Master thirdMaster = new Master(3, "Ivan", 5);
        masterService.addMaster(firstMaster);
        masterService.addMaster(secondMaster);
        masterService.addMaster(thirdMaster);
        ConsoleHelper.writeMessage("Добавление мастера:");
        ConsoleHelper.writeMessage(masterService.getAllMasters());
        masterService.removeMaster(thirdMaster);
        ConsoleHelper.writeMessage("Удаление мастера:");
        ConsoleHelper.writeMessage(masterService.getAllMasters());

        // добавить/удалить место в гараже
        Garage firstGarage = new Garage(1, "Orel, Moskovskaya-22", new ArrayList<>());
        garageService.addGarage(firstGarage);
        GaragePlace firstGaragePlace = new GaragePlace(1, "Car lift", 8);
        GaragePlace secondGaragePlace = new GaragePlace(2, "Pit", 12);
        GaragePlace thirdGaragePlace = new GaragePlace(3, "Car lift", 8);
        garageService.addGaragePlace(firstGarage, firstGaragePlace);
        garageService.addGaragePlace(firstGarage, secondGaragePlace);
        garageService.addGaragePlace(firstGarage, thirdGaragePlace);
        ConsoleHelper.writeMessage("Добавление места в гараже:");
        ConsoleHelper.writeMessage(garageService.getAllGarages());
        garageService.removeGaragePlace(firstGarage, thirdGaragePlace);
        ConsoleHelper.writeMessage("Удаление места в гараже:");
        ConsoleHelper.writeMessage(garageService.getAllGarages());

        // добавить/удалить/закрыть/отменить заказ
        List<Master> firstOrderMasters = new ArrayList<>();
        firstOrderMasters.add(firstMaster);
        List<Master> secondOrderMasters = new ArrayList<>();
        secondOrderMasters.add(secondMaster);
        thirdGaragePlace = new GaragePlace(3, "Car lift", 8);
        thirdMaster = new Master(3, "Ivan", 5);
        List<Master> thirdOrderMasters = new ArrayList<>();
        thirdOrderMasters.add(thirdMaster);
        Order firstOrder = new Order(1, LocalDateTime.of(2020, Month.JUNE, 1, 11, 0),
                                            LocalDateTime.of(2020, Month.JUNE, 1, 12, 0),
                                            LocalDateTime.of(2020, Month.JUNE, 1, 13, 0),
                                "Oil change", 500, firstGaragePlace, firstOrderMasters, OrderStatus.ACCEPTED);
        Order secondOrder = new Order(2, LocalDateTime.of(2020, Month.MAY, 31, 13, 0),
                                             LocalDateTime.of(2020, Month.MAY, 31, 14, 0),
                                             LocalDateTime.of(2020, Month.MAY, 31, 15, 0),
                                 "Tire fitting", 500, secondGaragePlace, secondOrderMasters, OrderStatus.ACCEPTED);
        Order thirdOrder = new Order(3, LocalDateTime.of(2020, Month.MAY, 31, 10, 0),
                                            LocalDateTime.of(2020, Month.MAY, 31, 11, 0),
                                            LocalDateTime.of(2020, Month.MAY, 31, 12, 0),
                                            "Diagnostics", 500, thirdGaragePlace, thirdOrderMasters, OrderStatus.ACCEPTED);
        orderService.addOrder(firstOrder);
        orderService.addOrder(secondOrder);
        orderService.addOrder(thirdOrder);
        ConsoleHelper.writeMessage("Добавление заказов:");
        ConsoleHelper.writeMessage(orderService.getAllOrders());
        orderService.removeOrder(thirdOrder);
        orderService.updateOrder(new Order(2, LocalDateTime.of(2020, Month.MAY, 31, 13, 0),
                                                  LocalDateTime.of(2020, Month.MAY, 31, 14, 0),
                                                  LocalDateTime.of(2020, Month.MAY, 31, 15, 0),
                                 "Oil change, diagnostics", 1000, secondGaragePlace, secondOrderMasters, OrderStatus.ACCEPTED));
        orderService.cancelOrder(firstOrder);
        ConsoleHelper.writeMessage("Удаление/изменение/отмена заказов:");
        ConsoleHelper.writeMessage(orderService.getAllOrders());

        // сместить время выполнения заказов
        orderService.shiftEndTimeOrders(2, 30);
        ConsoleHelper.writeMessage("Сместить время выполнения заказов:");
        ConsoleHelper.writeMessage(orderService.getAllOrders());

        // список свободных мест в гаражах
        ConsoleHelper.writeMessage("Список свободных мест в гаражах:");
        ConsoleHelper.writeMessage(garageService.getAllFreePlaces());

        // список заказов по дате подачи
        ConsoleHelper.writeMessage("Список заказов, отсортированный по дате подачи:");
        ConsoleHelper.writeMessage(orderService.getAllOrdersSorted("byStartDate"));
        // список заказов по дате планируемого начала выполнения
        ConsoleHelper.writeMessage("Список заказов, отсортированный по дате планируемого начала выполнения:");
        ConsoleHelper.writeMessage(orderService.getAllOrdersSorted("byPlannedStartDate"));
        // список заказов по дате выполнения
        ConsoleHelper.writeMessage("Список заказов, отсортированный по дате выполнения:");
        ConsoleHelper.writeMessage(orderService.getAllOrdersSorted("byEndDate"));
        // список заказов по цене
        ConsoleHelper.writeMessage("Список заказов, отсортированный по цене:");
        ConsoleHelper.writeMessage(orderService.getAllOrdersSorted("byCost"));

        // список мастеров по алфавиту
        ConsoleHelper.writeMessage("Список мастеров, отсортированный по алфавиту:");
        ConsoleHelper.writeMessage(masterService.getAllMastersSorted("byName"));
        // список мастеров по занятости
        ConsoleHelper.writeMessage("Список мастеров, отсортированный по занятости:");
        ConsoleHelper.writeMessage(masterService.getAllMastersSorted("byBusy"));

        // список выполняемых заказов по цене
        ConsoleHelper.writeMessage("Список выполняемых заказов, отсортированный по цене:");
        ConsoleHelper.writeMessage(orderService.getAllOrdersInProgress("byCost"));

        // заказ конкретного мастера
        ConsoleHelper.writeMessage("Заказ конкретного мастера:");
        ConsoleHelper.writeMessage(masterService.getCurrentOrder(secondMaster));

        // мастера, выполняющие конкретный заказ
        ConsoleHelper.writeMessage("Мастера, выполняющие конкретный заказ:");
        ConsoleHelper.writeMessage(orderService.getMastersByOrder(firstOrder));

        // заказы за промежуток времени
        ConsoleHelper.writeMessage("Заказы за промежуток времени:");
        ConsoleHelper.writeMessage(orderService.getOrdersByPeriod(LocalDateTime.of(2020, Month.MAY, 31, 13, 0),
                LocalDateTime.of(2020, Calendar.JUNE, 1, 9, 0), "byCost"));

        // кол-во свободных мест на любую дату в будущем
        ConsoleHelper.writeMessage("Количество свободных мест на любую дату в будущем:");
        ConsoleHelper.writeMessage(Math.min(garageService.getAllFreePlaces().size(), masterService.getAllFreeMasters().size()));

        // ближайшая свободная дата
        ConsoleHelper.writeMessage("Ближайшая свободная дата:");
        if (garageService.getAllFreePlaces().size() > 0 || masterService.getAllFreeMasters().size() > 0) {
            ConsoleHelper.writeMessage(new GregorianCalendar().getTime());
        } else {
            ConsoleHelper.writeMessage(orderService.getNearestFreeDate());
        }

    }

    private static void createServices() {
        IOrderDao orderDao = new OrderDao();
        orderService = new OrderService(orderDao);
        IMasterDao masterDao = new MasterDao();
        masterService = new MasterService(masterDao);
        IGarageDao garageDao = new GarageDao();
        garageService = new GarageService(garageDao);
    }

}
