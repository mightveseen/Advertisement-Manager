package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui;

import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotFoundException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ExitException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.WrongEnteredDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MenuController {

    private static final Logger LOGGER = LogManager.getLogger(MenuController.class);
    private final MenuNavigator menuNavigator;

    public MenuController() {
        final MenuBuilder menuBuilder = new MenuBuilder();
        final Menu menu = menuBuilder.getRootMenu();
        this.menuNavigator = new MenuNavigator(menu);
    }

    public void showMenu() {
        try {
            TimeUnit.MILLISECONDS.sleep(10);
            menuNavigator.printMenu();
            System.out.print("Choose operation: ");
            final Integer index = Integer.parseInt(new Scanner(System.in).next());
            menuNavigator.navigate(index);
        } catch (ElementNotFoundException | WrongEnteredDataException | InterruptedException | NumberFormatException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    public void run() {
        while (true) {
            try {
                showMenu();
            } catch (ExitException e) {
                LOGGER.info(e.getMessage());
                break;
            }
        }
    }
}
