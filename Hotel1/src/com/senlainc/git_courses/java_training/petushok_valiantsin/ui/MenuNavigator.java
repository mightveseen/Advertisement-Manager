package com.senlainc.git_courses.java_training.petushok_valiantsin.ui;

public class MenuNavigator {
    private Menu currentMenu;
    private static final String SET_MENU_COLOR = "\u001b[33m";
    private static final String RESET_MENU_COLOR = "\u001b[0m";

    public MenuNavigator(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void printMenu() {
        System.out.println(SET_MENU_COLOR + currentMenu.getName() + RESET_MENU_COLOR + "\n" + currentMenu.toString());
    }

    public void navigate(Integer index) {
        currentMenu.getItem(index).doAction();
        currentMenu = currentMenu.getItem(index).getNextMenu();
    }
}
