package com.senla.courses.view.action.orderaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.OrderController;

public class GetAllOrdersInProgressAction extends AbstractOrderAction {

    public GetAllOrdersInProgressAction(OrderController orderController) {
        super(orderController);
    }

    @Override
    public void execute() {
        String sortBy;
        ConsoleHelper.writeMessage("Введите способ сортировки (byStartDate - по дате начала выполнения, " +
                "byEndDate - по дате выполнения, byCost - по цене:");
        sortBy = ConsoleHelper.readString();

        ConsoleHelper.writeMessage(orderController.getAllOrdersInProgress(sortBy));
    }
}
