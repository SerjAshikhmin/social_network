package com.senla.courses.view.action.garageaction;

import com.senla.courses.autoservice.controller.GarageController;
import com.senla.courses.autoservice.utils.ConsoleHelper;

public class RemoveGarageAction extends AbstractGarageAction {

    public RemoveGarageAction(GarageController garageController) {
        super(garageController);
    }

    @Override
    public void execute() {
        int garageId;
        ConsoleHelper.writeMessage("Введите номер гаража:");
        garageId = Integer.parseInt(ConsoleHelper.readString());

        if (garageController.removeGarage(garageId)) {
            ConsoleHelper.writeMessage(String.format("Гараж %d успешно удален", garageId));
        } else {
            ConsoleHelper.writeMessage("При удалении гаража произошла ошибка");
        }
    }
}
