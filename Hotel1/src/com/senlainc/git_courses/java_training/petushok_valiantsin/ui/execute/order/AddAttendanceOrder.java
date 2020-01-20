package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.order;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.util.Scanner;

public class AddAttendanceOrder implements IAction {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Hotel.getInstance().showOrder();
        System.out.print("Enter order index: ");
        final int orderIndex = Integer.parseInt(scanner.nextLine());
        Hotel.getInstance().showAttendance();
        System.out.print("Enter attendance index: ");
        final int attendanceIndex = Integer.parseInt(scanner.nextLine());
        Hotel.getInstance().addOrderAttendance(orderIndex, attendanceIndex);
    }
}
