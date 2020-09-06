package com.senla.courses.view.action.garageaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.GarageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoveGaragePlaceAction extends AbstractGarageAction {

    private static final Logger logger = LoggerFactory.getLogger(RemoveGaragePlaceAction.class);

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

        if (garageController.removeGaragePlace(garageId, garagePlaceId) == 1) {
            logger.info(String.format("Место №%d в гараже №%d успешно удалено", garagePlaceId, garageId));
        } else {
            logger.error("При удалении места в гараже произошла ошибка");
        }
    }
}
