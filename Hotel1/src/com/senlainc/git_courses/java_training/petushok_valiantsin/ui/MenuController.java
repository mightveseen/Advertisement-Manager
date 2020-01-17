package com.senlainc.git_courses.java_training.petushok_valiantsin.ui;

import java.util.Scanner;

public class MenuController {
    private MenuNavigator menuNavigator;

    public MenuController() {
        final MenuBuilder menuBuilder = new MenuBuilder();
        final Menu menu = menuBuilder.getRootMenu();
        this.menuNavigator = new MenuNavigator(menu);
    }

    public void showMenu() {
        try {
            menuNavigator.printMenu();
            System.out.print("Choose operation: ");
            Integer index = Integer.parseInt(new Scanner(System.in).next());
            menuNavigator.navigate(index);
        } catch (NullPointerException e) {
            System.err.println("Wrong number/format of operation");
        } catch (NumberFormatException e) {
            System.err.println("You entered wrong data");
        }
    }

    public void run() {
        while (true) {
            showMenu();
        }
    }
}
