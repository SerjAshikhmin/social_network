package com.senla.courses.view.action.masteraction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.MasterController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddMasterAction extends AbstractMasterAction {

    private static final Logger logger = LoggerFactory.getLogger(AddMasterAction.class);

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

        if (masterController.addMaster(id, name, category) != 0) {
            logger.info(String.format("Мастер %s успешно добавлен", name));
        } else {
            logger.error("При добавлении мастера произошла ошибка");
        }
    }
}
