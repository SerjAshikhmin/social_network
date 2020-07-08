package com.senla.courses.view.menu;

import com.senla.courses.autoservice.controller.GarageController;
import com.senla.courses.autoservice.controller.MasterController;
import com.senla.courses.autoservice.controller.OrderController;
import com.senla.courses.view.action.garageaction.*;
import com.senla.courses.view.action.masteraction.*;
import com.senla.courses.view.action.orderaction.*;
import com.senla.courses.view.action.savestateaction.SaveStateAction;

import java.util.ArrayList;
import java.util.Properties;

public class MenuBuilder {

    private Menu rootMenu;
    private MasterController masterController;
    private OrderController orderController;
    private GarageController garageController;
    private Properties config;

    public MenuBuilder(MasterController masterController, OrderController orderController, GarageController garageController) {
        this.masterController = masterController;
        this.orderController = orderController;
        this.garageController = garageController;
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    public void buildMenu() {
        rootMenu = new Menu("Главное меню", new ArrayList<>());
        Menu masterMenu = new Menu("Меню мастеров", new ArrayList<>());
        Menu orderMenu = new Menu("Меню заказов", new ArrayList<>());
        Menu garageMenu = new Menu("Меню гаража", new ArrayList<>());

        rootMenu.addMenuItem(new MenuItem(1, "мастера", null, masterMenu, null));
        rootMenu.addMenuItem(new MenuItem(2, "заказы", null, orderMenu, null));
        rootMenu.addMenuItem(new MenuItem(3, "гараж", null, garageMenu, null));
        rootMenu.addMenuItem(new MenuItem(4, "сохранить состояние",
                new SaveStateAction(masterController, orderController, garageController), null, null));
        rootMenu.addMenuItem(new MenuItem(0, "выход", null, null, null));

        buildMasterMenu(masterMenu);
        buildOrderMenu(orderMenu);
        buildGarageMenu(garageMenu);
    }

    private void buildMasterMenu(Menu masterMenu) {
        masterMenu.addMenuItem(new MenuItem(1, "добавить мастера",
                new AddMasterAction(masterController), null, rootMenu));
        masterMenu.addMenuItem(new MenuItem(2, "удалить мастера",
                new RemoveMasterAction(masterController), null, rootMenu));
        masterMenu.addMenuItem(new MenuItem(3, "список мастеров",
                new GetAllMastersSortedAction(masterController), null, rootMenu));
        masterMenu.addMenuItem(new MenuItem(4, "заказ конкретного мастера",
                new GetCurrentOrderAction(masterController), null, rootMenu));
        masterMenu.addMenuItem(new MenuItem(5, "импорт мастера",
                new ImportMasterAction(masterController), null, rootMenu));
        masterMenu.addMenuItem(new MenuItem(6, "экспорт мастера",
                new ExportMasterAction(masterController), null, rootMenu));
        masterMenu.addMenuItem(new MenuItem(7, "назад",
                null, null, rootMenu));
    }

    private void buildOrderMenu(Menu orderMenu) {
        orderMenu.addMenuItem(new MenuItem(1, "добавить заказ",
                new AddOrderAction(orderController), null, rootMenu));
        orderMenu.addMenuItem(new MenuItem(2, "удалить заказ",
                new RemoveOrderAction(orderController), null, rootMenu));
        orderMenu.addMenuItem(new MenuItem(3, "закрыть заказ",
                new CloseOrderAction(orderController), null, rootMenu));
        orderMenu.addMenuItem(new MenuItem(4, "отменить заказ",
                new CancelOrderAction(orderController), null, rootMenu));

        orderMenu.addMenuItem(new MenuItem(5, "сместить время выполнения заказов",
                new ShiftEndTimeOrdersAction(orderController), null, rootMenu));

        orderMenu.addMenuItem(new MenuItem(6, "список заказов",
                new GetAllOrdersSortedAction(orderController), null, rootMenu));
        orderMenu.addMenuItem(new MenuItem(7, "список выполняемых заказов",
                new GetAllOrdersInProgressAction(orderController), null, rootMenu));

        orderMenu.addMenuItem(new MenuItem(8, "список мастеров, выполняющих конкретный заказ",
                new GetMastersByOrderAction(orderController), null, rootMenu));
        orderMenu.addMenuItem(new MenuItem(9, "заказы за промежуток времени",
                new GetOrdersByPeriodAction(orderController), null, rootMenu));

        orderMenu.addMenuItem(new MenuItem(10, "ближайшая свободная дата",
                new GetNearestFreeDateAction(orderController), null, rootMenu));

        orderMenu.addMenuItem(new MenuItem(11, "импорт заказа",
                new ImportOrderAction(orderController), null, rootMenu));
        orderMenu.addMenuItem(new MenuItem(12, "экспорт заказа",
                new ExportOrderAction(orderController), null, rootMenu));
        orderMenu.addMenuItem(new MenuItem(13, "назад",
                null, null, rootMenu));
    }

    private void buildGarageMenu(Menu garageMenu) {
        garageMenu.addMenuItem(new MenuItem(1, "добавить гараж",
                new AddGarageAction(garageController), null, rootMenu));
        garageMenu.addMenuItem(new MenuItem(2, "удалить гараж",
                new RemoveGarageAction(garageController), null, rootMenu));
        garageMenu.addMenuItem(new MenuItem(3, "добавить место в гараже",
                new AddGaragePlaceAction(garageController), null, rootMenu));
        garageMenu.addMenuItem(new MenuItem(4, "удалить место в гараже",
                new RemoveGaragePlaceAction(garageController), null, rootMenu));
        garageMenu.addMenuItem(new MenuItem(5, "список свободных мест в гаражах",
                new GetAllFreePlacesAction(garageController), null, rootMenu));
        garageMenu.addMenuItem(new MenuItem(6, "кол-во свободных мест на любую дату в будущем",
                new GetFreePlacesCountInFutureAction(garageController), null, rootMenu));
        garageMenu.addMenuItem(new MenuItem(7, "импорт гаража",
                new ImportGarageAction(garageController), null, rootMenu));
        garageMenu.addMenuItem(new MenuItem(8, "экспорт гаража",
                new ExportGarageAction(garageController), null, rootMenu));
        garageMenu.addMenuItem(new MenuItem(9, "импорт места в гараже",
                new ImportGaragePlaceAction(garageController), null, rootMenu));
        garageMenu.addMenuItem(new MenuItem(10, "экспорт места в гараже",
                new ExportGaragePlaceAction(garageController), null, rootMenu));
        garageMenu.addMenuItem(new MenuItem(11, "назад",
                null, null, rootMenu));
    }

}
