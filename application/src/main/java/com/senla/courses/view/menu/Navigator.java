package com.senla.courses.view.menu;


import com.lib.utils.ConsoleHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Navigator {

    private static final Logger logger = LoggerFactory.getLogger(Navigator.class);

    private Menu currentMenu;

    public Navigator(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void printMenu() {
        currentMenu.getMenuItems().stream()
                .forEach(menuItem -> ConsoleHelper.writeMessage(menuItem));
    }

    public void navigate(int index) {
        MenuItem selectedMenuItem = null;
        for (MenuItem menuItem : currentMenu.getMenuItems()) {
            if (menuItem.getNumber() == index) {
                selectedMenuItem = menuItem;
                break;
            }
        }

        if (selectedMenuItem == null) {
            logger.error("Ошибка: выбран неверный пункт меню");
        } else {
            if (selectedMenuItem.getNextMenu() != null) {
                this.currentMenu = selectedMenuItem.getNextMenu();
            } else {
                if (selectedMenuItem.getTitle().equals("назад")) {
                    this.currentMenu = selectedMenuItem.getPrevMenu();
                } else {
                    selectedMenuItem.doAction();
                }
            }
        }
    }

}
