package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.attendance;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.IAction;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.di.ApplicationContextHolder;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.WrongEnteredDataException;

import java.util.Scanner;

public class AddAttendance implements IAction {

    @Override
    public void execute() {
        try {
            final Hotel hotel = (Hotel) ApplicationContextHolder.getBean(Hotel.class);
            final Scanner scanner = new Scanner(System.in);
            System.out.print("Enter attendance name: ");
            final String name = scanner.nextLine();
            System.out.print("Enter attendance section: ");
            final String section = scanner.nextLine();
            System.out.print("Enter attendance price: ");
            final double price = Double.parseDouble(scanner.nextLine());
            hotel.addAttendance(name, section, price);
        } catch (NumberFormatException e) {
            throw new WrongEnteredDataException("Wrong entered data in: " + e.getMessage(), e);
        }
    }
}
