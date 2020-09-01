package com.senla.courses.view.action.masteraction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.MasterController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImportMasterAction extends AbstractMasterAction {

    private static final Logger logger = LoggerFactory.getLogger(ImportMasterAction.class);

    public ImportMasterAction(MasterController masterController) {
        super(masterController);
    }

    @Override
    public void execute() {
        String fileName;

        ConsoleHelper.writeMessage("Введите путь к файлу (*.csv):");
        fileName = ConsoleHelper.readString();

        if (masterController.importMaster(fileName) != 0) {
            logger.info(String.format("Мастер успешно импортирован из файла %s", fileName));
        } else {
            logger.error("При импорте мастера произошла ошибка");
        }
    }
}
