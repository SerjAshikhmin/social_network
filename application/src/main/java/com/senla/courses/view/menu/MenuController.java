package com.senla.courses.view.menu;


import com.lib.dicontainer.annotations.InjectByType;
import com.lib.dicontainer.annotations.Singleton;
import com.lib.utils.ConsoleHelper;
import com.senla.courses.view.action.garageaction.RemoveGarageAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Singleton
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

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
                logger.error("Ошибка: выбран неверный пункт меню");
            }

        }
    }
}
