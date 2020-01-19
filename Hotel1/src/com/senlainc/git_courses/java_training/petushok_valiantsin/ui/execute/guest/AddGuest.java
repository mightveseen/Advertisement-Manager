package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.guest;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AddGuest implements IAction {

    @Override
    public void execute() throws DateTimeParseException {
        Scanner scanner = new Scanner(System.in);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter first name: ");
        final String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        final String lastName = scanner.nextLine();
        System.out.print("Enter info contact(Email/Phone number): ");
        final String infoContact = scanner.nextLine();
        try {
            System.out.print("Enter birthday(Format: YYYY-MM-DD): ");
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            final LocalDate birthday = LocalDate.parse(scanner.nextLine(), formatter);
            Hotel.getInstance().addGuest(firstName, lastName, birthday, infoContact);
        } catch (DateTimeParseException e) {
            throw new NumberFormatException("Wrong format of date");
        }
    }
}
