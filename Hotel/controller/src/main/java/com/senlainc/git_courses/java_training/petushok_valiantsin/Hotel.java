package com.senlainc.git_courses.java_training.petushok_valiantsin;

import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.status.RoomStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@DependencyClass
public class Hotel {
    @DependencyComponent
    private IGuestService guestService;
    @DependencyComponent
    private IRoomService roomService;
    @DependencyComponent
    private IAttendanceService attendanceService;
    @DependencyComponent
    private IOrderService orderService;

    /**
     * Attendance
     */
    public void addAttendance(String name, String section, double price) {
        attendanceService.add(name, section, price);
    }

    public List<String> showAttendance() {
        return createStringList(attendanceService.getAttendanceList());
    }

    public void changePriceAttendance(int index, double price) {
        attendanceService.changePrice(index, price);
    }

    /**
     * Room
     */
    public void addRoom(int number, String classification, short roomNumber, short capacity, double price) {
        roomService.add(number, classification, roomNumber, capacity, price);
    }

    public void changePriceRoom(int index, double price) {
        roomService.changePrice(index, price);
    }

    public void changeStatusRoom(int index, RoomStatus status) {
        roomService.changeStatus(index, status);
    }

    public List<String> sortRoom(String type, String parameter) {
        return createStringList(roomService.sort(type, parameter));
    }

    public List<String> showRoom(String parameter) {
        return createStringList(roomService.getRoomList(parameter));
    }

    public String numFreeRoom() {
        return "Number of free room: " + roomService.numFreeRoom();
    }

    /**
     * Guest
     */
    public void addGuest(String firstName, String lastName, LocalDate birthday) {
        guestService.add(firstName, lastName, birthday);
    }

    public String numGuest() {
        return "Number of guest: " + guestService.num();
    }

    public List<String> showGuest() {
        return createStringList(guestService.show());
    }

    /**
     * Order
     */
    public void addOrder(int guestIndex, int roomIndex, LocalDate endDate) {
        orderService.add(guestIndex, roomIndex, endDate);
    }

    public void deleteOrder(int orderIndex) {
        orderService.delete(orderIndex);
    }

    public List<String> sortOrder(String parameter) {
        return createStringList(orderService.sort(parameter));
    }

    public List<String> showOrder() {
        return createStringList(orderService.getOrderList());
    }

    public List<String> showAfterDate(LocalDate freeDate) {
        return createStringList(orderService.showAfterDate(freeDate));
    }

    public List<String> showGuestRoom(int index) {
        return createStringList(orderService.showGuestRoom(index));
    }

    public List<String> showOrderAttendance(int index) {
        return createStringList(orderService.showAttendance(index));
    }

    public void addOrderAttendance(int orderIndex, int attendanceIndex) {
        orderService.addAttendance(orderIndex, attendanceIndex);
    }

    private <T> List<String> createStringList(List<T> list) {
        final List<String> stringList = new ArrayList<>();
        list.forEach(i -> stringList.add(i.toString()));
        return stringList;
    }
}
