package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Status;

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
    public void add(Order order) {
        if (roomService.getStatus(order.getRoomIndex()).equals(Status.RoomStatus.RENTED) || roomService.getStatus(order.getRoomIndex()).equals(Status.RoomStatus.SERVED)) {
            System.err.println("Room now is not available");
            return;
        }
        orderDao.create(order);
        orderDao.read(orderDao.readAll().size()).setPrice(roomService.getPrice(order.getRoomIndex()));
        roomService.changeStatus(order.getRoomIndex(), Status.RoomStatus.RENTED);
    }

    @Override
    public void delete(int index) {
        if (orderDao.readAll().size() < index) {
            System.err.println("Order with index: " + index + " dont exists.");
            return;
        }
        orderDao.delete(index);
        roomService.changeStatus(index, Status.RoomStatus.FREE);
    }

    @Override
    public void show() {
        for (int i = 1; i <= orderDao.readAll().size(); i++) {
            System.out.println(guestService.getGuest(orderDao.read(i).getGuestIndex()) + "\n" + roomService.getRoom(orderDao.read(i).getRoomIndex())
                    + "\nTotal amount: " + orderDao.read(i).getPrice());
        }
    }

    @Override
    public void show(List<Order> myList) {
        for (Order order : myList) {
            System.out.println(guestService.getGuest(order.getGuestIndex()) + "\n" + roomService.getRoom(order.getRoomIndex())
                    + "\nTotal amount: " + order.getPrice());
        }
    }

    @Override
    public void showGuestRoom(int index) {
        final List<Order> myList = orderDao.readAll().stream().filter(i -> i.getGuestIndex() == index).limit(3).collect(Collectors.toList());
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(guestService.getGuest(myList.get(i).getGuestIndex()) + "\n" + roomService.getRoom(myList.get(i).getRoomIndex()));
        }
    }

    @Override
    public void showAfterDate(LocalDate date) {
        System.out.print("\n\nRoom will be available after [" + date + "]:");
        for (int i = 1; i <= roomService.getSize(); i++) {
            if (roomService.getStatus(i).equals(Status.RoomStatus.RENTED) && i <= orderDao.readAll().size()) {
                if (date.isAfter(orderDao.read(i).getEndDate())) {
                    System.out.print(roomService.getRoom(i) + " - End date: ["
                            + orderDao.read(i).getEndDate() + "]");
                    continue;
                }
            }
            if (roomService.getStatus(i).equals(Status.RoomStatus.FREE)) {
                System.out.print(roomService.getRoom(i));
            }
        }
    }

    @Override
    public void addAttendance(int orderIndex, int attendanceIndex) {
        Order order = new Order(orderDao.read(orderIndex));
        List<Integer> myList = new LinkedList<>(order.getAttendanceIndex());
        myList.add(attendanceIndex);
        order.setAttendanceIndex(myList);
        double price = order.getPrice() + attendanceService.getPrice(attendanceIndex);
        order.setPrice(price);
        orderDao.update(order);
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
        return null;
    }

    private void sortByDate(List<Order> myList) {
        myList.sort(SORT_BY_DATE);
    }

    private void sortByAlphabet(List<Order> myList) {
        int[] guestIndex = guestService.sortByAlphabet();
        for (int index : guestIndex) {
            myList.add(orderDao.readAll().stream().filter(i -> i.getGuestIndex() == index).findFirst().orElse(null));
        }
    }

    public void showAttendance(int orderIndex) {
        try {
            Object[] attendanceIndex = orderDao.read(orderIndex).getAttendanceIndex().toArray();
            System.out.print(guestService.getGuest(orderDao.read(orderIndex).getGuestIndex()));
            for (Object index : attendanceIndex) {
                if (index == null) {
                    break;
                }
                System.out.print(attendanceService.get((int) index));
            }
        } catch (NullPointerException e) {
            System.out.println("This guest didn't have attendance's");
        }

    }
}
