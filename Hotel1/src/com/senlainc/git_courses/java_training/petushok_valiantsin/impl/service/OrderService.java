package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IOrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status.Free;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status.Rented;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status.Served;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository.OrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.utility.MyList;

import java.time.LocalDate;
import java.util.Comparator;

public class OrderService implements IOrderService {
    private IOrderDao orderDao = new OrderDao();
    private IRoomService roomService;
    private IGuestService guestService;
    private IAttendanceService attendanceService;

    private Comparator<Order> SORT_BY_DATE = new Comparator<>() {
        @Override
        public int compare(Order firstOrder, Order lastOrder) {
            return firstOrder.getEndDate().compareTo(lastOrder.getEndDate());
        }
    };

    private Comparator<Guest> SORT_BY_GUEST = new Comparator<>() {
        @Override
        public int compare(Guest firstGuest, Guest lastGuest) {
            return firstGuest.getFirstName().compareTo(lastGuest.getFirstName());
        }
    };

    public OrderService(IRoomService roomService, IGuestService guestService, IAttendanceService attendanceService) {
        this.guestService = guestService;
        this.roomService = roomService;
        this.attendanceService = attendanceService;
    }

    @Override
    public void add(Order order) {
        if (roomService.getStatus(order.getRoomIndex()) instanceof Served || roomService.getStatus(order.getRoomIndex()) instanceof Rented) {
            System.err.println("Room now is not available");
            return;
        }
        orderDao.create(order);
        orderDao.read(orderDao.readAll().size() - 1).setPrice(roomService.getPrice(order.getRoomIndex()));
        roomService.changeStatus(order.getRoomIndex(), new Rented(order.getEndDate()));
    }

    @Override
    public void delete(int index) {
        orderDao.delete(index);
        roomService.changeStatus(index, new Free());
    }

    @Override
    public void changeEndDate(int index, LocalDate date) {
        Order order = new Order(orderDao.read(index));
        order.setEndDate(date);
        orderDao.update(index, order);
    }

    @Override
    public void showOrder() {
        for (int i = 0; i < orderDao.readAll().size(); i++) {
            System.out.println(guestService.getGuest(orderDao.read(i).getGuestIndex()) + "\t" + roomService.getRoom(orderDao.read(i).getRoomIndex())
                    + "\nTotal amount: " + orderDao.read(i).getPrice());
        }
    }

    @Override
    public void showGuestRoom(int index) {
        int counter = 0;
        for (int i = 0; i < orderDao.readAll().size(); i++) {
            if (orderDao.read(i).getGuestIndex() == index) {
                System.out.println(guestService.getGuest(orderDao.read(i).getGuestIndex()) + "\t" + roomService.getRoom(orderDao.read(i).getRoomIndex()));
                counter++;
            }
            if (counter == 3) {
                return;
            }
        }
    }

    @Override
    public void addAttendance(int orderIndex, int attendanceIndex) {
        Order order = new Order(orderDao.read(orderIndex));
        MyList<Integer> myList = new MyList<>();
        System.arraycopy(myList.get(), 0, order.getAttendanceIndex().get(), 0, order.getAttendanceIndex().size());
        myList.add(attendanceIndex);
        order.setAttendanceIndex(myList);
        double price = order.getPrice() + attendanceService.getPrice(attendanceIndex);
        order.setPrice(price);
        orderDao.update(orderIndex, order);
    }

    @Override
    public void sort(String parameter) {
        switch (parameter) {
            case "alphabet":
                sortByAlphabet();
                break;
            case "date":
                sortByDate();
                break;
        }
    }

    private void sortByDate() {
        MyList<Order> myList = new MyList<>();
        for (int i = 0; i < orderDao.readAll().size(); i++) {
            myList.add(orderDao.read(i));
        }
        myList.sort(SORT_BY_DATE);
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(guestService.getGuest(myList.get(i).getGuestIndex()) + "\t" + roomService.getRoom(myList.get(i).getRoomIndex())
                    + "\nTotal amount: " + myList.get(i).getPrice());
        }
    }

    private void sortByAlphabet() {
//        orderDao.readAll().sort(SORT_BY_GUEST);
    }

    public void showAttendance(int orderIndex) {
        System.out.println(guestService.getGuest(orderDao.read(orderIndex).getGuestIndex()));
//        for(Integer index : orderDao.read(orderIndex).getAttendanceIndex()) {
//
//        }
    }
}
