package com.senla.courses.view.action.garageaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.GarageController;

public class AddGarageAction extends AbstractGarageAction {

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

        if (garageController.addGarage(id, address)) {
            ConsoleHelper.writeMessage(String.format("Гараж №%d успешно добавлен", id));
        } else {
            ConsoleHelper.writeMessage("При добавлении гаража произошла ошибка");
        }
    }
}
