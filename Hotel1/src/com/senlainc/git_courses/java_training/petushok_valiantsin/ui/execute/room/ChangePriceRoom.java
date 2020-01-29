package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.room;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangePriceRoom implements IAction {
    private static final Logger LOGGER = Logger.getLogger(Hotel.class.getSimpleName());

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Hotel.getInstance().showRoom("all").forEach(System.out::println);
        System.out.print("Enter room index: ");
        final int index = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter price(split cost - '.'): ");
        final double price = Double.parseDouble(scanner.nextLine());
        Hotel.getInstance().changePriceRoom(index, price);
        LOGGER.log(Level.INFO, "Change room price");
    }
}
