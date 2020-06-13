package com.senla.courses.view.action.orderaction;

import com.senla.courses.autoservice.controller.OrderController;
import com.senla.courses.autoservice.utils.ConsoleHelper;

public class GetNearestFreeDateAction extends AbstractOrderAction {

    public GetNearestFreeDateAction(OrderController orderController) {
        super(orderController);
    }

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(orderController.getNearestFreeDate());
    }
}
