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
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.data.MaxResult;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.CreateQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.DeleteQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.ReadQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.UpdateQueryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.MessageFormatMessage;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@DependencyClass
@DependencyPrimary
public class OrderService implements IOrderService {

    private static final Logger LOGGER = LogManager.getLogger(OrderService.class);
    private final EntityManager entityManager = CustomEntityManager.getEntityManager();
    @DependencyComponent
    private IRoomDao roomDao;
    @DependencyComponent
    private IGuestDao guestDao;
    @DependencyComponent
    private IAttendanceDao attendanceDao;
    @DependencyComponent
    private IOrderDao orderDao;
    @DependencyComponent
    private IRoomService roomService;

    @Override
    public void add(long guestIndex, long roomIndex, LocalDate endDate) {
        try {
            final RoomStatus roomStatus = roomDao.readStatus(roomIndex);
            if (roomStatus.equals(RoomStatus.RENTED) || roomStatus.equals(RoomStatus.SERVED)) {
                LOGGER.info("Room with index: {} is not available now.", roomIndex);
                return;
            }
            final Room room = roomDao.read(roomIndex);
            final Guest guest = guestDao.read(guestIndex);
            entityManager.getTransaction().begin();
            orderDao.create(new Order(guest, room, endDate, room.getPrice()));
            roomService.changeStatus(roomIndex, RoomStatus.RENTED.name());
            entityManager.getTransaction().commit();
            LOGGER.info("Add order in database");
        } catch (CreateQueryException e) {
            entityManager.getTransaction().rollback();
            LOGGER.warn("Error while creating order.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn(new MessageFormatMessage("Room with index {0} or Guest with index: {1} don't exists.",
                    guestIndex, roomIndex), e);
        }
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
            LOGGER.info("Delete order from database");
        } catch (DeleteQueryException e) {
            entityManager.getTransaction().rollback();
            LOGGER.warn("Error while deleting order.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn("Order with index {} don't exists.", index, e);
        }
    }

    @Override
    public List<Room> showGuestRoom(long index) {
        try {
            List<Room> guestRooms = orderDao.readLastRoom(index, 3);
            LOGGER.info("Show last 3 room's of guest");
            return guestRooms;
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read all order's. Read operation: room's of guest.", e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Room> showAfterDate(LocalDate date) {
        final int maxResult = MaxResult.ROOM.getMaxResult();
        try {
            final List<Room> rooms = roomDao.readAllFree(roomDao.readSize().intValue() - maxResult, maxResult);
            rooms.addAll(orderDao.readAfterDate(date));
            LOGGER.info("Show room's will be available after: {}", date);
            return rooms;
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read all order's. Read operation: room's will be available after date.", e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Attendance> showAttendance(long orderIndex) {
        try {
            final List<Attendance> attendances = orderDao.read(orderIndex).getAttendances();
            if (attendances == null) {
                LOGGER.info("Order with index: {} don't have attendance's.", orderIndex);
                return Collections.emptyList();
            }
            LOGGER.info("Show guest attendance's");
            return attendances;
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read order. Read operation: show order attendance's.", e);
        }
        return Collections.emptyList();
    }

    @Override
    public void addAttendance(long orderIndex, long attendanceIndex) {
        try {
            final Order order = orderDao.read(orderIndex);
            final Attendance attendance = attendanceDao.read(attendanceIndex);
            final List<Attendance> attendances = order.getAttendances();
            attendances.add(attendance);
            order.setAttendances(attendances);
            order.setPrice(order.getPrice() + attendance.getPrice());
            entityManager.getTransaction().begin();
            orderDao.update(order);
            entityManager.getTransaction().commit();
            LOGGER.info("Add attendance to order");
        } catch (UpdateQueryException e) {
            entityManager.getTransaction().rollback();
            LOGGER.warn("Error while updating attendance. Update operation: add attendance to order.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn(new MessageFormatMessage("Order with index {0} or Attendance with index: {1} don't exists.",
                    orderIndex, attendanceIndex), e);
        }
    }

    @Override
    public List<Order> getOrderList() {
        final int maxResult = MaxResult.ORDER.getMaxResult();
        try {
            return orderDao.readAll(orderDao.readSize().intValue() - maxResult, maxResult);
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read all order's.", e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Order> sort(String parameter) {
        final int maxResult = MaxResult.ORDER.getMaxResult();
        try {
            if (parameter.equals("default")) {
                return getOrderList();
            }
            return orderDao.readAll(orderDao.readSize().intValue() - maxResult, maxResult, parameter);
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read all order's.", e);
        }
        return Collections.emptyList();
    }
}
