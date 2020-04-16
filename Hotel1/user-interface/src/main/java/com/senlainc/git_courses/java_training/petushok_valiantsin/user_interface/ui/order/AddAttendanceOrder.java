package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.order;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.IAction;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.di.ApplicationContextHolder;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.WrongEnteredDataException;

import java.util.Scanner;

public class AddAttendanceOrder implements IAction {

    @Override
    public void execute() {
        try {
            final Hotel hotel = (Hotel) ApplicationContextHolder.getBean(Hotel.class);
            final Scanner scanner = new Scanner(System.in);
            hotel.showOrder().forEach(System.out::println);
            System.out.print("Enter order index: ");
            final long orderIndex = Long.parseLong(scanner.nextLine());
            hotel.showAttendance().forEach(System.out::println);
            System.out.print("Enter attendance index: ");
            final long attendanceIndex = Long.parseLong(scanner.nextLine());
            hotel.addOrderAttendance(orderIndex, attendanceIndex);
        } catch (NumberFormatException e) {
            throw new WrongEnteredDataException("Wrong entered data in: " + e.getMessage(), e);
        }
    }
}
