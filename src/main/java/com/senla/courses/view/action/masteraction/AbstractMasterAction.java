package com.senla.courses.view.action.masteraction;

import com.senla.courses.autoservice.controller.MasterController;
import com.senla.courses.view.action.interfaces.IAction;

public abstract class AbstractMasterAction implements IAction {

    protected MasterController masterController;

    public AbstractMasterAction(MasterController masterController) {
        this.masterController = masterController;
    }

}
