package com.senla.courses.view.action.masteraction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.MasterController;

public class ImportMasterAction extends AbstractMasterAction{

    public ImportMasterAction(MasterController masterController) {
        super(masterController);
    }

    @Override
    public void execute() {
        String fileName;

        ConsoleHelper.writeMessage("Введите путь к файлу (*.csv):");
        fileName = ConsoleHelper.readString();

        if (masterController.importMaster(fileName) != 0) {
            ConsoleHelper.writeMessage(String.format("Мастер успешно импортирован из файла %s", fileName));
        } else {
            ConsoleHelper.writeMessage("При импорте мастера произошла ошибка");
        }
    }
}
