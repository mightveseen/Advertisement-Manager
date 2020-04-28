package com.senlainc.git_courses.java_training.petushok_valiantsin.api.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface IOrderService {

    void create(long guestIndex, long roomIndex, LocalDate endDate);

    void delete(long index);

    List<Room> getGuestRooms(long index);

    void addAttendance(long orderIndex, long attendanceIndex);

    List<Attendance> getAttendances(long orderIndex);

    List<Room> getRoomsAfterDate(LocalDate date);

    Order getOrder(long index);

    List<Order> getOrders();

    List<Order> getSortedOrders(String parameter);
}
