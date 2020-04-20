package com.senlainc.git_courses.java_training.petushok_valiantsin.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class Hotel {

    private final IAttendanceService attendanceService;
    private final IGuestService guestService;
    private final IRoomService roomService;
    private final IOrderService orderService;

    @Autowired
    public Hotel(IAttendanceService attendanceService, IGuestService guestService, IRoomService roomService, IOrderService orderService) {
        this.attendanceService = attendanceService;
        this.guestService = guestService;
        this.roomService = roomService;
        this.orderService = orderService;
    }

    /**
     * Attendance
     */
    public void addAttendance(String name, String section, double price) {
        attendanceService.add(name, section, price);
    }

    public List<String> showAttendance() {
        return createStringList(attendanceService.getAttendanceList());
    }

    public void changePriceAttendance(long index, double price) {
        attendanceService.changePrice(index, price);
    }

    /**
     * Room
     */
    public void addRoom(int number, String classification, short roomNumber, short capacity, double price) {
        roomService.add(number, classification, roomNumber, capacity, price);
    }

    public void changePriceRoom(long index, double price) {
        roomService.changePrice(index, price);
    }

    public void changeStatusRoom(long index, String status) {
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
        return createStringList(guestService.getGuestList());
    }

    /**
     * Order
     */
    public void addOrder(long guestIndex, long roomIndex, LocalDate endDate) {
        orderService.add(guestIndex, roomIndex, endDate);
    }

    public void deleteOrder(long orderIndex) {
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

    public List<String> showGuestRoom(long index) {
        return createStringList(orderService.showGuestRoom(index));
    }

    public List<String> showOrderAttendance(long index) {
        return createStringList(orderService.showAttendance(index));
    }

    public void addOrderAttendance(long orderIndex, long attendanceIndex) {
        orderService.addAttendance(orderIndex, attendanceIndex);
    }

    private <T> List<String> createStringList(List<T> list) {
        final List<String> stringList = new ArrayList<>();
        list.forEach(i -> stringList.add(i.toString()));
        return stringList;
    }
}
