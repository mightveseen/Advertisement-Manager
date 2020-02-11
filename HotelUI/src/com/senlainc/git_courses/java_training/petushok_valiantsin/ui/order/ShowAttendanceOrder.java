package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.order;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.DependencyController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowAttendanceOrder implements IAction {
    private static final Logger LOGGER = Logger.getLogger(ShowAttendanceOrder.class.getSimpleName());

    @Override
    public void execute() {
        try {
            final Hotel hotel = DependencyController.getInstance().getClazz(Hotel.class);
            final Scanner scanner = new Scanner(System.in);
            hotel.showGuest().forEach(System.out::println);
            System.out.print("Enter guest index: ");
            final int index = Integer.parseInt(scanner.nextLine());
            hotel.showOrderAttendance(index).forEach(System.out::println);
            LOGGER.log(Level.INFO, "Show guest attendance");
        } catch (NumberFormatException e) {
            throw new RuntimeException("Wrong data: " + e.getMessage(), e);
        }
    }
}
