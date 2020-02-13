package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.attendance;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.DependencyController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.WrongEnteredDataException;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangePriceAttendance implements IAction {
        private static final Logger LOGGER = Logger.getLogger(ChangePriceAttendance.class.getSimpleName());

    @Override
    public void execute() {
        try {
            final Hotel hotel = DependencyController.getInstance().getClazz(Hotel.class);
            final Scanner scanner = new Scanner(System.in);
            hotel.showAttendance().forEach(System.out::println);
            System.out.print("Enter attendance index: ");
            final int index = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter price: ");
            final double price = Double.parseDouble(scanner.nextLine());
            hotel.changePriceAttendance(index, price);
            LOGGER.log(Level.INFO, "Change attendance price");
        } catch (NumberFormatException e) {
            throw new WrongEnteredDataException("Wrong entered date in: " + e.getMessage(), e);
        }
    }
}
