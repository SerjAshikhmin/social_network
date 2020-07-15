package com.senla.courses.view.menu;


import com.lib.dicontainer.annotations.InjectByType;
import com.lib.dicontainer.annotations.Singleton;
import com.lib.utils.ConsoleHelper;

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
