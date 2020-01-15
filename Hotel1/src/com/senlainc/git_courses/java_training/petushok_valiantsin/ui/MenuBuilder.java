package com.senlainc.git_courses.java_training.petushok_valiantsin.ui;

import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.attendance.AddAttendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.guest.ShowNumberGuest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.order.AddOrder;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.order.DisableOrder;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.order.FinalPriceOrder;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.order.ShowActiveOrder;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.room.*;

public class MenuBuilder {
    private final Hotel hotel;
    private final Menu rootMenu = new Menu("Main menu");

    public MenuBuilder(Hotel hotel) {
        this.hotel = hotel;
    }

    public void buildMenu() {
        final Menu guest = new Menu("Guest menu");
        final Menu room = new Menu("Room menu");
        final Menu order = new Menu("Order menu");
        final Menu attendance = new Menu("Attendance menu");

        final Menu roomSort = new Menu("Sort room's");
        final Menu orderSort = new Menu("Sort order's");

        roomMenuInit(room, roomSort);
        orderMenuInit(order, orderSort);
        guestMenuInit(guest);
        attendanceMenuInit(attendance);
        rootMenuInit(room, guest, attendance, room);
        roomSortMenuInit(roomSort);
        orderSortMenuInit(orderSort);

        hotel.createAttendance();
        hotel.createGuest();
        hotel.createRoom();
        hotel.createOrder();
    }

    public Menu getRootMenu() {
        buildMenu();
        return this.rootMenu;
    }

    private void rootMenuInit(Menu room, Menu guest, Menu attendance, Menu order) {
        rootMenu.addItem(new MenuItem("Room", room));
        rootMenu.addItem(new MenuItem("Guest", guest));
        rootMenu.addItem(new MenuItem("Attendance", attendance));
        rootMenu.addItem(new MenuItem("Order", order));
    }

    private void roomMenuInit(Menu room, Menu roomSort) {
        room.addItem(new MenuItem("Add Room", new AddRoom(), rootMenu));
        room.addItem(new MenuItem("Change price", new ChangePriceRoom(), rootMenu));
        room.addItem(new MenuItem("Change status", new ChangeStatusRoom(), rootMenu));
        room.addItem(new MenuItem("Show after date", new ShowDateRoom(), rootMenu));
        room.addItem(new MenuItem("Show number free room's", new ShowNumberFreeRoom(), rootMenu));
        room.addItem(new MenuItem("Show room", roomSort));
        room.addItem(new MenuItem("Show free room", roomSort));
    }

    private void guestMenuInit(Menu guest) {
        guest.addItem(new MenuItem("Show number guest", new ShowNumberGuest(), rootMenu));
    }

    private void attendanceMenuInit(Menu attendance) {
        attendance.addItem(new MenuItem("Add attendance", new AddAttendance(), rootMenu));
        attendance.addItem(new MenuItem("Change price", new ChangePriceRoom(), rootMenu));
    }

    private void orderMenuInit(Menu order, Menu orderSort) {
        order.addItem(new MenuItem("Add order", new AddOrder(), rootMenu));
        order.addItem(new MenuItem("Disable order", new DisableOrder(), rootMenu));
        order.addItem(new MenuItem("Final price", new FinalPriceOrder(), rootMenu));
        order.addItem(new MenuItem("Show order's", orderSort));
    }

    private void roomSortMenuInit(Menu roomSort) {
        roomSort.addItem(new MenuItem("Sort by price", new ShowRoom("price"), rootMenu));
        roomSort.addItem(new MenuItem("Sort by classification", new ShowRoom("classification"), rootMenu));
        roomSort.addItem(new MenuItem("Sort by room number", new ShowRoom("room number"), rootMenu));
    }

    private void orderSortMenuInit(Menu orderSort) {
        orderSort.addItem(new MenuItem("Sort by date", new ShowActiveOrder("date"), rootMenu));
        orderSort.addItem(new MenuItem("Sort by alphabet", new ShowActiveOrder("alphabet"), rootMenu));
    }
}
