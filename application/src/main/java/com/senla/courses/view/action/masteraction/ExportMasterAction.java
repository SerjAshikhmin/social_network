package com.senla.courses.view.action.masteraction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.MasterController;

public class ExportMasterAction extends AbstractMasterAction {

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
            ConsoleHelper.writeMessage(String.format("Мастер №%d успешно экспортирован в файл %s", id, fileName));
        } else {
            ConsoleHelper.writeMessage("При экпорте мастера произошла ошибка");
        }
    }
}
