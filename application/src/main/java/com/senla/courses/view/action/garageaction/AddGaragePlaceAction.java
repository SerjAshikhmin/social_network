package com.senla.courses.view.action.garageaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.GarageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddGaragePlaceAction extends AbstractGarageAction {

    private static final Logger logger = LoggerFactory.getLogger(AddGaragePlaceAction.class);

    public AddGaragePlaceAction(GarageController garageController) {
        super(garageController);
    }

    @Override
    public void execute() {
        String type;
        int garageId;
        int garagePlaceId;
        int area;

        ConsoleHelper.writeMessage("Введите номер гаража:");
        garageId = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите номер места в гараже:");
        garagePlaceId = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите тип места в гараже:");
        type = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите площадь:");
        area = Integer.parseInt(ConsoleHelper.readString());

        if (garageController.addGaragePlace(garageId, garagePlaceId, type, area) == 1) {
            logger.info(String.format("Место в гараже №%d успешно добавлено", garagePlaceId));
        } else {
            logger.error("При добавлении места в гараже произошла ошибка");
        }
    }
}
