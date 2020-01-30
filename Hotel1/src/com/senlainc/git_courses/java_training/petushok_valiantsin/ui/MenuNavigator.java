package com.senlainc.git_courses.java_training.petushok_valiantsin.ui;

import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.ConsoleColor;

public class MenuNavigator {
    private Menu currentMenu;

    public MenuNavigator(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void printMenu() {
        System.out.println(ConsoleColor.YELLOW.getCode() + currentMenu.getName() + ConsoleColor.RESET.getCode() + "\n" + currentMenu.toString());
    }

    public void navigate(Integer index) {
        currentMenu.getItem(index).doAction();
        currentMenu = currentMenu.getItem(index).getNextMenu();
    }
}
