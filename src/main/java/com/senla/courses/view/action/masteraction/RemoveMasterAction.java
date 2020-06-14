package com.senla.courses.view.action.masteraction;

import com.senla.courses.autoservice.controller.MasterController;
import com.senla.courses.autoservice.utils.ConsoleHelper;

public class RemoveMasterAction extends AbstractMasterAction {

    public RemoveMasterAction(MasterController masterController) {
        super(masterController);
    }

    @Override
    public void execute() {
        String name;
        ConsoleHelper.writeMessage("Введите имя мастера:");
        name = ConsoleHelper.readString();

        if (masterController.removeMaster(name)) {
            ConsoleHelper.writeMessage(String.format("Мастер %s успешно удален", name));
        } else {
            ConsoleHelper.writeMessage("При удалении мастера произошла ошибка");
        }
    }
}
