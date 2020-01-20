package com.senlainc.git_courses.java_training.petushok_valiantsin.api.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface IOrderService {
    void add(int guestIndex, int roomIndex, LocalDate endDate);

    void delete(int index);

    void show();

    void show(List<Order> myList);

    void showGuestRoom(int index);

    List<Order> sort(String parameter);

    void addAttendance(int orderIndex, int attendanceIndex);

    void showAttendance(int orderIndex);

    void showAfterDate(LocalDate date);
}
