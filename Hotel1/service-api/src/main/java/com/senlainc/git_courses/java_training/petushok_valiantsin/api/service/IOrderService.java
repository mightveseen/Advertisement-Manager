package com.senlainc.git_courses.java_training.petushok_valiantsin.api.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface IOrderService {

    void add(int guestIndex, int roomIndex, LocalDate endDate);

    void delete(int index);

    List<Order> getOrderList();

    List<Room> showGuestRoom(int index);

    List<Order> sort(String parameter);

    void addAttendance(int orderIndex, int attendanceIndex);

    List<Attendance> showAttendance(int orderIndex);

    List<Room> showAfterDate(LocalDate date);
}
