package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.*;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository.*;

import java.time.LocalDate;

public class OrderService implements IOrderService {
    private IOrderDao orderDao = new OrderDao();
    private IRoomDao roomDao = new RoomDao();
    private IAttendanceDao attendanceDao = new AttendanceDao();
    private IGuestDao guestDao = new GuestDao();


    @Override
    public void add(Order order) {

    }

    @Override
    public void delete(int index) {

    }

    @Override
    public void changeEndDate(int index, LocalDate date) {
        
    }
}
