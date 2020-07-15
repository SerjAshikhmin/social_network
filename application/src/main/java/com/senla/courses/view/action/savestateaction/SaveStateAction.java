package com.senla.courses.view.action.savestateaction;

import com.senla.courses.autoservice.controller.GarageController;
import com.senla.courses.autoservice.controller.MasterController;
import com.senla.courses.autoservice.controller.OrderController;
import com.senla.courses.view.action.interfaces.IAction;

public class SaveStateAction implements IAction {

    private MasterController masterController;
    private OrderController orderController;
    private GarageController garageController;

    public SaveStateAction(MasterController masterController, OrderController orderController, GarageController garageController) {
        this.masterController = masterController;
        this.orderController = orderController;
        this.garageController = garageController;
    }

    @Override
    public void execute() {
        masterController.saveState();
        garageController.saveState();
        orderController.saveState();
    }
}
