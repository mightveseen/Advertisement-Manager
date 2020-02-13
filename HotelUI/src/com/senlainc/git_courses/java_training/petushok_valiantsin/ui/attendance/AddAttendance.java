package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.attendance;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.DependencyController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.WrongEnteredDataException;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

@DependencyClass
public class AddAttendance implements IAction {
    private static final Logger LOGGER = Logger.getLogger(AddAttendance.class.getSimpleName());

    @Override
    public void execute() {
        try {
            final Hotel hotel = DependencyController.getInstance().getClazz(Hotel.class);
            final Scanner scanner = new Scanner(System.in);
            System.out.print("Enter attendance name: ");
            final String name = scanner.nextLine();
            System.out.print("Enter attendance section: ");
            final String section = scanner.nextLine();
            System.out.print("Enter attendance price: ");
            final double price = Double.parseDouble(scanner.nextLine());
            hotel.addAttendance(name, section, price);
            LOGGER.log(Level.INFO, "Add attendance in list");
        } catch (NumberFormatException e) {
            throw new WrongEnteredDataException("Wrong entered data in: " + e.getMessage(), e);
        }
    }
}
