package com.senlainc.git_courses.java_training.petushok_valiantsin.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.repository.AttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.repository.GuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.repository.OrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.repository.RoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.service.AttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.service.GuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.service.OrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.service.RoomService;

import java.time.LocalDate;
import java.util.List;

public class Hotel {
    private static Hotel instance;
    private final IGuestService guestService;
    private final IRoomService roomService;
    private final IAttendanceService attendanceService;
    private final IOrderService orderService;

    private Hotel() {
        IGuestDao guestDao = new GuestDao();
        IRoomDao roomDao = new RoomDao();
        IAttendanceDao attendanceDao = new AttendanceDao();
        IOrderDao orderDao = new OrderDao();
        this.guestService = new GuestService(guestDao);
        this.roomService = new RoomService(roomDao);
        this.attendanceService = new AttendanceService(attendanceDao);
        this.orderService = new OrderService(orderDao, roomService, guestService, attendanceService);
    }

    public static Hotel getInstance() {
        if (instance == null) {
            instance = new Hotel();
        }
        return instance;
    }

    /**
     * Attendance
     */
    public void createAttendance() {
        attendanceService.add("Lunch", "Food", 12.3);
        attendanceService.add("Dinner", "Food", 9);
        attendanceService.add("Breakfast", "Food", 9);
    }

    public void addAttendance(String name, String section, double price) {
        attendanceService.add(name, section, price);
    }

    public List<String> showAttendance() {
        return attendanceService.showAttendance();
    }

    public void changePriceAttendance(int index, double price) {
        attendanceService.changePrice(index, price);
    }

    /**
     * Room
     */
    public void createRoom() {
        roomService.add(new Room(203, "President", (short) 4, (short) 6, 330.0));
        roomService.add(new Room(312, "Business", (short) 1, (short) 2, 170.0));
        roomService.add(new Room(401, "Lux", (short) 2, (short) 4, 230.0));
        roomService.add(new Room(105, "Lux", (short) 2, (short) 4, 30.0));
        roomService.add(new Room(506, "President", (short) 5, (short) 10, 230.0));
    }

    public void addRoom(Room room) {
        roomService.add(room);
    }

    public void changePriceRoom(int index, double price) {
        roomService.changePrice(index, price);
    }

    public void changeStatusRoom(int index, RoomStatus status) {
        roomService.changeStatus(index, status);
    }

    public List<String> sortRoom(String type, String parameter) {
        return roomService.show(type, roomService.sort(parameter));
    }

    public List<String> showRoom(String parameter) {
        return roomService.show(parameter, roomService.getRoomList());
    }

    public String numFreeRoom() {
        return roomService.numFreeRoom();
    }

    /**
     * Guest
     */
    public void createGuest() {
        guestService.add("Victoria", "July", LocalDate.of(1986, 5, 12), "+1532521678");
        guestService.add("Robert", "Johnson", LocalDate.of(1967, 12, 1), "+278392386");
        guestService.add("Daniel", "Blake", LocalDate.of(1971, 1, 24), "+1532521678");
    }

    public void addGuest(String firstName, String lastName, LocalDate birthday, String infoContact) {
        guestService.add(firstName, lastName, birthday, infoContact);
    }

    public String numGuest() {
        return guestService.num();
    }

    public List<String> showGuest() {
        return guestService.show();
    }

    /**
     * Order
     */
    public void createOrder() {
        orderService.add(2, 4, LocalDate.of(2019, 3, 12));
        orderService.add(1, 1, LocalDate.of(2019, 1, 3));
        orderService.add(3, 2, LocalDate.of(2019, 2, 23));
    }

    public void addOrder(int guestIndex, int roomIndex, LocalDate endDate) {
        orderService.add(guestIndex, roomIndex, endDate);
    }

    public void deleteOrder(int orderIndex) {
        orderService.delete(orderIndex);
    }

    public List<String> sortOrder(String parameter) {
        return orderService.show(orderService.sort(parameter));
    }

    public List<String> showOrder() {
        return orderService.show();
    }

    public List<String> showAfterDate(LocalDate freeDate) {
        return orderService.showAfterDate(freeDate);
    }

    public List<String> showGuestRoom(int index) {
        return orderService.showGuestRoom(index);
    }

    public List<String> showOrderAttendance(int index) {
        return orderService.showAttendance(index);
    }

    public void addOrderAttendance(int orderIndex, int attendanceIndex) {
        orderService.addAttendance(orderIndex, attendanceIndex);
    }
}
