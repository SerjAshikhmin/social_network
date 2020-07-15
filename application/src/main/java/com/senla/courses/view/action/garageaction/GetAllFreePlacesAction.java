package com.senla.courses.view.action.garageaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.GarageController;

public class GetAllFreePlacesAction extends AbstractGarageAction {

    public GetAllFreePlacesAction(GarageController garageController) {
        super(garageController);
    }

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(garageController.getAllFreePlaces());
    }
}
