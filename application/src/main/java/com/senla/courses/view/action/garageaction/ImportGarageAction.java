package com.senla.courses.view.action.garageaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.GarageController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImportGarageAction extends AbstractGarageAction {

    private static final Logger logger = LoggerFactory.getLogger(ImportGarageAction.class);

    public ImportGarageAction(GarageController garageController) {
        super(garageController);
    }

    @Override
    public void execute() {
        String fileName;

        ConsoleHelper.writeMessage("Введите путь к файлу (*.csv):");
        fileName = ConsoleHelper.readString();

        if (garageController.importGarage(fileName) == 1) {
            logger.info(String.format("Гараж успешно импортирован из файла %s", fileName));
        } else {
            logger.error("При импорте гаража произошла ошибка");
        }
    }
}
