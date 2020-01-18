package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.room;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Status;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.util.Scanner;

public class ChangePriceRoom implements IAction {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Hotel.getInstance().showRoom("all");
        System.out.print("Enter room index: ");
        final int index = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter price(split cost - '.'): ");
        final double price = Double.parseDouble(scanner.nextLine());
        Hotel.getInstance().changePriceRoom(index, price);
    }
}
