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
import com.senlainc.git_courses.java_training.petushok_valiantsin.service.AttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.service.GuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.service.OrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.service.RoomService;

import java.time.LocalDate;
import java.time.LocalTime;

public class Hotel {
    private final IGuestService guestService = new GuestService();
    private final IRoomService roomService = new RoomService();
    private final IAttendanceService attendanceService = new AttendanceService();
    private final IOrderService orderService = new OrderService(roomService, guestService, attendanceService);

    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        /* Create operations */
        hotel.createRoom();
        hotel.createGuest();
        hotel.createAttendance();
        hotel.createOrder();
        /* Room operations */
        hotel.sortRoom("price");
//        hotel.showRoom("all");
        hotel.showAfterDate(LocalDate.of(2019, 1, 25));
        hotel.numFreeRoom();
        /* Guest operations */
        hotel.numGuest();
//        hotel.showOrder();
        /* Order operations */
        hotel.addAttendance(1, 0);
        hotel.sortOrder("date");
        hotel.showGuestRoom(0);
        hotel.showAttendance(1);
    }

    private void createRoom() {
        roomService.add(new Room(203, "President", (short) 4, new Free(), 330.0));
        roomService.add(new Room(312, "Business", (short) 1, new Served("Replace sofa", LocalTime.of(23, 20)), 130.0));
        roomService.add(new Room(401, "Lux", (short) 2, new Free(), 230.0));
        roomService.add(new Room(105, "Lux", (short) 2, new Free(), 230.0));
        roomService.add(new Room(506, "President", (short) 5, new Rented(LocalDate.of(2019, 1, 5)), 230.0));
    }

    private void createGuest() {
        guestService.add(new Guest("Victoria", "July", LocalDate.of(1986, 5, 12), "+1532521678"));
        guestService.add(new Guest("Robert", "Johnson", LocalDate.of(1967, 12, 1), "+278392386"));
        guestService.add(new Guest("Daniel", "Blake", LocalDate.of(1971, 1, 24), "+1532521678"));
    }

    private void createAttendance() {
        attendanceService.add(new Attendance("Lunch", "Food", 12.3));
        attendanceService.add(new Attendance("Dinner", "Food", 9));
        attendanceService.add(new Attendance("Breakfast", "Food", 9));
    }

    private void createOrder() {
        orderService.add(new Order(1, 3, (short) 3, LocalDate.of(2019, 3, 12), roomService.getPrice(3)));
        orderService.add(new Order(0, 0, (short) 1, LocalDate.of(2019, 1, 3), roomService.getPrice(4)));
    }

    private void numGuest() {
        guestService.num();
    }

    private void numFreeRoom() {
        roomService.numFreeRoom();
    }

    private void sortRoom(String parameter) {
        roomService.sort(parameter);
    }

    private void sortOrder(String parameter) {
        orderService.sort(parameter);
    }

    private void showOrder() {
        orderService.showOrder();
    }

    private void showRoom(String parameter) {
        roomService.show(parameter);
    }

    private void showAfterDate(LocalDate freeDate) {
        roomService.showAfterDate(freeDate);
    }

    private void showGuestRoom(int index) {
        orderService.showGuestRoom(index);
    }

    private void showGuest() {
        guestService.show();
    }

    private void showAttendance(int index) {
        orderService.showAttendance(index);
    }

    private void addAttendance(int orderIndex, int attendanceIndex) {
        orderService.addAttendance(orderIndex, attendanceIndex);
    }
}
