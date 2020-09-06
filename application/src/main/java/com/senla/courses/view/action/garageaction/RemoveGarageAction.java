package com.senla.courses.view.action.garageaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.GarageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoveGarageAction extends AbstractGarageAction {

    private static final Logger logger = LoggerFactory.getLogger(RemoveGarageAction.class);

    public RemoveGarageAction(GarageController garageController) {
        super(garageController);
    }

    @Override
    public void execute() {
        int garageId;
        ConsoleHelper.writeMessage("Введите номер гаража:");
        garageId = Integer.parseInt(ConsoleHelper.readString());

        if (garageController.removeGarage(garageId) == 1) {
            logger.info(String.format("Гараж %d успешно удален", garageId));
        } else {
            logger.error("При удалении гаража произошла ошибка");
        }
    }
}
