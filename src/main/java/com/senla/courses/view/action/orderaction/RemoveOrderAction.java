package com.senla.courses.view.action.orderaction;

import com.senla.courses.autoservice.controller.OrderController;
import com.senla.courses.autoservice.utils.ConsoleHelper;

public class RemoveOrderAction extends AbstractOrderAction {

    public RemoveOrderAction(OrderController orderController) {
        super(orderController);
    }

    @Override
    public void execute() {
        int orderId;
        ConsoleHelper.writeMessage("Введите номер номер:");
        orderId = Integer.parseInt(ConsoleHelper.readString());

        if (orderController.removeOrder(orderId)) {
            ConsoleHelper.writeMessage(String.format("Заказ №%d успешно удален", orderId));
        } else {
            ConsoleHelper.writeMessage("При удалении заказа произошла ошибка");
        }
    }
}
