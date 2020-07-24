package com.senla.courses.view.action.masteraction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.MasterController;

public class GetAllMastersSortedAction extends AbstractMasterAction {

    public GetAllMastersSortedAction(MasterController masterController) {
        super(masterController);
    }

    @Override
    public void execute() {
        String sortBy;
        ConsoleHelper.writeMessage("Введите способ сортировки (byName - в алфавитном порядке, byBusy - по занятости:");
        sortBy = ConsoleHelper.readString();
        ConsoleHelper.writeMessage(masterController.getAllMastersSorted(sortBy));
    }
}
