package com.senlainc.git_courses.java_training.petushok_valiantsin.ui;

import java.util.Scanner;

public class MenuController {
    private final MenuBuilder menuBuilder;
    private MenuNavigator menuNavigator;
    private Menu menu;

    public MenuController() {
        this.menuBuilder = new MenuBuilder();
        this.menu = menuBuilder.getRootMenu();
        this.menuNavigator = new MenuNavigator(menu);
    }

    public void showMenu() {
        try {
            menuNavigator.printMenu();
            System.out.print("Choose operation: ");
            int index = new Scanner(System.in).nextInt();
            menuNavigator.navigate(index);
        } catch (NullPointerException e) {
            System.err.println("Wrong number/format of operation");
        }
    }

    public void run() throws InterruptedException {
        do {
            showMenu();
//            Thread.sleep(3000);
            menu = menuNavigator.getCurrentMenu();
        } while (true);
    }
}
