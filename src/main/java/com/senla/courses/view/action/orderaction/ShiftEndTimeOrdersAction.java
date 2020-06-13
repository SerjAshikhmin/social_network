package com.senla.courses.view.action.orderaction;

import com.senla.courses.autoservice.controller.OrderController;
import com.senla.courses.autoservice.utils.ConsoleHelper;

public class ShiftEndTimeOrdersAction extends AbstractOrderAction {

    public ShiftEndTimeOrdersAction(OrderController orderController) {
        super(orderController);
    }

    @Override
    public void execute() {
        int hours;
        int minutes;
        ConsoleHelper.writeMessage("На сколько часов?");
        hours = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("На сколько минут?");
        minutes = Integer.parseInt(ConsoleHelper.readString());

        orderController.shiftEndTimeOrders(hours, minutes);
    }
}
