package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.order;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AddOrder implements IAction {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Hotel.getInstance().showGuest();
        System.out.print("Enter guest index: ");
        final int guestIndex = Integer.parseInt(scanner.nextLine());
        Hotel.getInstance().showRoom("all");
        System.out.print("Enter room index: ");
        final int roomIndex = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter end date(Format: YYYY-MM-DD): ");
        try {
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            final LocalDate endDate = LocalDate.parse(scanner.nextLine(), formatter);
            Hotel.getInstance().addOrder(guestIndex, roomIndex, endDate);
        } catch (DateTimeParseException e) {
            System.err.println("Wrong date format");
        }
    }
}
