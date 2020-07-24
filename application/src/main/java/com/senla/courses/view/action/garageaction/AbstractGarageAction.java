package com.senla.courses.view.action.garageaction;

import com.senla.courses.autoservice.controller.GarageController;
import com.senla.courses.view.action.interfaces.IAction;

public abstract class AbstractGarageAction implements IAction {

    protected GarageController garageController;

    public AbstractGarageAction(GarageController garageController) {
        this.garageController = garageController;
    }

}
