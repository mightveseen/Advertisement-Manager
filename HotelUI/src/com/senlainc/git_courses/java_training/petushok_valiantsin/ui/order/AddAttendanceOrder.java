package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.order;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.DependencyController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddAttendanceOrder implements IAction {
    private static final Logger LOGGER = Logger.getLogger(AddAttendanceOrder.class.getSimpleName());

    @Override
    public void execute() {
        try {
            final Hotel hotel = DependencyController.getInstance().getClazz(Hotel.class);
            final Scanner scanner = new Scanner(System.in);
            hotel.showOrder().forEach(System.out::println);
            System.out.print("Enter order index: ");
            final int orderIndex = Integer.parseInt(scanner.nextLine());
            hotel.showAttendance();
            System.out.print("Enter attendance index: ");
            final int attendanceIndex = Integer.parseInt(scanner.nextLine());
            hotel.addOrderAttendance(orderIndex, attendanceIndex);
            LOGGER.log(Level.INFO, "Add attendance to order");
        } catch (NumberFormatException e) {
            throw new RuntimeException("Wrong data: " + e.getMessage(), e);
        }
    }
}
