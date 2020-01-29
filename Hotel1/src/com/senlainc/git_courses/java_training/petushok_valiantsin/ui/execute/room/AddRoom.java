package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.room;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddRoom implements IAction {
    private static final Logger LOGGER = Logger.getLogger(Hotel.class.getSimpleName());

    @Override
    public void execute() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter number: ");
            final int number = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter classification: ");
            final String classification = scanner.nextLine();
            System.out.print("Enter number of room: ");
            final short numberRoom = Short.parseShort(scanner.nextLine());
            System.out.print("Enter capacity: ");
            final short capacity = Short.parseShort(scanner.nextLine());
            System.out.print("Enter price(split cost - '.'): ");
            final double price = Double.parseDouble(scanner.nextLine());
            Hotel.getInstance().addRoom(new Room(number, classification, numberRoom, capacity, price));
            LOGGER.log(Level.INFO, "Add room in list");
        } catch (NumberFormatException e) {
            throw new RuntimeException("Wrong data: " + e.getMessage(), e);
        }
    }
}
