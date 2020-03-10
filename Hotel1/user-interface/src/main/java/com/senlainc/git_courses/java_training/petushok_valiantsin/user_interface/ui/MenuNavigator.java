package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui;

import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.console.ConsoleColor;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotFoundException;

public class MenuNavigator {
    private Menu currentMenu;

    public MenuNavigator(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void printMenu() {
        System.out.println(ConsoleColor.YELLOW.getCode() + currentMenu.getName() + ConsoleColor.RESET.getCode() + "\n" + currentMenu.toString());
    }

    public void navigate(Integer index) {
        try {
            currentMenu.getItem(index).doAction();
            currentMenu = currentMenu.getItem(index).getNextMenu();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ElementNotFoundException("Menu with index: " + index + " didn't exists", e);
        }

    }
}
