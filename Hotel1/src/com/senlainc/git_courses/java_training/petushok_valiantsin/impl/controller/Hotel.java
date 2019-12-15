package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status.Free;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status.Rented;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status.Served;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.service.AttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.service.GuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.service.OrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.service.RoomService;

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
        hotel.sortRoom("price");
        hotel.showRoom("all");
        hotel.showAfterDate(LocalDate.of(2019, 01, 25));
        hotel.freeRoom();
    }

    private void createRoom() {
        roomService.add(new Room(203, "President", (short) 4, new Free(), 330.0));
        roomService.add(new Room(312, "Business", (short) 1, new Served("Replace sofa", LocalTime.of(23, 20)), 130.0));
        roomService.add(new Room(401, "Lux", (short) 2, new Free(), 230.0));
        roomService.add(new Room(105, "Lux", (short) 2, new Free(), 230.0));
        roomService.add(new Room(506, "President", (short) 5, new Rented(LocalDate.of(2019, 01, 05)), 230.0));
    }

    private void createGuest() {
        guestService.add(new Guest("Daniel", "Blake", LocalDate.of(1971, 01, 24), "+1532521678"));
        guestService.add(new Guest("Robert", "Johnson", LocalDate.of(1967, 12, 1), "+278392386"));
        guestService.add(new Guest("Victoria", "July", LocalDate.of(1986, 05, 12), "+1532521678"));

    }

    private void createAttendance() {
        attendanceService.add(new Attendance("Lunch", "Food", 12.3));
        attendanceService.add(new Attendance("Dinner", "Food", 9));
        attendanceService.add(new Attendance("Breakfast", "Food", 9));
        attendanceService.add(new Attendance("", "Food", 9));
    }

    private void createOrder() {

    }

    private void sortRoom(String parameter) {
        roomService.sortRoom(parameter);
    }

    private void showRoom(String parameter) {
        roomService.showRoom(parameter);
    }

    private void showAfterDate(LocalDate freeDate) {
        roomService.showAfterDate(freeDate);
    }

    private void freeRoom() {
        System.out.print("\nNumber of free room: " + roomService.numFreeRoom());
    }
}
