package com.senla.courses.view.action.orderaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.OrderController;
import com.senla.courses.autoservice.model.enums.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class AddOrderAction extends AbstractOrderAction {

    private static final Logger logger = LoggerFactory.getLogger(AddOrderAction.class);

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
        int garageId;
        String masterName;

        ConsoleHelper.writeMessage("Введите номер заказа:");
        id = Integer.parseInt(ConsoleHelper.readString());
        submissionDate = LocalDateTime.now();
        ConsoleHelper.writeMessage("Введите тип работ:");
        kindOfWork = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите стоимость работ:");
        cost = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите номер гаража:");
        garageId = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите номер места в гараже:");
        garagePlaceId = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите имя мастера:");
        masterName = ConsoleHelper.readString();

        if (orderController.addOrder(id, submissionDate, null, null, kindOfWork, cost, garageId,
                garagePlaceId, masterName, OrderStatus.ACCEPTED) == 1) {
            logger.info(String.format("Заказ №%d успешно добавлен", id));
        } else {
            logger.error("При добавлении заказа произошла ошибка");
        }
    }
}
