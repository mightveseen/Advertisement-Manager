package com.senlainc.git_courses.java_training.petushok_valiantsin.ui;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Status;

import java.time.LocalDate;
import java.util.List;

public class Hotel {
    private static Hotel instance;
    private final IGuestService guestService;
    private final IRoomService roomService;
    private final IAttendanceService attendanceService;
    private final IOrderService orderService;

    private Hotel(IGuestService guestService, IRoomService roomService, IAttendanceService attendanceService, IOrderService orderService) {
        this.guestService = guestService;
        this.roomService = roomService;
        this.attendanceService = attendanceService;
        this.orderService = orderService;
    }

    public static void setInstance(IGuestService guestService, IRoomService roomService, IAttendanceService attendanceService, IOrderService orderService) {
        instance = new Hotel(guestService, roomService, attendanceService, orderService);
    }

    public static Hotel getInstance() {
        return instance;
    }

    /** Attendance */
    public void createAttendance() {
        attendanceService.add("Lunch", "Food", 12.3);
        attendanceService.add("Dinner", "Food", 9);
        attendanceService.add("Breakfast", "Food", 9);
    }

    public void addAttendance(String name, String section, double price) {
        attendanceService.add(name, section, price);
    }

    public void showAttendance() {
        attendanceService.showAttendance();
    }

    public void changePriceAttendance(int index, double price) {
        attendanceService.changePrice(index, price);
    }

    /** Room */
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

    public void changeStatusRoom(int index, Status.RoomStatus status) {
        roomService.changeStatus(index, status);
    }

    public void sortRoom(String type, String parameter) {
        List<Room> myList = roomService.sort(parameter);
        roomService.show(type, myList);
    }

    public void showRoom(String parameter) {
        roomService.show(parameter, roomService.getRoomList());
    }

    public void numFreeRoom() {
        roomService.numFreeRoom();
    }

    /** Guest */
    public void createGuest() {
        guestService.add("Victoria", "July", LocalDate.of(1986, 5, 12), "+1532521678");
        guestService.add("Robert", "Johnson", LocalDate.of(1967, 12, 1), "+278392386");
        guestService.add("Daniel", "Blake", LocalDate.of(1971, 1, 24), "+1532521678");
    }

    public void addGuest(String firstName, String lastName, LocalDate birthday, String infoContact) {
        guestService.add(firstName, lastName, birthday, infoContact);
    }

    public void numGuest() {
        guestService.num();
    }

    public void showGuest() {
        guestService.show();
    }

    /** Order */
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

    public void sortOrder(String parameter) {
        List<Order> myList = orderService.sort(parameter);
        orderService.show(myList);
    }

    public void showOrder() {
        orderService.show();
    }

    public void showAfterDate(LocalDate freeDate) {
        orderService.showAfterDate(freeDate);
    }

    public void showGuestRoom(int index) {
        orderService.showGuestRoom(index);
    }

    public void showOrderAttendance(int index) {
        orderService.showAttendance(index);
    }

    public void addOrderAttendance(int orderIndex, int attendanceIndex) {
        orderService.addAttendance(orderIndex, attendanceIndex);
    }
}
