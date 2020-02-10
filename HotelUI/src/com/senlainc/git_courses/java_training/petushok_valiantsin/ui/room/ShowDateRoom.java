package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.room;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.DependencyController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowDateRoom implements IAction {
    private static final Logger LOGGER = Logger.getLogger(Hotel.class.getSimpleName());

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        try {
            final Hotel hotel = DependencyController.getInstance().getClazz(Hotel.class);
            System.out.print("Enter date(Format: YYYY-MM-DD): ");
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            final LocalDate date = LocalDate.parse(scanner.nextLine(), formatter);
            System.out.println("Room will be available after [" + date + "]:");
            hotel.showAfterDate(date).forEach(System.out::println);
            LOGGER.log(Level.INFO, "Show room will free after: " + date);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Wrong data: " + e.getMessage(), e);
        }
    }
}
