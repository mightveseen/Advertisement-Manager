package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.OrderStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
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
            throw new NullPointerException("Room now is not available");
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
        } catch (NullPointerException e) {
            throw new NullPointerException("Order with index: " + index + " dont exists.");
        }
    }

    @Override
    public void show() {
        orderDao.readAll().forEach(this::show);
    }

    @Override
    public void show(List<Order> myList) {
        myList.forEach(this::show);
    }

    private void show(Order order) {
        System.out.println("Order index: " + order.getId() + "\n" +
                guestService.getGuest(order.getGuestIndex()) + "\n" +
                roomService.getRoom(order.getRoomIndex()) + "\nStart date: " +
                order.getStartDate() + "\nEnd date: " +
                order.getEndDate() + "\nTotal amount: " +
                order.getPrice() + "\nStatus: " +
                order.getStatus());
    }

    @Override
    public void showGuestRoom(int index) {
        final List<Order> orderList = orderDao.readAll().stream().filter(i -> i.getGuestIndex() == index).limit(3).collect(Collectors.toList());
        for (Order order : orderList) {
            System.out.println(guestService.getGuest(order.getGuestIndex()) + "\n" + roomService.getRoom(order.getRoomIndex()));
        }
    }

    @Override
    public void showAfterDate(LocalDate date) {
        System.out.println("Room will be available after [" + date + "]:");
        final List<Order> orderList = orderDao.readAll().stream().filter(i -> i.getEndDate().isBefore(date) && i.getStatus().equals(OrderStatus.ACTIVE)).collect(Collectors.toList());
        for (Order order : orderList) {
            System.out.println(roomService.getRoom(order.getRoomIndex()) + " - End date: ["
                    + order.getEndDate() + "]");
        }
        roomService.getRoomList().stream().filter(i -> i.getStatus().equals(RoomStatus.FREE)).collect(Collectors.toList()).forEach(System.out::println);
    }

    @Override
    public void addAttendance(int orderIndex, int attendanceIndex) {
        try {
            Order order = new Order(orderDao.read(orderIndex));
            final List<Integer> myList = new LinkedList<>(order.getAttendanceIndex());
            myList.add(attendanceIndex);
            order.setAttendanceIndex(myList);
            final double price = order.getPrice() + attendanceService.getPrice(attendanceIndex);
            order.setPrice(price);
            orderDao.update(order);
        } catch (NullPointerException e) {
            throw new NullPointerException("Failed to add attendance");
        }
    }

    @Override
    public List<Order> sort(String parameter) {
        List<Order> myList = new ArrayList<>(orderDao.readAll());
        switch (parameter) {
            case "date":
                sortByDate(myList);
                return myList;
            case "alphabet":
                sortByAlphabet(myList);
                return myList;
        }
        return myList;
    }

    private void sortByDate(List<Order> myList) {
        myList.sort(SORT_BY_DATE);
    }

    private void sortByAlphabet(List<Order> myList) {
        myList.clear();
        for (int index : guestService.sortByAlphabet()) {
            myList.add(orderDao.readAll().stream().filter(i -> i.getGuestIndex() == index).findFirst().orElseThrow(NullPointerException::new));
        }
    }

    public void showAttendance(int guestIndex) {
        try {
            List<Integer> attendanceIndexList = orderDao.readAll().stream().filter(i -> i.getGuestIndex() == guestIndex).findFirst().orElseThrow(NullPointerException::new).getAttendanceIndex();
            System.out.println(guestService.getGuest(guestIndex));
            for (Object index : attendanceIndexList) {
                if (index == null) {
                    break;
                }
                System.out.println(attendanceService.get((int) index));
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("This guest didn't have attendance's");
        }

    }
}
