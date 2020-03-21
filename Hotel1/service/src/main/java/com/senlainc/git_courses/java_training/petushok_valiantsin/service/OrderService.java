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
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.CustomEntityManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotFoundException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.EntityNotAvailableException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.EntityNotFoundException;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@DependencyClass
@DependencyPrimary
public class OrderService implements IOrderService {

    private final EntityManager entityManager = CustomEntityManager.getEntityManager();
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

    @Override
    public void add(long guestIndex, long roomIndex, LocalDate endDate) {
        final Room room = roomDao.read(roomIndex);
        final Guest guest = guestDao.read(guestIndex);
        if (room.getStatus().equals(RoomStatus.RENTED) || room.getStatus().equals(RoomStatus.SERVED)) {
            throw new EntityNotAvailableException("Room now is not available");
        }
        entityManager.getTransaction().begin();
        orderDao.create(new Order(guest, room, endDate, room.getPrice()));
        roomService.changeStatus(roomIndex, RoomStatus.RENTED.name());
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(long index) {
        try {
            final Order order = orderDao.read(index);
            order.setStatus(OrderStatus.DISABLED);
            order.setEndDate(LocalDate.now());
            entityManager.getTransaction().begin();
            orderDao.update(order);
            roomService.changeStatus(order.getRoom().getId(), RoomStatus.FREE.name());
            entityManager.getTransaction().commit();
        } catch (ElementNotFoundException e) {
            throw new ElementNotFoundException("Order with index: " + index + " don't exists.", e);
        }
    }

    @Override
    public List<Order> getOrderList() {
        return orderDao.readAll();
    }

    @Override
    public List<Room> showGuestRoom(long index) {
        return orderDao.readLastRoom(index, 3);
    }

    @Override
    public List<Room> showAfterDate(LocalDate date) {
        final List<Room> roomList = roomDao.readAllFree();
        roomList.addAll(orderDao.readAfterDate(date));
        return roomList;
    }

    @Override
    public List<Attendance> showAttendance(long orderIndex) {
        final List<Attendance> attendanceList = orderDao.read(orderIndex).getAttendances();
        if (attendanceList == null) {
            throw new EntityNotFoundException("Order with index: " + orderIndex + " don't have order.");
        }
        return attendanceList;
    }

    @Override
    public void addAttendance(long orderIndex, long attendanceIndex) {
        try {
            final Order order = orderDao.read(orderIndex);
            final Attendance attendance = attendanceDao.read(attendanceIndex);
            final List<Attendance> attendanceList = order.getAttendances();
            attendanceList.add(attendance);
            order.setAttendances(attendanceList);
            order.setPrice(order.getPrice() + attendance.getPrice());
            entityManager.getTransaction().begin();
            orderDao.update(order);
            entityManager.getTransaction().commit();
        } catch (ElementNotFoundException e) {
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
