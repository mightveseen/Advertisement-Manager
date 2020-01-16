package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.attendance;

import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChangePriceAttendance implements IAction {
    @Override
    public void execute() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Hotel.getInstance().showAttendance();
            System.out.print("Enter attendance index: ");
            final int index = Integer.parseInt(reader.readLine());
            System.out.print("Enter price: ");
            final double price = Double.parseDouble(reader.readLine());
            Hotel.getInstance().changeAttendancePrice(index, price);
        } catch (IOException e) {
            System.err.println("Something go wrong");
        }
    }
}
