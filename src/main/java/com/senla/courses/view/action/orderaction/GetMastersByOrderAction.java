package com.senla.courses.view.action.orderaction;

import com.senla.courses.autoservice.controller.OrderController;
import com.senla.courses.autoservice.utils.ConsoleHelper;

public class GetMastersByOrderAction extends AbstractOrderAction {

    public GetMastersByOrderAction(OrderController orderController) {
        super(orderController);
    }

    @Override
    public void execute() {
        int id;

        ConsoleHelper.writeMessage("Введите номер заказа:");
        id = Integer.parseInt(ConsoleHelper.readString());

        ConsoleHelper.writeMessage(orderController.getMastersByOrder(id));
    }
}
