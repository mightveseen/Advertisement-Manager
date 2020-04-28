package com.senlainc.git_courses.java_training.petushok_valiantsin.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementAlreadyExistsException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotAvailableException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotFoundException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.CreateQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.DeleteQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.ReadQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.UpdateQueryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.MessageFormatMessage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hotel {

    private static final Logger LOGGER = LogManager.getLogger(Hotel.class);
    private final IAttendanceService attendanceService;
    private final IGuestService guestService;
    private final IRoomService roomService;
    private final IOrderService orderService;

    public Hotel(IAttendanceService attendanceService, IGuestService guestService, IRoomService roomService, IOrderService orderService) {
        this.attendanceService = attendanceService;
        this.guestService = guestService;
        this.roomService = roomService;
        this.orderService = orderService;
    }

    /**
     * Attendance
     */
    public void createAttendance(String name, String section, double price) {
        try {
            attendanceService.create(name, section, price);
            LOGGER.info("Add attendance in database");
        } catch (CreateQueryException e) {
            LOGGER.warn("Error while creating attendance.", e);
        }
    }

    public void deleteAttendance(long index) {
        try {
            attendanceService.delete(index);
            LOGGER.info("Delete attendance with index: {} from database", index);
        } catch (DeleteQueryException e) {
            LOGGER.warn("Error while deleting attendance.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn("Attendance with index: {} don't exists.", index, e);
        }
    }

    public List<String> showAttendance() {
        try {
            return createStringList(attendanceService.getAttendances());
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read attendance's.", e);
        }
        return Collections.emptyList();
    }

    public void changePriceAttendance(long index, double price) {
        try {
            attendanceService.changePrice(index, price);
            LOGGER.info("Change attendance price");
        } catch (UpdateQueryException e) {
            LOGGER.warn("Error while updating attendance. Update operation: change price.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn("Attendance with index {} don't exists.", index, e);
        }
    }

    /**
     * Room
     */
    public void createRoom(int number, String classification, short roomNumber, short capacity, double price) {
        try {
            roomService.create(number, classification, roomNumber, capacity, price);
            LOGGER.info("Add room in database");
        } catch (CreateQueryException e) {
            LOGGER.warn("Error while creating room", e);
        } catch (ElementAlreadyExistsException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    public void deleteRoom(long index) {
        try {
            roomService.delete(index);
            LOGGER.info("Delete room with index: {} from database", index);
        } catch (DeleteQueryException e) {
            LOGGER.warn("Error while deleting room", e);
        } catch (ReadQueryException e) {
            LOGGER.warn("Room with index: {} don't exists.", index, e);
        }
    }

    public void changePriceRoom(long index, double price) {
        try {
            roomService.changePrice(index, price);
            LOGGER.info("Change room price");
        } catch (UpdateQueryException e) {
            LOGGER.warn("Error while updating room. Update operation: change price.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn("Room with index {} don't exists.", index, e);
        }
    }

    public void changeStatusRoom(long index, String status) {
        try {
            roomService.changeStatus(index, status);
            LOGGER.info("Change room status");
        } catch (UpdateQueryException e) {
            LOGGER.warn("Error while updating room. Update operation: change status.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn("Room with index {} don't exists.", index, e);
        } catch (ElementNotAvailableException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    public List<String> sortRoom(String type, String parameter) {
        try {
            return createStringList(roomService.getSortedRooms(type, parameter));
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read room's.", e);
        }
        return Collections.emptyList();
    }

    public List<String> showRoom(String parameter) {
        try {
            return createStringList(roomService.getRooms(parameter));
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read room's.", e);
        }
        return Collections.emptyList();
    }

    public String numFreeRoom() {
        try {
            final String result = "Number of free room: " + roomService.getNumFree();
            LOGGER.info("Show umber of free room");
            return result;
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read room's. Read operation: number of free room's.", e);
        }
        return "Error";
    }

    /**
     * Guest
     */
    public void createGuest(String firstName, String lastName, LocalDate birthday) {
        try {
            guestService.create(firstName, lastName, birthday);
            LOGGER.info("Add guest in database");
        } catch (CreateQueryException e) {
            LOGGER.warn("Error while creating guest", e);
        } catch (ElementNotAvailableException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    public void deleteGuest(long index) {
        try {
            guestService.delete(index);
            LOGGER.info("Delete guest with index: {} from database", index);
        } catch (DeleteQueryException e) {
            LOGGER.warn("Error while deleting guest.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn("Guest with index: {} don't exists.", index, e);
        }
    }

    public String numGuest() {
        try {
            final String result = "Number of guest: " + guestService.getNum();
            LOGGER.info("Show number of guest");
            return result;
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read guest's. Read operation: number of guest's", e);
        }
        return "Error";
    }

    public List<String> showGuest() {
        try {
            return createStringList(guestService.getGuests());
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read all guest's.", e);
        }
        return Collections.emptyList();
    }

    /**
     * Order
     */
    public void createOrder(long guestIndex, long roomIndex, LocalDate endDate) {
        try {
            orderService.create(guestIndex, roomIndex, endDate);
            LOGGER.info("Add order in database");
        } catch (CreateQueryException e) {
            LOGGER.warn("Error while creating order.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn(new MessageFormatMessage("Room with index {0} or Guest with index: {1} don't exists.",
                    guestIndex, roomIndex), e);
        } catch (ElementNotAvailableException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    public void deleteOrder(long orderIndex) {
        try {
            orderService.delete(orderIndex);
            LOGGER.info("Delete order from database");
        } catch (DeleteQueryException e) {
            LOGGER.warn("Error while deleting order.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn("Order with index {} don't exists.", orderIndex, e);
        } catch (ElementNotAvailableException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    public List<String> sortOrder(String parameter) {
        try {
            return createStringList(orderService.getSortedOrders(parameter));
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read all order's.", e);
        }
        return Collections.emptyList();
    }

    public List<String> showOrder() {
        try {
            return createStringList(orderService.getOrders());
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read all order's.", e);
        }
        return Collections.emptyList();
    }

    public List<String> showAfterDate(LocalDate freeDate) {
        try {
            final List<String> result = createStringList(orderService.getRoomsAfterDate(freeDate));
            LOGGER.info("Show room's will be available after: {}", freeDate);
            return result;
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read all order's. Read operation: room's will be available after date.", e);
        }
        return Collections.emptyList();
    }

    public List<String> showGuestRoom(long index) {
        try {
            final List<String> result = createStringList(orderService.getGuestRooms(index));
            LOGGER.info("Show last 3 room's of guest");
            return result;
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read all order's. Read operation: room's of guest.", e);
        }
        return Collections.emptyList();
    }

    public List<String> showOrderAttendance(long index) {
        try {
            final List<String> result = createStringList(orderService.getAttendances(index));
            LOGGER.info("Show guest attendance's");
            return result;
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read order. Read operation: show order attendance's.", e);
        } catch (ElementNotFoundException e) {
            LOGGER.warn(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    public void addOrderAttendance(long orderIndex, long attendanceIndex) {
        try {
            orderService.addAttendance(orderIndex, attendanceIndex);
            LOGGER.info("Add attendance to order");
        } catch (UpdateQueryException e) {
            LOGGER.warn("Error while updating attendance. Update operation: add attendance to order.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn(new MessageFormatMessage("Order with index {0} or Attendance with index: " +
                    "{1} don't exists.", orderIndex, attendanceIndex), e);
        } catch (ElementNotAvailableException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    private <T> List<String> createStringList(List<T> list) {
        final List<String> stringList = new ArrayList<>();
        list.forEach(i -> stringList.add(i.toString()));
        return stringList;
    }
}
