package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.OrderStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.ConnectionManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotFoundException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.EntityNotAvailableException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.EntityNotFoundException;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@DependencyClass
@DependencyPrimary
public class OrderService implements IOrderService {

    @DependencyComponent
    private IOrderDao orderDao;
    @DependencyComponent
    private IRoomDao roomDao;
    @DependencyComponent
    private IGuestDao guestDao;
    @DependencyComponent
    private IAttendanceDao attendanceDao;
    @DependencyComponent
    private IRoomService roomService;
    @DependencyComponent
    private ConnectionManager connectionManager;

    @Override
    public void add(int guestIndex, int roomIndex, LocalDate endDate) {
        final Room room = roomDao.read(roomIndex);
        final Guest guest = guestDao.read(guestIndex);
        if (room.getStatus().equals(RoomStatus.RENTED) || room.getStatus().equals(RoomStatus.SERVED)) {
            throw new EntityNotAvailableException("Room now is not available");
        }
        orderDao.create(new Order(guest, room, endDate, room.getPrice()));
        roomService.changeStatus(roomIndex, RoomStatus.RENTED.name());
    }

    @Override
    public void delete(int index) {
        try {
            final Order order = orderDao.read(index);
            order.setStatus(OrderStatus.DISABLED);
            order.setEndDate(LocalDate.now());
            orderDao.update(order);
            roomService.changeStatus(order.getRoom().getId(), RoomStatus.FREE.name());
        } catch (ElementNotFoundException e) {
            throw new ElementNotFoundException("Order with index: " + index + " dont exists.", e);
        }
    }

    @Override
    public List<Order> getOrderList() {
        return orderDao.readAll();
    }

    @Override
    public List<Room> showGuestRoom(int index) {
        return orderDao.readLastRoom(index);
    }

    @Override
    public List<Room> showAfterDate(LocalDate date) {
        final List<Room> roomList = roomDao.readAllFree();
        roomList.addAll(orderDao.readAfterDate(date));
        return roomList;
    }

    @Override
    public List<Attendance> showAttendance(int orderIndex) {
        try {
            return orderDao.read(orderIndex).getAttendanceIndex();
        } catch (ElementNotFoundException e) {
            throw new EntityNotFoundException("Order with index: " + orderIndex + " dont have order.", e);
        }
    }

    @Override
    public void addAttendance(int orderIndex, int attendanceIndex) {
        try {
            final Order order = orderDao.read(orderIndex);
            final Attendance attendance = attendanceDao.read((long) attendanceIndex);
            order.setPrice(order.getPrice() + attendance.getPrice());
            orderDao.update(order);
            orderDao.update(order, attendance);
            connectionManager.commit();
        } catch (ElementNotFoundException e) {
            connectionManager.rollback();
            throw new ElementNotFoundException("Failed to add attendance", e);
        }
    }

    @Override
    public List<Order> sort(String parameter) {
        final List<Order> myList = orderDao.readAll();
        switch (parameter) {
            case "date":
                sortByDate(myList);
                break;
            case "alphabet":
                sortByAlphabet(myList);
                break;
            default:
                break;
        }
        return myList;
    }

    private void sortByDate(List<Order> myList) {
        myList.sort(Comparator.comparing(Order::getEndDate));
    }

    private void sortByAlphabet(List<Order> myList) {
        myList.sort(Comparator.comparing(i -> i.getGuest().getFirstName()));
    }
}
