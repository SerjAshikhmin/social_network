package com.senla.courses.view.action.garageaction;

import com.senla.courses.autoservice.controller.GarageController;
import com.senla.courses.autoservice.utils.ConsoleHelper;

public class GetAllFreePlacesAction extends AbstractGarageAction {

    public GetAllFreePlacesAction(GarageController garageController) {
        super(garageController);
    }

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(garageController.getAllFreePlaces());
    }
}
