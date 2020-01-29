package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.room;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangeStatusRoom implements IAction {
    private final RoomStatus status;
    private static final Logger LOGGER = Logger.getLogger(Hotel.class.getSimpleName());

    public ChangeStatusRoom(RoomStatus status) {
        this.status = status;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Hotel.getInstance().showRoom("all").forEach(System.out::println);
        System.out.print("Enter room index: ");
        final int index = Integer.parseInt(scanner.nextLine());
        Hotel.getInstance().changeStatusRoom(index, status);
        LOGGER.log(Level.INFO, "Change room status");
    }
}
