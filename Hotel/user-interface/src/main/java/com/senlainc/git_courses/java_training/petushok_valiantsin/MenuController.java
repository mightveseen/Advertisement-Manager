package com.senlainc.git_courses.java_training.petushok_valiantsin;

import com.senlainc.git_courses.java_training.petushok_valiantsin.exception.DaoException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.exception.ElementNotFoundException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.exception.EntityNotAvailableException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.exception.EntityNotFoundException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.exception.MaxElementsException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.exception.WrongEnteredDataException;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuController {
    private static final Logger LOGGER = Logger.getLogger(MenuController.class.getName());
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
        } catch (EntityNotFoundException | MaxElementsException | ElementNotFoundException | EntityNotAvailableException | WrongEnteredDataException | InterruptedException | DaoException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    public void run() {
        while (true) {
            showMenu();
        }
    }
}
