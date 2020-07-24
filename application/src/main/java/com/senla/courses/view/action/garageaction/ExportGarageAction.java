package com.senla.courses.view.action.garageaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.GarageController;

public class ExportGarageAction extends AbstractGarageAction {

    public ExportGarageAction(GarageController garageController) {
        super(garageController);
    }

    @Override
    public void execute() {
        int id;
        String fileName;

        ConsoleHelper.writeMessage("Введите номер гаража:");
        id = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите путь к файлу (*.csv):");
        fileName = ConsoleHelper.readString();

        if (garageController.exportGarage(id, fileName)) {
            ConsoleHelper.writeMessage(String.format("Гараж №%d успешно экспортирован в файл %s", id, fileName));
        } else {
            ConsoleHelper.writeMessage("При экпорте гаража произошла ошибка");
        }
    }
}
