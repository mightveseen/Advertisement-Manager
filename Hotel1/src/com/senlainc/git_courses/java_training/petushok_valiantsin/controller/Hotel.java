package com.senlainc.git_courses.java_training.petushok_valiantsin.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.*;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.MyList;

import java.time.LocalDate;

public class Hotel {
    private final Status.Type[] types;
    private final IGuestService guestService;
    private final IRoomService roomService;
    private final IAttendanceService attendanceService;
    private final IOrderService orderService;

    public Hotel(IGuestService guestService, IRoomService roomService, IAttendanceService attendanceService, IOrderService orderService, Status.Type[] types) {
        this.guestService = guestService;
        this.roomService = roomService;
        this.attendanceService = attendanceService;
        this.orderService = orderService;
        this.types = types;
    }

    public void createRoom() {
        roomService.add(new Room(203, "President", (short) 4, (short) 6, types[0], 330.0));
        roomService.add(new Room(312, "Business", (short) 1, (short) 2, types[0], 170.0));
        roomService.add(new Room(401, "Lux", (short) 2, (short) 4, types[0], 230.0));
        roomService.add(new Room(105, "Lux", (short) 2, (short) 4, types[0], 230.0));
        roomService.add(new Room(506, "President", (short) 5, (short) 6, types[0], 230.0));
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
        orderService.add(new Order(3, 2, LocalDate.of(2019, 2, 23), roomService.getPrice(2)));

    }

    public void numGuest() {
        guestService.num();
    }

    public void numFreeRoom() {
        roomService.numFreeRoom();
    }

    public void sortRoom(String parameter) {
        MyList<Room> myList = roomService.sort(parameter);
        roomService.show(myList);
    }

    public void sortOrder(String parameter) {
        MyList<Order> myList = orderService.sort(parameter);
        orderService.show(myList);
    }

    public void showOrder() {
        orderService.show();
    }

    public void showRoom(String parameter) {
        roomService.show(parameter);
    }

    public void showAfterDate(LocalDate freeDate) {
        orderService.showAfterDate(freeDate);
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
