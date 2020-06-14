package com.senla.courses.view.action.orderaction;

import com.senla.courses.autoservice.controller.OrderController;
import com.senla.courses.view.action.interfaces.IAction;

public abstract class AbstractOrderAction implements IAction {

    protected OrderController orderController;

    public AbstractOrderAction(OrderController orderController) {
        this.orderController = orderController;
    }

}
