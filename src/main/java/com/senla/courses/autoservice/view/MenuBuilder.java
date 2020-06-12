package com.senla.courses.autoservice.view;

import java.util.ArrayList;

public class MenuBuilder {

    private Menu rootMenu;
    private MasterAction masterAction;
    private OrderAction orderAction;
    private GarageAction garageAction;

    public MenuBuilder(MasterAction masterAction, OrderAction orderAction, GarageAction garageAction) {
        this.masterAction = masterAction;
        this.orderAction = orderAction;
        this.garageAction = garageAction;
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    public void buildMenu() {
        rootMenu = new Menu("Главное меню", new ArrayList<>());
        Menu masterMenu = new Menu("Меню мастеров", new ArrayList<>());
        Menu orderMenu = new Menu("Меню заказов", new ArrayList<>());
        Menu garageMenu = new Menu("Меню гаража", new ArrayList<>());

        rootMenu.addMenuItem(new MenuItem(1, "мастера", masterAction, masterMenu, null));
        rootMenu.addMenuItem(new MenuItem(2, "заказы", orderAction, orderMenu, null));
        rootMenu.addMenuItem(new MenuItem(3, "гараж", garageAction, garageMenu, null));
        rootMenu.addMenuItem(new MenuItem(0, "выход", null, null, null));

        buildMasterMenu(masterMenu);
        buildOrderMenu(orderMenu);
        buildGarageMenu(garageMenu);
    }

    private void buildMasterMenu(Menu masterMenu) {
        masterMenu.addMenuItem(new MenuItem(1, "добавить мастера",
                masterAction, null, rootMenu));
        masterMenu.addMenuItem(new MenuItem(2, "удалить мастера",
                masterAction, null, rootMenu));
        masterMenu.addMenuItem(new MenuItem(3, "список мастеров в алфавитном порядке",
                masterAction, null, rootMenu));
        masterMenu.addMenuItem(new MenuItem(4, "список мастеров по занятости",
                masterAction, null, rootMenu));
        masterMenu.addMenuItem(new MenuItem(5, "заказ конкретного мастера",
                masterAction, null, rootMenu));
        masterMenu.addMenuItem(new MenuItem(6, "назад",
                masterAction, null, rootMenu));
    }

    private void buildOrderMenu(Menu orderMenu) {
        orderMenu.addMenuItem(new MenuItem(1, "добавить заказ",
                orderAction, null, rootMenu));
        orderMenu.addMenuItem(new MenuItem(2, "удалить заказ",
                orderAction, null, rootMenu));
        orderMenu.addMenuItem(new MenuItem(3, "закрыть заказ",
                orderAction, null, rootMenu));
        orderMenu.addMenuItem(new MenuItem(4, "отменить заказ",
                orderAction, null, rootMenu));

        orderMenu.addMenuItem(new MenuItem(5, "сместить время выполнения заказов",
                orderAction, null, rootMenu));
        orderMenu.addMenuItem(new MenuItem(6, "список заказов, отсортированный по дате начала выполнения",
                orderAction, null, rootMenu));
        orderMenu.addMenuItem(new MenuItem(7, "список заказов, отсортированный по дате планируемого начала выполнения",
                orderAction, null, rootMenu));
        orderMenu.addMenuItem(new MenuItem(8, "список заказов, отсортированный по дате выполнения",
                orderAction, null, rootMenu));
        orderMenu.addMenuItem(new MenuItem(9, "список заказов, отсортированный по цене",
                orderAction, null, rootMenu));

        orderMenu.addMenuItem(new MenuItem(10, "список выполняемых заказов, отсортированный по цене",
                orderAction, null, rootMenu));
        orderMenu.addMenuItem(new MenuItem(11, "список выполняемых заказов, отсортированный по дате начала выполнения",
                orderAction, null, rootMenu));
        orderMenu.addMenuItem(new MenuItem(12, "список выполняемых заказов, отсортированный по дате выполнения",
                orderAction, null, rootMenu));

        orderMenu.addMenuItem(new MenuItem(13, "список мастеров, выполняющих конкретный заказ",
                orderAction, null, rootMenu));

        orderMenu.addMenuItem(new MenuItem(14, "заказы за промежуток времени, отсортированные по цене",
                orderAction, null, rootMenu));
        orderMenu.addMenuItem(new MenuItem(15, "заказы за промежуток времени, отсортированные по дате начала выполнения",
                orderAction, null, rootMenu));
        orderMenu.addMenuItem(new MenuItem(16, "заказы за промежуток времени, отсортированные по дате выполнения",
                orderAction, null, rootMenu));

        orderMenu.addMenuItem(new MenuItem(17, "ближайшая свободная дата",
                orderAction, null, rootMenu));
        orderMenu.addMenuItem(new MenuItem(18, "назад",
                orderAction, null, rootMenu));
    }

    private void buildGarageMenu(Menu garageMenu) {
        garageMenu.addMenuItem(new MenuItem(1, "добавить гараж",
                garageAction, null, rootMenu));
        garageMenu.addMenuItem(new MenuItem(2, "удалить гараж",
                garageAction, null, rootMenu));
        garageMenu.addMenuItem(new MenuItem(3, "добавить место в гараже",
                garageAction, null, rootMenu));
        garageMenu.addMenuItem(new MenuItem(4, "удалить место в гараже",
                garageAction, null, rootMenu));
        garageMenu.addMenuItem(new MenuItem(5, "список свободных мест в гаражах",
                garageAction, null, rootMenu));
        garageMenu.addMenuItem(new MenuItem(6, "кол-во свободных мест на любую дату в будущем",
                garageAction, null, rootMenu));
        garageMenu.addMenuItem(new MenuItem(7, "назад",
                garageAction, null, rootMenu));
    }

}
