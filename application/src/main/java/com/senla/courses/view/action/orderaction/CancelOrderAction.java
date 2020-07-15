package com.senla.courses.view.action.orderaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.OrderController;

public class CancelOrderAction extends AbstractOrderAction {

    public CancelOrderAction(OrderController orderController) {
        super(orderController);
    }

    @Override
    public void execute() {
        int orderId;
        ConsoleHelper.writeMessage("Введите номер номер:");
        orderId = Integer.parseInt(ConsoleHelper.readString());

        orderController.cancelOrder(orderId);
    }
}
