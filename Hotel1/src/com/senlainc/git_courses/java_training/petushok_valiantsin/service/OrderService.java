package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.OrderStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotFoundException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.EntityNotAvailableException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.EntityNotFoundException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.sort.Sort;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.sort.SortParameter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public void add(int guestIndex, int roomIndex, LocalDate endDate) {
        if (roomDao.read(roomIndex).getStatus().equals(RoomStatus.RENTED) || roomDao.read(roomIndex).getStatus().equals(RoomStatus.SERVED)) {
            throw new EntityNotAvailableException("Room now is not available");
        }
        orderDao.create(new Order(guestDao.read(guestIndex), roomDao.read(roomIndex), endDate, roomDao.read(roomIndex).getPrice()));
        roomService.changeStatus(roomIndex, RoomStatus.RENTED);
    }

    @Override
    public void delete(int index) {
        try {
            orderDao.read(index).setStatus(OrderStatus.DISABLED);
            roomService.changeStatus(index, RoomStatus.FREE);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ElementNotFoundException("Order with index: " + index + " dont exists.", e);
        }
    }

    @Override
    public List<Order> show() {
        return orderDao.readAll();
    }

    @Override
    public List<Order> show(List<Order> myList) {
        return myList;
    }

    @Override
    public List<Room> showGuestRoom(int index) {
        final List<Room> guestRoomList = new ArrayList<>();
        final List<Order> orderList = orderDao.readAll().stream()
                .filter(i -> i.getGuest().getId() == index).limit(3)
                .collect(Collectors.toList());
        for (Order order : orderList) {
            guestRoomList.add(order.getRoom());
        }
        return guestRoomList;
    }

    @Override
    public List<Room> showAfterDate(LocalDate date) {
        final List<Order> orderList = orderDao.readAll().stream()
                .filter(i -> i.getEndDate().isBefore(date) && i.getStatus().equals(OrderStatus.ACTIVE))
                .collect(Collectors.toList());
        final List<Room> roomList = roomDao.readAll().stream()
                .filter(i -> i.getStatus().equals(RoomStatus.FREE))
                .collect(Collectors.toList());
        for (Order order : orderList) {
            roomList.add(order.getRoom());
        }
        return roomList;
    }

    @Override
    public void addAttendance(int orderIndex, int attendanceIndex) {
        try {
            final Order order = orderDao.read(orderIndex);
            final List<Integer> myList = new LinkedList<>(order.getAttendanceIndex());
            myList.add(attendanceIndex);
            order.setAttendanceIndex(myList);
            final double price = order.getPrice() + attendanceDao.read(attendanceIndex).getPrice();
            order.setPrice(price);
            orderDao.update(order);
        } catch (ArrayIndexOutOfBoundsException e) {
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
        myList.sort(Sort.ORDER.getComparator(SortParameter.DATE));
    }

    private void sortByAlphabet(List<Order> myList) {
        myList.clear();
        try {
            for (int index : sortGuestByAlphabet()) {
                orderDao.readAll().stream()
                        .filter(i -> i.getGuest().getId() == index)
                        .findFirst().ifPresent(myList::add);
            }
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("List is empty", e);
        }
    }

    private int[] sortGuestByAlphabet() {
        final List<Guest> myList = guestDao.readAll();
        myList.sort(Sort.GUEST.getComparator(SortParameter.ALPHABET));
        return getGuestIndex(myList);
    }

    private int[] getGuestIndex(List<Guest> myList) {
        final int[] guestIndex = new int[myList.size()];
        for (int i = 0; i < myList.size(); i++) {
            guestIndex[i] = myList.get(i).getId();
        }
        return guestIndex;
    }

    @Override
    public List<Attendance> showAttendance(int guestIndex) {
        final List<Attendance> list = new ArrayList<>();
        final List<Integer> attendanceIndexList;
        try {
            attendanceIndexList = orderDao.readAll().stream()
                    .filter(i -> i.getGuest().getId() == guestIndex)
                    .findFirst().orElseThrow(EntityNotFoundException::new).getAttendanceIndex();
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("This guest didn't have attendance's", e);
        }
        for (Integer index : attendanceIndexList) {
            list.add(attendanceDao.read(index));
        }
        return list;
    }
}
