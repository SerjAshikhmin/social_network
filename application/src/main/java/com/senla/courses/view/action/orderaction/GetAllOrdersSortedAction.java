package com.senla.courses.view.action.orderaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.OrderController;

public class GetAllOrdersSortedAction extends AbstractOrderAction {

    public GetAllOrdersSortedAction(OrderController orderController) {
        super(orderController);
    }

    @Override
    public void execute() {
        String sortBy;
        ConsoleHelper.writeMessage("Введите способ сортировки (bySubmission - по дате подачи, " +
                "byStartDate - по дате начала выполнения, byEndDate - по дате выполнения, byCost - по цене:");
        sortBy = ConsoleHelper.readString();

        ConsoleHelper.writeMessage(orderController.getAllOrdersSorted(sortBy));
    }
}