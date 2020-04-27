package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.guest;

import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.ApplicationContextHolder;
import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.IAction;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.WrongEnteredDataException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AddGuest implements IAction {

    @Override
    public void execute() {
        try {
            final Hotel hotel = ApplicationContextHolder.getBean(Hotel.class);
            final Scanner scanner = new Scanner(System.in);
            System.out.print("Enter first name: ");
            final String firstName = scanner.nextLine();
            System.out.print("Enter last name: ");
            final String lastName = scanner.nextLine();
            System.out.print("Enter birthday(Format: YYYY-MM-DD): ");
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            final LocalDate birthday = LocalDate.parse(scanner.nextLine(), formatter);
            hotel.createGuest(firstName, lastName, birthday);
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new WrongEnteredDataException("Wrong entered data in: " + e.getMessage(), e);
        }
    }
}
