package com.senla.courses.view.action.orderaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.OrderController;

public class GetNearestFreeDateAction extends AbstractOrderAction {

    public GetNearestFreeDateAction(OrderController orderController) {
        super(orderController);
    }

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(orderController.getNearestFreeDate());
    }
}
