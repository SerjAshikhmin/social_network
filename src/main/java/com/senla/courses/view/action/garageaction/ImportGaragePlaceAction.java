package com.senla.courses.view.action.garageaction;

import com.senla.courses.autoservice.controller.GarageController;
import com.senla.courses.autoservice.utils.ConsoleHelper;

public class ImportGaragePlaceAction extends AbstractGarageAction {

    public ImportGaragePlaceAction(GarageController garageController) {
        super(garageController);
    }

    @Override
    public void execute() {
        String fileName;

        ConsoleHelper.writeMessage("Введите путь к файлу (*.csv):");
        fileName = ConsoleHelper.readString();

        if (garageController.importGaragePlace(fileName)) {
            ConsoleHelper.writeMessage(String.format("Место в гараже успешно импортировано из файла %s", fileName));
        } else {
            ConsoleHelper.writeMessage("При импорте места в гараже произошла ошибка");
        }
    }
}
