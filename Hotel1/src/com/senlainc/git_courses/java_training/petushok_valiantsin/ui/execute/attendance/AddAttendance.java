package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.attendance;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddAttendance implements IAction {
    private static final Logger LOGGER = Logger.getLogger(Hotel.class.getSimpleName());

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter attendance name: ");
        final String name = scanner.nextLine();
        System.out.print("Enter attendance section: ");
        final String section = scanner.nextLine();
        System.out.print("Enter attendance price: ");
        final double price = Double.parseDouble(scanner.nextLine());
        Hotel.getInstance().addAttendance(name, section, price);
        LOGGER.log(Level.INFO, "Add attendance in list");
    }
}
