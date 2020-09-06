package com.senla.courses.view.action.masteraction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.MasterController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RemoveMasterAction extends AbstractMasterAction {

    private static final Logger logger = LoggerFactory.getLogger(RemoveMasterAction.class);

    public RemoveMasterAction(MasterController masterController) {
        super(masterController);
    }

    @Override
    public void execute() {
        String name;
        ConsoleHelper.writeMessage("Введите имя мастера:");
        name = ConsoleHelper.readString();

        if (masterController.removeMaster(name) != 0) {
            logger.info(String.format("Мастер %s успешно удален", name));
        } else {
            logger.error("При удалении мастера произошла ошибка");
        }
    }
}
