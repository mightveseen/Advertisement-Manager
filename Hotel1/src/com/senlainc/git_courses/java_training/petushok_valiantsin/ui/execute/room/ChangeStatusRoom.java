package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.room;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Status;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.util.Scanner;

public class ChangeStatusRoom implements IAction {
    private final Status.RoomStatus status;

    public ChangeStatusRoom(Status.RoomStatus status) {
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
