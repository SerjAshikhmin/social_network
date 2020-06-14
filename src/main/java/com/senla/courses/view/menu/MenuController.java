package com.senla.courses.view.menu;

import com.senla.courses.view.Main;
import com.senla.courses.autoservice.utils.ConsoleHelper;

public class MenuController {

    private MenuBuilder menuBuilder;
    private Navigator navigator;

    public MenuController() {
        this.menuBuilder = new MenuBuilder(Main.getMasterController(), Main.getOrderController(), Main.getGarageController());
    }

    public void run() {
        menuBuilder.buildMenu();
        navigator = new Navigator(menuBuilder.getRootMenu());
        while (true) {
            navigator.printMenu();
            String command = ConsoleHelper.readString();
            if (command.equals("0")) {
                break;
            }
            try {
                navigator.navigate(Integer.parseInt(command));
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage("Ошибка: выбран неверный пункт меню");
            }

        }
    }
}
