package com.senla.courses.view.action.garageaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.GarageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExportGarageAction extends AbstractGarageAction {

    private static final Logger logger = LoggerFactory.getLogger(ExportGarageAction.class);

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
            logger.info(String.format("Гараж №%d успешно экспортирован в файл %s", id, fileName));
        } else {
            logger.error("При экпорте гаража произошла ошибка");
        }
    }
}
