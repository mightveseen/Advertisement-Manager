package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.guest;

import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.util.Scanner;

public class ShowThreeRoomGuest implements IAction {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Hotel.getInstance().showGuest();
        System.out.print("Enter guest index: ");
        final int index = Integer.parseInt(scanner.nextLine());
        Hotel.getInstance().showGuestRoom(index);
    }
}
