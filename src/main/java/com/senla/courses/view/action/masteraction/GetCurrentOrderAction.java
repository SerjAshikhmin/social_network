package com.senla.courses.view.action.masteraction;

import com.senla.courses.autoservice.controller.MasterController;
import com.senla.courses.autoservice.model.Order;
import com.senla.courses.autoservice.utils.ConsoleHelper;

public class GetCurrentOrderAction extends AbstractMasterAction {

    public GetCurrentOrderAction(MasterController masterController) {
        super(masterController);
    }

    @Override
    public void execute() {
        String name;
        ConsoleHelper.writeMessage("Введите имя мастера:");
        name = ConsoleHelper.readString();

        Order currentOrder = masterController.getCurrentOrder(name);
        if (currentOrder != null) {
            ConsoleHelper.writeMessage(currentOrder);
        } else {
            ConsoleHelper.writeMessage("Заказ не найден");
        }
    }
}
