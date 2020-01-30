package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.order;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddAttendanceOrder implements IAction {
    private static final Logger LOGGER = Logger.getLogger(Hotel.class.getSimpleName());

    @Override
    public void execute() {
        try{
            Scanner scanner = new Scanner(System.in);
            Hotel.getInstance().showOrder().forEach(System.out::println);
            System.out.print("Enter order index: ");
            final int orderIndex = Integer.parseInt(scanner.nextLine());
            Hotel.getInstance().showAttendance();
            System.out.print("Enter attendance index: ");
            final int attendanceIndex = Integer.parseInt(scanner.nextLine());
            Hotel.getInstance().addOrderAttendance(orderIndex, attendanceIndex);
            LOGGER.log(Level.INFO, "Add attendance to order");
        } catch (NumberFormatException e) {
            throw new RuntimeException("Wrong data: " + e.getMessage(), e);
        }
    }
}
