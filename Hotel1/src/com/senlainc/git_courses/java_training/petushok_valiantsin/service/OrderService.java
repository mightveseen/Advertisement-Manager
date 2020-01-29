package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.OrderStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService implements IOrderService {
    private final IOrderDao orderDao;
    private final IRoomService roomService;
    private final IGuestService guestService;
    private final IAttendanceService attendanceService;
    private final Comparator<Order> SORT_BY_DATE = Comparator.comparing(Order::getEndDate);

    public OrderService(IOrderDao orderDao, IRoomService roomService, IGuestService guestService, IAttendanceService attendanceService) {
        this.orderDao = orderDao;
        this.guestService = guestService;
        this.roomService = roomService;
        this.attendanceService = attendanceService;
    }

    @Override
    public void add(int guestIndex, int roomIndex, LocalDate endDate) {
        if (roomService.getRoom(roomIndex).getStatus().equals(RoomStatus.RENTED) || roomService.getRoom(roomIndex).getStatus().equals(RoomStatus.SERVED)) {
            throw new ArrayIndexOutOfBoundsException("Room now is not available");
        }
        orderDao.create(new Order(guestIndex, roomIndex, endDate));
        orderDao.read(orderDao.readAll().size()).setPrice(roomService.getRoom(roomIndex).getPrice());
        roomService.changeStatus(roomIndex, RoomStatus.RENTED);
    }

    @Override
    public void delete(int index) {
        try {
            orderDao.read(index).setStatus(OrderStatus.DISABLED);
            roomService.changeStatus(index, RoomStatus.FREE);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Order with index: " + index + " dont exists.", e);
        }
    }

    @Override
    public List<String> show() {
        return createStringList(orderDao.readAll());
    }

    @Override
    public List<String> show(List<Order> myList) {
        return createStringList(myList);
    }

    private String show(Order order) {
        return "Order index: " + order.getId() + "\n" +
                guestService.getGuest(order.getGuestIndex()) + "\n" +
                roomService.getRoom(order.getRoomIndex()) + "\nStart date: " +
                order.getStartDate() + "\nEnd date: " +
                order.getEndDate() + "\nTotal amount: " +
                order.getPrice() + "\nStatus: " +
                order.getStatus().toString();
    }

    @Override
    public List<String> showGuestRoom(int index) {
        final List<String> guestRoomList = new ArrayList<>();
        final List<Order> orderList = orderDao.readAll().stream().filter(i -> i.getGuestIndex() == index).limit(3).collect(Collectors.toList());
        for (Order order : orderList) {
            guestRoomList.add(guestService.getGuest(order.getGuestIndex()) + "\n" + roomService.getRoom(order.getRoomIndex()));
        }
        return guestRoomList;
    }

    @Override
    public List<String> showAfterDate(LocalDate date) {
        final List<Order> orderList = orderDao.readAll().stream().filter(i -> i.getEndDate().isBefore(date) && i.getStatus().equals(OrderStatus.ACTIVE)).collect(Collectors.toList());
        final List<Room> roomList = roomService.getRoomList().stream().filter(i -> i.getStatus().equals(RoomStatus.FREE)).collect(Collectors.toList());
        final List<String> finalList = new ArrayList<>();
        for (Order order : orderList) {
            finalList.add(roomService.getRoom(order.getRoomIndex()).toString());
        }
        for(Room room : roomList) {
            finalList.add(room.toString());
        }
        return finalList;
    }

    @Override
    public void addAttendance(int orderIndex, int attendanceIndex) {
        try {
            Order order = orderDao.read(orderIndex);
            final List<Integer> myList = new LinkedList<>(order.getAttendanceIndex());
            myList.add(attendanceIndex);
            order.setAttendanceIndex(myList);
            final double price = order.getPrice() + attendanceService.getPrice(attendanceIndex);
            order.setPrice(price);
            orderDao.update(order);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Failed to add attendance", e);
        }
    }

    @Override
    public List<Order> sort(String parameter) {
        List<Order> myList = orderDao.readAll();
        switch (parameter) {
            case "date":
                sortByDate(myList);
                break;
            case "alphabet":
                sortByAlphabet(myList);
                break;
        }
        return myList;
    }

    private void sortByDate(List<Order> myList) {
        myList.sort(SORT_BY_DATE);
    }

    private void sortByAlphabet(List<Order> myList) {
        myList.clear();
        try {
            for (int index : guestService.sortByAlphabet()) {
                myList.add(orderDao.readAll().stream().filter(i -> i.getGuestIndex() == index).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("List is empty", e);
        }
    }

    @Override
    public List<String> showAttendance(int guestIndex) {
        List<String> finalList = new ArrayList<>();
        List<Integer> attendanceIndexList;
        try {
             attendanceIndexList = orderDao.readAll().stream().filter(i -> i.getGuestIndex() == guestIndex).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new).getAttendanceIndex();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("This guest didn't have attendance's", e);
        }
        for (Object index : attendanceIndexList) {
            finalList.add(attendanceService.get((int) index).toString());
        }
        return finalList;
    }

    private List<String> createStringList(List<Order> orderList) {
        List<String> stringList = new ArrayList<>();
        for(Order order : orderList) {
            stringList.add(show(order));
        }
        return stringList;
    }
}
