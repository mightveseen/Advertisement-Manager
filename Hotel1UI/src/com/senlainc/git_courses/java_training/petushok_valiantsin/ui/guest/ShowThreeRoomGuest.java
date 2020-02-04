package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.guest;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowThreeRoomGuest implements IAction {
    private static final Logger LOGGER = Logger.getLogger(Hotel.class.getSimpleName());

    @Override
    public void execute() {
        try {
            Scanner scanner = new Scanner(System.in);
            Hotel.getInstance().showGuest().forEach(System.out::println);
            System.out.print("Enter guest index: ");
            final int index = Integer.parseInt(scanner.nextLine());
            Hotel.getInstance().showGuestRoom(index).forEach(System.out::println);
            LOGGER.log(Level.INFO, "Show last 3 room of guest");
        } catch (NumberFormatException e) {
            throw new RuntimeException("Wrong data: " + e.getMessage(), e);
        }
    }
}
