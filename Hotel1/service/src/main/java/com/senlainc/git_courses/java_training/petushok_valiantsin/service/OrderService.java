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

    private final IOrderDao orderDao;
    private final IAttendanceService attendanceService;
    private final IGuestService guestService;
    private final IRoomService roomService;

    @Autowired
    public OrderService(IOrderDao orderDao, IGuestService guestService, IAttendanceService attendanceService, IRoomService roomService) {
        this.orderDao = orderDao;
        this.guestService = guestService;
        this.attendanceService = attendanceService;
        this.roomService = roomService;
    }

    @Override
    @Transactional
    // FIXME: Deadlock while create Order and update RoomStatus
    public void create(long guestIndex, long roomIndex, LocalDate endDate) {
        final RoomStatus roomStatus = roomService.getRoomStatus(roomIndex);
        if (roomStatus.equals(RoomStatus.RENTED) || roomStatus.equals(RoomStatus.SERVED)) {
            throw new ElementNotAvailableException("Room with index: " + roomIndex + " is not available now.");
        }
        final Room room = roomService.getRoom(roomIndex);
        final Guest guest = guestService.getGuest(guestIndex);
        final Order order = new Order(guest, room, endDate, room.getPrice());
        orderDao.create(order);
        roomService.changeStatus(roomIndex, RoomStatus.RENTED.name());
    }

    @Override
    @Transactional
    public void delete(long index) {
        final Order order = orderDao.read(index);
        if (order.getStatus().equals(OrderStatus.DISABLED)) {
            throw new ElementNotAvailableException("Order with index: " + index + " already disabled.");
        }
        order.setStatus(OrderStatus.DISABLED);
        order.setEndDate(LocalDate.now());
        orderDao.delete(order);
        roomService.changeStatus(order.getRoom().getId(), RoomStatus.FREE.name());
    }

    @Override
    @Transactional
    public void addAttendance(long orderIndex, long attendanceIndex) {
        final Order order = orderDao.read(orderIndex);
        if (order.getStatus().equals(OrderStatus.DISABLED)) {
            throw new ElementNotAvailableException("Order with index: " + orderIndex + " is disabled.");
        }
        final Attendance attendance = attendanceService.getAttendance(attendanceIndex);
        final List<Attendance> attendances = order.getAttendances();
        attendances.add(attendance);
        order.setAttendances(attendances);
        order.setPrice(order.getPrice() + attendance.getPrice());
        orderDao.update(order);
    }

    @Override
    public List<Room> getGuestRooms(long index) {
        return orderDao.readLastRoom(index, 3);
    }

    @Override
    public List<Room> getRoomsAfterDate(LocalDate date) {
        final List<Room> rooms = roomService.getRooms("free");
        rooms.addAll(orderDao.readAfterDate(date));
        return rooms;
    }

    @Override
    public List<Attendance> getAttendances(long orderIndex) {
        final List<Attendance> attendances = orderDao.read(orderIndex).getAttendances();
        if (attendances == null) {
            throw new ElementNotFoundException("Order with index: " + orderIndex + " don't have attendance's.");
        }
        return attendances;
    }

    @Override
    public Order getOrder(long index) {
        return orderDao.read(index);
    }

    @Override
    public List<Order> getOrders() {
        final int maxResult = MaxResult.ORDER.getMaxResult();
        return orderDao.readAll(orderDao.readSize().intValue() - maxResult, maxResult);
    }

    @Override
    public List<Order> getSortedOrders(String parameter) {
        final int maxResult = MaxResult.ORDER.getMaxResult();
        final int firstElement = orderDao.readSize().intValue() - maxResult;
        if (parameter.equals("default")) {
            return getOrders();
        }
        if (parameter.contains("/")) {
            final String[] parameterParse = parameter.split("/", 2);
            return orderDao.readAll(firstElement, maxResult,
                    parameterParse[0], parameterParse[1]);
        }
        return orderDao.readAll(firstElement, maxResult, parameter);
    }
}
