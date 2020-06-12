package com.senla.courses.autoservice.view;

import com.senla.courses.autoservice.controller.OrderController;
import com.senla.courses.autoservice.model.enums.OrderStatus;
import com.senla.courses.autoservice.utils.ConsoleHelper;

import java.time.LocalDateTime;

public class OrderAction implements IAction {

    private OrderController orderController;

    public OrderAction(OrderController orderController) {
        this.orderController = orderController;
    }

    @Override
    public void execute(String actionText) {
        switch (actionText) {
            case "добавить заказ":
                addOrder();
                break;
            case "удалить заказ":
                removeOrder();
                break;
            case "закрыть заказ":
                closeOrder();
                break;
            case "отменить заказ":
                cancelOrder();
                break;
            case "сместить время выполнения заказов":
                shiftEndTimeOrders();
                break;
            case "список заказов, отсортированный по дате начала выполнения":
                getAllOrdersSorted("byStartDate");
                break;
            case "список заказов, отсортированный по дате планируемого начала выполнения":
                getAllOrdersSorted("byPlannedStartDate");
                break;
            case "список заказов, отсортированный по дате выполнения":
                getAllOrdersSorted("byEndDate");
                break;
            case "список заказов, отсортированный по цене":
                getAllOrdersSorted("byCost");
                break;
            case "список выполняемых заказов, отсортированный по цене":
                getAllOrdersInProgress("byCost");
                break;
            case "список выполняемых заказов, отсортированный по дате начала выполнения":
                getAllOrdersInProgress("byStartDate");
                break;
            case "список выполняемых заказов, отсортированный по дате выполнения":
                getAllOrdersInProgress("byEndDate");
                break;
            case "список мастеров, выполняющих конкретный заказ":
                getMastersByOrder();
                break;
            case "заказы за промежуток времени, отсортированные по цене":
                getOrdersByPeriod("byCost");
                break;
            case "заказы за промежуток времени, отсортированные по дате начала выполнения":
                getOrdersByPeriod("byStartDate");
                break;
            case "заказы за промежуток времени, отсортированные по дате выполнения":
                getOrdersByPeriod("byEndDate");
                break;
            case "ближайшая свободная дата":
                getNearestFreeDate();
                break;
        }
    }

    public void addOrder() {
        int id;
        LocalDateTime plannedStartDate;
        String kindOfWork;
        int cost;
        int garagePlaceId;
        String masterName;

        ConsoleHelper.writeMessage("Введите номер заказа:");
        id = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите дату планируемого начала выполнения:");
        plannedStartDate = LocalDateTime.parse(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите тип работ:");
        kindOfWork = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите стоимость работ:");
        cost = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите номер места в гараже:");
        garagePlaceId = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите имя мастера:");
        masterName = ConsoleHelper.readString();

        if (orderController.addOrder(id, plannedStartDate, null, null, kindOfWork, cost,
                garagePlaceId, masterName, OrderStatus.ACCEPTED)) {
            ConsoleHelper.writeMessage(String.format("Заказ №%d успешно добавлен", id));
        } else {
            ConsoleHelper.writeMessage("При добавлении заказа произошла ошибка");
        }
    }

    public void removeOrder() {
        int orderId;
        ConsoleHelper.writeMessage("Введите номер номер:");
        orderId = Integer.parseInt(ConsoleHelper.readString());

        if (orderController.removeOrder(orderId)) {
            ConsoleHelper.writeMessage(String.format("Заказ №%d успешно удален", orderId));
        } else {
            ConsoleHelper.writeMessage("При удалении заказа произошла ошибка");
        }
    }

    public void cancelOrder() {
        int orderId;
        ConsoleHelper.writeMessage("Введите номер номер:");
        orderId = Integer.parseInt(ConsoleHelper.readString());

        orderController.cancelOrder(orderId);
    }

    public void closeOrder() {
        int orderId;
        ConsoleHelper.writeMessage("Введите номер номер:");
        orderId = Integer.parseInt(ConsoleHelper.readString());

        orderController.closeOrder(orderId);
    }

    public void shiftEndTimeOrders() {
        int hours;
        int minutes;
        ConsoleHelper.writeMessage("На сколько часов?");
        hours = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("На сколько минут?");
        minutes = Integer.parseInt(ConsoleHelper.readString());

        orderController.shiftEndTimeOrders(hours, minutes);
    }

    public void getAllOrdersSorted(String sortBy) {
        ConsoleHelper.writeMessage(orderController.getAllOrdersSorted(sortBy));
    }

    public void getAllOrdersInProgress(String sortBy) {
        ConsoleHelper.writeMessage(orderController.getAllOrdersInProgress(sortBy));
    }

    public void getMastersByOrder() {
        int id;

        ConsoleHelper.writeMessage("Введите номер заказа:");
        id = Integer.parseInt(ConsoleHelper.readString());

        ConsoleHelper.writeMessage(orderController.getMastersByOrder(id));
    }

    public void getOrdersByPeriod(String sortBy) {
        LocalDateTime startPeriod;
        LocalDateTime endPeriod;

        ConsoleHelper.writeMessage("Введите начало периода:");
        startPeriod = LocalDateTime.parse(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите конец периода:");
        endPeriod = LocalDateTime.parse(ConsoleHelper.readString());

        ConsoleHelper.writeMessage(orderController.getOrdersByPeriod(startPeriod, endPeriod, sortBy));
    }

    public void getNearestFreeDate() {
        ConsoleHelper.writeMessage(orderController.getNearestFreeDate());
    }
}
