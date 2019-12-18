package com.senlainc.git_courses.java_training.petushok_valiantsin.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.Free;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.Rented;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.Served;

import java.time.LocalDate;
import java.time.LocalTime;

public class Hotel {
    private final IGuestService guestService;
    private final IRoomService roomService;
    private final IAttendanceService attendanceService;
    private final IOrderService orderService;

    public Hotel(IGuestService guestService, IRoomService roomService, IAttendanceService attendanceService, IOrderService orderService) {
        this.guestService = guestService;
        this.roomService = roomService;
        this.attendanceService = attendanceService;
        this.orderService = orderService;
    }

    public void createRoom() {
        roomService.add(new Room(203, "President", (short) 4, (short) 6, new Free(), 330.0));
        roomService.add(new Room(312, "Business", (short) 1, (short) 2, new Served("Replace sofa", LocalTime.of(23, 20)), 130.0));
        roomService.add(new Room(401, "Lux", (short) 2, (short) 4, new Free(), 230.0));
        roomService.add(new Room(105, "Lux", (short) 2, (short) 4, new Free(), 230.0));
        roomService.add(new Room(506, "President", (short) 5, (short) 6, new Rented(LocalDate.of(2019, 1, 5)), 230.0));
    }

    public void createGuest() {
        guestService.add(new Guest("Victoria", "July", LocalDate.of(1986, 5, 12), "+1532521678"));
        guestService.add(new Guest("Robert", "Johnson", LocalDate.of(1967, 12, 1), "+278392386"));
        guestService.add(new Guest("Daniel", "Blake", LocalDate.of(1971, 1, 24), "+1532521678"));
    }

    public void createAttendance() {
        attendanceService.add(new Attendance("Lunch", "Food", 12.3));
        attendanceService.add(new Attendance("Dinner", "Food", 9));
        attendanceService.add(new Attendance("Breakfast", "Food", 9));
    }

    public void createOrder() {
        orderService.add(new Order(2, 4, LocalDate.of(2019, 3, 12), roomService.getPrice(3)));
        orderService.add(new Order(1, 1, LocalDate.of(2019, 1, 3), roomService.getPrice(4)));
    }

    public void numGuest() {
        guestService.num();
    }

    public void numFreeRoom() {
        roomService.numFreeRoom();
    }

    public void sortRoom(String parameter) {
//        roomService.sort(parameter);
    }

    public void sortOrder(String parameter) {
        orderService.sort(parameter);
    }

    public void showOrder() {
        orderService.showOrder();
    }

    public void showRoom(String parameter) {
        roomService.show(parameter);
    }

    public void showAfterDate(LocalDate freeDate) {
        roomService.showAfterDate(freeDate);
    }

    public void showGuestRoom(int index) {
        orderService.showGuestRoom(index);
    }

    public void showGuest() {
        guestService.show();
    }

    public void showAttendance(int index) {
        orderService.showAttendance(index);
    }

    public void addAttendance(int orderIndex, int attendanceIndex) {
        orderService.addAttendance(orderIndex, attendanceIndex);
    }
}
