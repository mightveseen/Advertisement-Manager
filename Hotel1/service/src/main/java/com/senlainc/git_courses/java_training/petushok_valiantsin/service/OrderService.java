package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.OrderStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotAvailableException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    private final IOrderDao orderDao;
    private final IAttendanceService attendanceService;
    private final IRoomService roomService;
    private final IGuestService guestService;

    @Autowired
    public OrderService(IOrderDao orderDao, IAttendanceService attendanceService, IRoomService roomService, IGuestService guestService) {
        this.orderDao = orderDao;
        this.attendanceService = attendanceService;
        this.roomService = roomService;
        this.guestService = guestService;
    }

    @Override
    @Transactional
    public void create(Order order) {
        final RoomStatus roomStatus = roomService.getRoomStatus(order.getRoom().getId());
        if (roomStatus.equals(RoomStatus.RENTED) || roomStatus.equals(RoomStatus.SERVED)) {
            throw new ElementNotAvailableException("Room with index: " + order.getRoom().getId() + " is not available now.");
        }
        final Room room = roomService.read(order.getRoom().getId());
        final Guest guest = guestService.read(order.getGuest().getId());
        order.setOrderDate(LocalDateTime.now());
        order.setStartDate(LocalDate.now());
        order.setStatus(OrderStatus.ACTIVE);
        order.setRoom(room);
        order.setGuest(guest);
        order.setAttendances(new ArrayList<>());
        order.setPrice(room.getPrice());
        orderDao.create(order);
        roomService.changeStatus(order.getRoom().getId(), RoomStatus.RENTED.name());
    }

    @Override
    @Transactional
    public void delete(Long index) {
        final Order order = orderDao.read(index);
        if (order.getStatus().equals(OrderStatus.DISABLED)) {
            throw new ElementNotAvailableException("Order with index: " + index + " already disabled.");
        }
        order.setStatus(OrderStatus.DISABLED);
        order.setEndDate(LocalDate.now());
        orderDao.update(order);
        roomService.changeStatus(order.getRoom().getId(), RoomStatus.FREE.name());
    }

    @Override
    @Transactional
    public void update(Order order) {
        orderDao.update(order);
    }

    @Override
    @Transactional
    public void addAttendance(long orderIndex, long attendanceIndex) {
        final Order order = orderDao.read(orderIndex);
        if (order.getStatus().equals(OrderStatus.DISABLED)) {
            throw new ElementNotAvailableException("Order with index: " + orderIndex + " is disabled.");
        }
        final Attendance attendance = attendanceService.read(attendanceIndex);
        final List<Attendance> attendances = order.getAttendances();
        attendances.add(attendance);
        order.setAttendances(attendances);
        order.setPrice(order.getPrice() + attendance.getPrice());
        orderDao.update(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Room> getGuestRooms(long index, int limit) {
        return orderDao.readLastRoom(index, limit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Room> getRoomsAfterDate(LocalDate date, int firstElement, int maxResult) {
        final List<Room> rooms = roomService.readAll("free", firstElement, maxResult);
        rooms.addAll(orderDao.readAfterDate(date));
        return rooms;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Attendance> getAttendances(long orderIndex) {
        final List<Attendance> attendances = orderDao.read(orderIndex).getAttendances();
        if (attendances == null) {
            throw new ElementNotFoundException("Order with index: " + orderIndex + " don't have attendance's.");
        }
        return attendances;
    }

    @Override
    @Transactional(readOnly = true)
    public Order read(Long index) {
        return orderDao.read(index);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> readAll(int firstElement, int maxResult) {
        return orderDao.readAll(firstElement, maxResult);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> readAllSorted(String parameter, int firstElement, int maxResult) {
        if (parameter.equals("default")) {
            return readAll(firstElement, maxResult);
        }
        if (parameter.contains("-")) {
            final String[] parameterParse = parameter.split("-", 2);
            return orderDao.readAll(firstElement, maxResult,
                    parameterParse[0], parameterParse[1]);
        }
        return orderDao.readAll(firstElement, maxResult, parameter);
    }
}
