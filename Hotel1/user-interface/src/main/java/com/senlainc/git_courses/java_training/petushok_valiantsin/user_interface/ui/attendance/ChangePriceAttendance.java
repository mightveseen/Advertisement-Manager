package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.attendance;

import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.ApplicationContextHolder;
import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.IAction;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.WrongEnteredDataException;

import java.util.Scanner;

public class ChangePriceAttendance implements IAction {

    @Override
    public void execute() {
        try {
            final Hotel hotel = ApplicationContextHolder.getBean(Hotel.class);
            final Scanner scanner = new Scanner(System.in);
            hotel.showAttendance().forEach(System.out::println);
            System.out.print("Enter attendance index: ");
            final long index = Long.parseLong(scanner.nextLine());
            System.out.print("Enter price: ");
            final double price = Double.parseDouble(scanner.nextLine());
            hotel.changePriceAttendance(index, price);
        } catch (NumberFormatException e) {
            throw new WrongEnteredDataException("Wrong entered date in: " + e.getMessage(), e);
        }
    }
}
