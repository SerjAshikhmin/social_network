package com.senla.courses.autoservice.view;

public class MenuItem {

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
        action.execute(title);
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
