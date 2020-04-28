package com.senlainc.git_courses.java_training.petushok_valiantsin.api.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface IOrderService extends IGenericService<Order, Long> {

    void addAttendance(long orderIndex, long attendanceIndex);

    List<Attendance> getAttendances(long orderIndex);

    List<Room> getGuestRooms(long index);

    List<Room> getRoomsAfterDate(LocalDate date, int firstElement, int maxResult);

    List<Order> readAllSorted(String parameter, int firstElement, int maxResult);
}
