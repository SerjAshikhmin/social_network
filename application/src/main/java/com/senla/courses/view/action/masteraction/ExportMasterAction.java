package com.senla.courses.view.action.masteraction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.MasterController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExportMasterAction extends AbstractMasterAction {

    private static final Logger logger = LoggerFactory.getLogger(ExportMasterAction.class);

    public ExportMasterAction(MasterController masterController) {
        super(masterController);
    }

    @Override
    public void execute() {
        int id;
        String fileName;

        ConsoleHelper.writeMessage("Введите номер мастера:");
        id = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите путь к файлу (*.csv):");
        fileName = ConsoleHelper.readString();

        if (masterController.exportMaster(id, fileName)) {
            logger.info(String.format("Мастер №%d успешно экспортирован в файл %s", id, fileName));
        } else {
            logger.error("При экпорте мастера произошла ошибка");
        }
    }
}
