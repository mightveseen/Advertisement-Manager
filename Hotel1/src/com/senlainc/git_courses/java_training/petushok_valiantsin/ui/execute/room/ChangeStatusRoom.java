package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.room;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.util.Scanner;

public class ChangeStatusRoom implements IAction {
    private final RoomStatus status;

    public ChangeStatusRoom(RoomStatus status) {
        this.status = status;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Hotel.getInstance().showRoom("all");
        System.out.print("Enter room index: ");
        final int index = Integer.parseInt(scanner.nextLine());
        Hotel.getInstance().changeStatusRoom(index, status);
    }
}
