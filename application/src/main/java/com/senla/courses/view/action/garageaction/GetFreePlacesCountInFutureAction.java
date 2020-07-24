package com.senla.courses.view.action.garageaction;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.autoservice.controller.GarageController;

public class GetFreePlacesCountInFutureAction extends AbstractGarageAction {

    public GetFreePlacesCountInFutureAction(GarageController garageController) {
        super(garageController);
    }

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(garageController.getFreePlacesCountInFuture());
    }
}
