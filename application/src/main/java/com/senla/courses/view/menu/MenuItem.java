package com.senla.courses.view.menu;

import com.lib.utils.ConsoleHelper;
import com.senla.courses.view.action.garageaction.RemoveGarageAction;
import com.senla.courses.view.action.interfaces.IAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.format.DateTimeParseException;

public class MenuItem {

    private static final Logger logger = LoggerFactory.getLogger(MenuItem.class);

    private int number;
    private String title;
    private IAction action;
    private Menu nextMenu;
    private Menu prevMenu;

    public MenuItem(int number, String title, IAction action, Menu nextMenu, Menu prevMenu) {
        this.number = number;
        this.title = title;
        this.action = action;
        this.nextMenu = nextMenu;
        this.prevMenu = prevMenu;
    }

    public void doAction() {
        try {
            action.execute();
        } catch (NumberFormatException e) {
            logger.error("Неверный формат ввода");
        } catch (DateTimeParseException e) {
            logger.error("Неверный формат даты");
        }
    }

    public String getTitle() {
        return title;
    }

    public Menu getNextMenu() {
        return nextMenu;
    }

    public Menu getPrevMenu() {
        return prevMenu;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.format("%d - %s", number, title);
    }
}
