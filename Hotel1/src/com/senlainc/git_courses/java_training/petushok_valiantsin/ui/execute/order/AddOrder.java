package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.order;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddOrder implements IAction {
    private static final Logger LOGGER = Logger.getLogger(Hotel.class.getSimpleName());

    @Override
    public void execute() {
        try {
            Scanner scanner = new Scanner(System.in);
            Hotel.getInstance().showGuest().forEach(System.out::println);
            System.out.print("Enter guest index: ");
            final int guestIndex = Integer.parseInt(scanner.nextLine());
            Hotel.getInstance().showRoom("all").forEach(System.out::println);
            System.out.print("Enter room index: ");
            final int roomIndex = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter end date(Format: YYYY-MM-DD): ");
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            final LocalDate endDate = LocalDate.parse(scanner.nextLine(), formatter);
            Hotel.getInstance().addOrder(guestIndex, roomIndex, endDate);
            LOGGER.log(Level.INFO, "Add order in list");
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new RuntimeException("Wrong data: " + e.getMessage(), e);
        }

    }
}
