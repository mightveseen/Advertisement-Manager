package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status.Free;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status.Rented;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status.Served;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository.AttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository.GuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository.OrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository.RoomDao;

import java.time.LocalDate;

public class OrderService implements IOrderService {
    private IOrderDao orderDao = new OrderDao();
    private IRoomService roomService;
    private IGuestService guestService;
    private IAttendanceService attendanceService;

    public OrderService(IRoomService roomService, IGuestService guestService, IAttendanceService attendanceService) {
        this.guestService = guestService;
        this.roomService = roomService;
        this.attendanceService = attendanceService;
    }

    @Override
    public void add(Order order) {
        if (roomService.getStatus(order.getRoomIndex()) instanceof Served || roomService.getStatus(order.getRoomIndex()) instanceof Rented) {
            System.err.println("Room now is not available");
            return;
        }
        orderDao.create(order);
        roomService.changeStatus(order.getRoomIndex(), new Rented(order.getEndDate()));
    }

    @Override
    public void delete(int index) {
        orderDao.delete(index);
        roomService.changeStatus(index, new Free());
    }

    @Override
    public void changeEndDate(int index, LocalDate date) {
        Order order = new Order(orderDao.read(index));
        order.setEndDate(date);
        orderDao.update(index, order);
    }

    @Override
    public void showOrder() {
        for(int i = 0; i < orderDao.readAll().size(); i++) {
            System.out.println(orderDao.read(i));
        }
    }
}
