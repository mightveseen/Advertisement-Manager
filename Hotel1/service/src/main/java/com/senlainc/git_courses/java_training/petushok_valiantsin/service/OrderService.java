package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.OrderStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.data.MaxResult;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotAvailableException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    private final IRoomDao roomDao;
    private final IGuestDao guestDao;
    private final IAttendanceDao attendanceDao;
    private final IOrderDao orderDao;
    private final IRoomService roomService;

    @Autowired
    public OrderService(IRoomDao roomDao, IGuestDao guestDao, IAttendanceDao attendanceDao, IOrderDao orderDao, IRoomService roomService) {
        this.roomDao = roomDao;
        this.guestDao = guestDao;
        this.attendanceDao = attendanceDao;
        this.orderDao = orderDao;
        this.roomService = roomService;
    }

    @Override
    @Transactional
    public void add(long guestIndex, long roomIndex, LocalDate endDate) {
        final RoomStatus roomStatus = roomDao.readStatus(roomIndex);
        if (roomStatus.equals(RoomStatus.RENTED) || roomStatus.equals(RoomStatus.SERVED)) {
            throw new ElementNotAvailableException("Room with index: " + roomIndex + " is not available now.");
        }
        final Room room = roomDao.read(roomIndex);
        final Guest guest = guestDao.read(guestIndex);
        orderDao.create(new Order(guest, room, endDate, room.getPrice()));
        roomService.changeStatus(roomIndex, RoomStatus.RENTED.name());
    }

    @Override
    @Transactional
    public void delete(long index) {
        final Order order = orderDao.read(index);
        order.setStatus(OrderStatus.DISABLED);
        order.setEndDate(LocalDate.now());
        orderDao.update(order);
        roomService.changeStatus(order.getRoom().getId(), RoomStatus.FREE.name());
    }

    @Override
    public List<Room> showGuestRoom(long index) {
        return orderDao.readLastRoom(index, 3);
    }

    @Override
    public List<Room> showAfterDate(LocalDate date) {
        final int maxResult = MaxResult.ROOM.getMaxResult();
        final List<Room> rooms = roomDao.readAllFree(roomDao.readSize().intValue() - maxResult,
                maxResult);
        rooms.addAll(orderDao.readAfterDate(date));
        return rooms;
    }

    @Override
    public List<Attendance> showAttendance(long orderIndex) {
        final List<Attendance> attendances = orderDao.read(orderIndex).getAttendances();
        if (attendances == null) {
            throw new ElementNotFoundException("Order with index: " + orderIndex + " don't have attendance's.");
        }
        return attendances;
    }

    @Override
    @Transactional
    public void addAttendance(long orderIndex, long attendanceIndex) {
        final Order order = orderDao.read(orderIndex);
        final Attendance attendance = attendanceDao.read(attendanceIndex);
        final List<Attendance> attendances = order.getAttendances();
        attendances.add(attendance);
        order.setAttendances(attendances);
        order.setPrice(order.getPrice() + attendance.getPrice());
        orderDao.update(order);
    }

    @Override
    public List<Order> getOrderList() {
        final int maxResult = MaxResult.ORDER.getMaxResult();
        return orderDao.readAll(orderDao.readSize().intValue() - maxResult, maxResult);
    }

    @Override
    public List<Order> sort(String parameter) {
        final int maxResult = MaxResult.ORDER.getMaxResult();
        if (parameter.equals("default")) {
            return getOrderList();
        }
        if (parameter.contains("/")) {
            final String[] parameterParse = parameter.split("/", 2);
            return orderDao.readAll(orderDao.readSize().intValue() - maxResult, maxResult,
                    parameterParse[0], parameterParse[1]);
        }
        return orderDao.readAll(orderDao.readSize().intValue() - maxResult, maxResult, parameter);
    }
}
