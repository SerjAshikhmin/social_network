package com.senla.courses.view.action.garageaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.GarageController;

public class ImportGaragePlaceAction extends AbstractGarageAction {

    public ImportGaragePlaceAction(GarageController garageController) {
        super(garageController);
    }

    @Override
    public void execute() {
        String fileName;

        ConsoleHelper.writeMessage("Введите путь к файлу (*.csv):");
        fileName = ConsoleHelper.readString();

        if (garageController.importGaragePlace(fileName) == 1) {
            ConsoleHelper.writeMessage(String.format("Место в гараже успешно импортировано из файла %s", fileName));
        } else {
            ConsoleHelper.writeMessage("При импорте места в гараже произошла ошибка");
        }
    }
}
