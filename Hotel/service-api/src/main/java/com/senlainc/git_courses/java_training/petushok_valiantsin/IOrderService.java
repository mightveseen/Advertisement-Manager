package com.senlainc.git_courses.java_training.petushok_valiantsin;

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
