package com.senla.courses.view.action.orderaction;

import com.senla.courses.autoservice.controller.OrderController;
import com.senla.courses.autoservice.model.enums.OrderStatus;
import com.senla.courses.autoservice.utils.ConsoleHelper;

import java.time.LocalDateTime;

public class AddOrderAction extends AbstractOrderAction {

    public AddOrderAction(OrderController orderController) {
        super(orderController);
    }

    @Override
    public void execute() {
        int id;
        LocalDateTime submissionDate;
        String kindOfWork;
        int cost;
        int garagePlaceId;
        String masterName;

        ConsoleHelper.writeMessage("Введите номер заказа:");
        id = Integer.parseInt(ConsoleHelper.readString());
        submissionDate = LocalDateTime.now();
        ConsoleHelper.writeMessage("Введите тип работ:");
        kindOfWork = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите стоимость работ:");
        cost = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите номер места в гараже:");
        garagePlaceId = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите имя мастера:");
        masterName = ConsoleHelper.readString();

        if (orderController.addOrder(id, submissionDate, null, null, kindOfWork, cost,
                garagePlaceId, masterName, OrderStatus.ACCEPTED)) {
            ConsoleHelper.writeMessage(String.format("Заказ №%d успешно добавлен", id));
        } else {
            ConsoleHelper.writeMessage("При добавлении заказа произошла ошибка");
        }
    }
}
