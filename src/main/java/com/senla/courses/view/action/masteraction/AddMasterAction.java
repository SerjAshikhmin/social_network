package com.senla.courses.view.action.masteraction;

import com.senla.courses.autoservice.controller.MasterController;
import com.senla.courses.autoservice.utils.ConsoleHelper;

public class AddMasterAction extends AbstractMasterAction {

    public AddMasterAction(MasterController masterController) {
        super(masterController);
    }

    @Override
    public void execute() {
        String name;
        int id;
        int category;

        ConsoleHelper.writeMessage("Введите номер мастера:");
        id = Integer.parseInt(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите имя мастера:");
        name = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите разряд мастера:");
        category = Integer.parseInt(ConsoleHelper.readString());

        if (masterController.addMaster(id, name, category)) {
            ConsoleHelper.writeMessage(String.format("Мастер %s успешно добавлен", name));
        } else {
            ConsoleHelper.writeMessage("При добавлении мастера произошла ошибка");
        }
    }
}
