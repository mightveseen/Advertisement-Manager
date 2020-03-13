package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.attendance;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.DependencyController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.IAction;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.WrongEnteredDataException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class ChangePriceAttendance implements IAction {
    private static final Logger LOGGER = LogManager.getLogger(ChangePriceAttendance.class.getName());

    @Override
    public void execute() {
        try {
            final Hotel hotel = (Hotel) DependencyController.getInstance().getClazz(Hotel.class);
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
