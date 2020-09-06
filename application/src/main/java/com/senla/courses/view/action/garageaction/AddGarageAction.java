package com.senla.courses.view.action.garageaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.GarageController;
import com.senla.courses.view.action.orderaction.AddOrderAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddGarageAction extends AbstractGarageAction {

    private static final Logger logger = LoggerFactory.getLogger(AddGarageAction.class);

    public AddGarageAction(GarageController garageController) {
        super(garageController);
    }

    @Override
    public void execute() {
        String address;
        int id;

        ConsoleHelper.writeMessage("Введите номер гаража:");
        id = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите адрес гаража:");
        address = ConsoleHelper.readString();

        if (garageController.addGarage(id, address) == 1) {
            ConsoleHelper.writeMessage(String.format("Гараж №%d успешно добавлен", id));
        } else {
            ConsoleHelper.writeMessage("При добавлении гаража произошла ошибка");
        }
    }
}
