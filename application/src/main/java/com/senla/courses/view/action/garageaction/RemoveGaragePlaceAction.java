package com.senla.courses.view.action.garageaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.GarageController;

public class RemoveGaragePlaceAction extends AbstractGarageAction {

    public RemoveGaragePlaceAction(GarageController garageController) {
        super(garageController);
    }

    @Override
    public void execute() {
        int garageId;
        int garagePlaceId;
        ConsoleHelper.writeMessage("Введите номер гаража:");
        garageId = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите номер места в гараже:");
        garagePlaceId = Integer.parseInt(ConsoleHelper.readString());

        if (garageController.removeGaragePlace(garageId, garagePlaceId)) {
            ConsoleHelper.writeMessage(String.format("Место №%d в гараже №%d успешно удалено", garagePlaceId, garageId));
        } else {
            ConsoleHelper.writeMessage("При удалении места в гараже произошла ошибка");
        }
    }
}
