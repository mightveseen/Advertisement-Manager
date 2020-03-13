package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.order;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.DependencyController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.IAction;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.WrongEnteredDataException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class AddAttendanceOrder implements IAction {
    private static final Logger LOGGER = LogManager.getLogger(AddAttendanceOrder.class.getName());

    @Override
    public void execute() {
        try {
            final Hotel hotel = (Hotel) DependencyController.getInstance().getClazz(Hotel.class);
            final Scanner scanner = new Scanner(System.in);
            hotel.showOrder().forEach(System.out::println);
            System.out.print("Enter order index: ");
            final int orderIndex = Integer.parseInt(scanner.nextLine());
            hotel.showAttendance().forEach(System.out::println);
            System.out.print("Enter attendance index: ");
            final int attendanceIndex = Integer.parseInt(scanner.nextLine());
            hotel.addOrderAttendance(orderIndex, attendanceIndex);
            LOGGER.log(Level.INFO, "Add attendance to order");
        } catch (NumberFormatException e) {
            throw new WrongEnteredDataException("Wrong entered data in: " + e.getMessage(), e);
        }
    }
}
