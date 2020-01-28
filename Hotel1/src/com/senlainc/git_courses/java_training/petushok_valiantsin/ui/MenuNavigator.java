package com.senlainc.git_courses.java_training.petushok_valiantsin.ui;

public class MenuNavigator {
    private Menu currentMenu;

    public MenuNavigator(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void printMenu() {
        System.out.println(currentMenu.getName() + "\n" + currentMenu.toString());
    }

    public void navigate(Integer index) {
        currentMenu.getItem(index).doAction();
        currentMenu = currentMenu.getItem(index).getNextMenu();
    }

    public String getMenuName() {
        return currentMenu.getName();
    }
}
