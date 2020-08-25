package com.senla.courses.view.action.garageaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.GarageController;

public class RemoveGarageAction extends AbstractGarageAction {

    public RemoveGarageAction(GarageController garageController) {
        super(garageController);
    }

    @Override
    public void execute() {
        int garageId;
        ConsoleHelper.writeMessage("Введите номер гаража:");
        garageId = Integer.parseInt(ConsoleHelper.readString());

        if (garageController.removeGarage(garageId) == 1) {
            ConsoleHelper.writeMessage(String.format("Гараж %d успешно удален", garageId));
        } else {
            ConsoleHelper.writeMessage("При удалении гаража произошла ошибка");
        }
    }
}
