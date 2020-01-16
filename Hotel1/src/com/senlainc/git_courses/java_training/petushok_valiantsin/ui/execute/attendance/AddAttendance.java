package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.attendance;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddAttendance implements IAction {
    @Override
    public void execute() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter attendance name: ");
            final String name = reader.readLine();
            System.out.print("Enter attendance section: ");
            final String section = reader.readLine();
            System.out.print("Enter attendance price: ");
            final double price = Double.parseDouble(reader.readLine());
            Hotel.getInstance().addAttendance(new Attendance(name, section, price));
        } catch (IOException e) {
            System.err.println("Something go wrong");
        }
    }
}
