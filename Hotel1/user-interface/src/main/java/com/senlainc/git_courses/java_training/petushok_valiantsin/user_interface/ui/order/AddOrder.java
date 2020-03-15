package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.order;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.DependencyController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.IAction;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.WrongEnteredDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AddOrder implements IAction {

    private static final Logger LOGGER = LogManager.getLogger(AddOrder.class);

    @Override
    public void execute() {
        try {
            final Hotel hotel = (Hotel) DependencyController.getInstance().getClazz(Hotel.class);
            final Scanner scanner = new Scanner(System.in);
            hotel.showGuest().forEach(System.out::println);
            System.out.print("Enter guest index: ");
            final int guestIndex = Integer.parseInt(scanner.nextLine());
            hotel.showRoom("all").forEach(System.out::println);
            System.out.print("Enter room index: ");
            final int roomIndex = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter end date(Format: YYYY-MM-DD): ");
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            final LocalDate endDate = LocalDate.parse(scanner.nextLine(), formatter);
            hotel.addOrder(guestIndex, roomIndex, endDate);
            LOGGER.info("Add order in list");
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new WrongEnteredDataException("Wrong entered data in: " + e.getMessage(), e);
        }
    }
}
