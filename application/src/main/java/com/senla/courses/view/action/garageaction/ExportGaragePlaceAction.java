package com.senla.courses.view.action.garageaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.GarageController;

public class ExportGaragePlaceAction extends AbstractGarageAction {

    public ExportGaragePlaceAction(GarageController garageController) {
        super(garageController);
    }

    @Override
    public void execute() {
        int garageId;
        int garagePlaceId;
        String fileName;

        ConsoleHelper.writeMessage("Введите номер гаража:");
        garageId = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите номер места в гараже:");
        garagePlaceId = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите путь к файлу (*.csv):");
        fileName = ConsoleHelper.readString();

        if (garageController.exportGaragePlace(garageId, garagePlaceId, fileName)) {
            ConsoleHelper.writeMessage(String.format("Место в гараже №%d успешно экспортировано в файл %s", garageId, fileName));
        } else {
            ConsoleHelper.writeMessage("При экпорте места в гараже произошла ошибка");
        }
    }
}
