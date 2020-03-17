package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.attendance;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.DependencyController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.IAction;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.WrongEnteredDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class AddAttendance implements IAction {

    private static final Logger LOGGER = LogManager.getLogger(AddAttendance.class);

    @Override
    public void execute() {
        try {
            final Hotel hotel = (Hotel) DependencyController.getInstance().getClazz(Hotel.class);
            final Scanner scanner = new Scanner(System.in);
            System.out.print("Enter attendance name: ");
            final String name = scanner.nextLine();
            System.out.print("Enter attendance section: ");
            final String section = scanner.nextLine();
            System.out.print("Enter attendance price: ");
            final double price = Double.parseDouble(scanner.nextLine());
            hotel.addAttendance(name, section, price);
            LOGGER.info("Add attendance in list");
        } catch (NumberFormatException e) {
            throw new WrongEnteredDataException("Wrong entered data in: " + e.getMessage(), e);
        }
    }
}
