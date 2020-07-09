package com.senla.courses.view.menu;

import com.senla.courses.autoservice.ioc.annotations.InjectByType;
import com.senla.courses.autoservice.ioc.annotations.Singleton;
import com.senla.courses.autoservice.utils.ConsoleHelper;

@Singleton
public class MenuController {

    @InjectByType
    private MenuBuilder menuBuilder;
    private Navigator navigator;

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
