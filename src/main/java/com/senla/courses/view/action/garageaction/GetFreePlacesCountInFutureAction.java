package com.senla.courses.view.action.garageaction;

import com.senla.courses.autoservice.controller.GarageController;
import com.senla.courses.autoservice.utils.ConsoleHelper;

public class GetFreePlacesCountInFutureAction extends AbstractGarageAction {

    public GetFreePlacesCountInFutureAction(GarageController garageController) {
        super(garageController);
    }

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(garageController.getFreePlacesCountInFuture());
    }
}
