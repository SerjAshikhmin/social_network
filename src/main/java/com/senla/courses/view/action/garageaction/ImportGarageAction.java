package com.senla.courses.view.action.garageaction;

import com.senla.courses.autoservice.controller.GarageController;
import com.senla.courses.autoservice.utils.ConsoleHelper;

public class ImportGarageAction extends AbstractGarageAction {

    public ImportGarageAction(GarageController garageController) {
        super(garageController);
    }

    @Override
    public void execute() {
        String fileName;

        ConsoleHelper.writeMessage("Введите путь к файлу (*.csv):");
        fileName = ConsoleHelper.readString();

        if (garageController.importGarage(fileName)) {
            ConsoleHelper.writeMessage(String.format("Гараж успешно импортирован из файла %s", fileName));
        } else {
            ConsoleHelper.writeMessage("При импорте гаража произошла ошибка");
        }
    }
}
