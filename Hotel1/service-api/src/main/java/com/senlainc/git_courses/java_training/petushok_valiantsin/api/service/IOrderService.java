package com.senlainc.git_courses.java_training.petushok_valiantsin.api.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface IOrderService {

    void add(long guestIndex, long roomIndex, LocalDate endDate);

    void delete(long index);

    List<Order> getOrderList();

    List<Room> showGuestRoom(long index);

    List<Order> sort(String parameter);

    void addAttendance(long orderIndex, long attendanceIndex);

    List<Attendance> showAttendance(long orderIndex);

    List<Room> showAfterDate(LocalDate date);
}
