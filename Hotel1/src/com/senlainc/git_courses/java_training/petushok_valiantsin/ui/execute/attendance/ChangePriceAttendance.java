package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.attendance;

import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.util.Scanner;

public class ChangePriceAttendance implements IAction {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Hotel.getInstance().showAttendance();
        System.out.print("Enter attendance index: ");
        final int index = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter price: ");
        final double price = Double.parseDouble(scanner.nextLine());
        Hotel.getInstance().changePriceAttendance(index, price);
    }
}
