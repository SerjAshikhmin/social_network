package com.senla.courses.view.action.orderaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.OrderController;

import java.time.LocalDateTime;

public class GetOrdersByPeriodAction extends AbstractOrderAction {

    public GetOrdersByPeriodAction(OrderController orderController) {
        super(orderController);
    }

    @Override
    public void execute() {
        LocalDateTime startPeriod;
        LocalDateTime endPeriod;
        String sortBy;

        ConsoleHelper.writeMessage("Введите начало периода:");
        startPeriod = LocalDateTime.parse(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите конец периода:");
        endPeriod = LocalDateTime.parse(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите способ сортировки (byStartDate - по дате начала выполнения, " +
                "byEndDate - по дате выполнения, byCost - по цене:");
        sortBy = ConsoleHelper.readString();

        ConsoleHelper.writeMessage(orderController.getOrdersByPeriod(startPeriod, endPeriod, sortBy));
    }
}
