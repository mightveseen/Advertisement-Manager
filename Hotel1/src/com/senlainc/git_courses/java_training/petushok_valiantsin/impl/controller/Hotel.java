package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.*;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.*;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status.*;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.service.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class Hotel {
    private IGuestService guestService = new GuestService();
    private IRoomService roomService = new RoomService();
    private IOrderService orderService = new OrderService();
    private IAttendanceService attendanceService = new AttendanceService();

    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        hotel.createRoom();
        hotel.createGuest();
        hotel.createAttendance();
        hotel.showRoom("all");
        hotel.freeRoom();
    }

    private void createRoom() {
        roomService.add(new Room(203, "President", (short)3, new Free(), 330.0));
        roomService.add(new Room(105, "Lux", (short)2, new Rented(LocalDate.of(2019, 01, 22)), 230.0));
        roomService.add(new Room(312, "Business", (short)1, new Served("Replace sofa", LocalTime.of(23, 20)), 130.0));
    }

    private void createGuest() {

    }

    private void createAttendance() {
        attendanceService.add(new Attendance("Lunch", "Food", 12.3));
        attendanceService.add(new Attendance("Dinner", "Food", 9));
        attendanceService.add(new Attendance("Breakfast", "Food", 9));
        attendanceService.add(new Attendance("", "Food", 9));
    }

    private void showRoom(String parameter) {
        roomService.showRoom(parameter);
    }

    private void freeRoom() {
        System.out.print("\nNumber of free room: " + roomService.numFreeRoom());
    }
}
