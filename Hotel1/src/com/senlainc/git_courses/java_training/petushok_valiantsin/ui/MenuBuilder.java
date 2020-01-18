package com.senlainc.git_courses.java_training.petushok_valiantsin.ui;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Status;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.attendance.AddAttendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.attendance.ChangePriceAttendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.guest.AddGuest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.guest.ShowNumberGuest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.guest.ShowThreeRoomGuest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.order.*;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.room.*;

import java.util.concurrent.atomic.AtomicInteger;

public class MenuBuilder {
    private final Menu rootMenu = new Menu("Main menu");

    public void buildMenu() {
        final Menu guest = new Menu("Guest menu");
        final Menu room = new Menu("Room menu");
        final Menu order = new Menu("Order menu");
        final Menu attendance = new Menu("Attendance menu");
        final Menu roomStatus = new Menu("Room status");
        final Menu orderSort = new Menu("Sort order's");
        final Menu roomSortFree = new Menu("Sort room's(Free)");
        final Menu roomSortAll = new Menu("Sort room's(All)");
        final Menu roomType = new Menu("Type room");

        rootMenuInit(room, guest, attendance, order);
        roomMenuInit(room, roomType, roomStatus);
        orderMenuInit(order, orderSort);
        guestMenuInit(guest);
        attendanceMenuInit(attendance);

        roomSortMenuInit(roomSortAll, "all");
        roomSortMenuInit(roomSortFree, "free");
        roomStatusMenuInit(roomStatus);
        roomTypeMenuInit(roomType, roomSortAll, roomSortFree);
        orderSortMenuInit(orderSort);

        Hotel.getInstance().createAttendance();
        Hotel.getInstance().createGuest();
        Hotel.getInstance().createRoom();
        Hotel.getInstance().createOrder();
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
        AtomicInteger index = new AtomicInteger(1);
        rootMenu.getMenuItemList().forEach(i -> i.setId(index.getAndIncrement()));
    }

    private void roomMenuInit(Menu room, Menu roomType, Menu roomStatus) {
        room.addItem(new MenuItem("Add room", new AddRoom(), rootMenu));
        room.addItem(new MenuItem("Change price", new ChangePriceRoom(), rootMenu));
        room.addItem(new MenuItem("Change status", roomStatus));
        room.addItem(new MenuItem("Show after date", new ShowDateRoom(), rootMenu));
        room.addItem(new MenuItem("Show number free room's", new ShowNumberFreeRoom(), rootMenu));
        room.addItem(new MenuItem("Show room", roomType));
        AtomicInteger index = new AtomicInteger(1);
        room.getMenuItemList().forEach(i -> i.setId(index.getAndIncrement()));
    }

    private void guestMenuInit(Menu guest) {
        guest.addItem(new MenuItem("Add guest", new AddGuest(), rootMenu));
        guest.addItem(new MenuItem("Show number guest", new ShowNumberGuest(), rootMenu));
        guest.addItem(new MenuItem("Show last tree room's", new ShowThreeRoomGuest(), rootMenu));
        AtomicInteger index = new AtomicInteger(1);
        guest.getMenuItemList().forEach(i -> i.setId(index.getAndIncrement()));
    }

    private void attendanceMenuInit(Menu attendance) {
        attendance.addItem(new MenuItem("Add attendance", new AddAttendance(), rootMenu));
        attendance.addItem(new MenuItem("Change price", new ChangePriceAttendance(), rootMenu));
        AtomicInteger index = new AtomicInteger(1);
        attendance.getMenuItemList().forEach(i -> i.setId(index.getAndIncrement()));
    }

    private void orderMenuInit(Menu order, Menu orderSort) {
        order.addItem(new MenuItem("Add order", new AddOrder(), rootMenu));
        order.addItem(new MenuItem("Disable order", new DeleteOrder(), rootMenu));
        order.addItem(new MenuItem("Add attendance", new AddAttendanceOrder(), rootMenu));
        order.addItem(new MenuItem("Show attendance order", new ShowAttendanceOrder(), rootMenu));
        order.addItem(new MenuItem("Show order's", orderSort));
        AtomicInteger index = new AtomicInteger(1);
        order.getMenuItemList().forEach(i -> i.setId(index.getAndIncrement()));
    }

    private void orderSortMenuInit(Menu orderSort) {
        orderSort.addItem(new MenuItem("Default", new ShowOrder("default"), rootMenu));
        orderSort.addItem(new MenuItem("Sort by date", new ShowOrder("date"), rootMenu));
        orderSort.addItem(new MenuItem("Sort by alphabet", new ShowOrder("alphabet"), rootMenu));
        AtomicInteger index = new AtomicInteger(1);
        orderSort.getMenuItemList().forEach(i -> i.setId(index.getAndIncrement()));
    }

    private void roomSortMenuInit(Menu roomSort, String type) {
        roomSort.addItem(new MenuItem("Default", new ShowRoom(type, "default"), rootMenu));
        roomSort.addItem(new MenuItem("Sort by price", new ShowRoom(type, "price"), rootMenu));
        roomSort.addItem(new MenuItem("Sort by classification", new ShowRoom(type, "classification"), rootMenu));
        roomSort.addItem(new MenuItem("Sort by room number", new ShowRoom(type, "room number"), rootMenu));
        AtomicInteger index = new AtomicInteger(1);
        roomSort.getMenuItemList().forEach(i -> i.setId(index.getAndIncrement()));
    }

    private void roomStatusMenuInit(Menu roomStatus) {
        roomStatus.addItem(new MenuItem("Rented", new ChangeStatusRoom(Status.RoomStatus.RENTED), rootMenu));
        roomStatus.addItem(new MenuItem("Free", new ChangeStatusRoom(Status.RoomStatus.FREE), rootMenu));
        roomStatus.addItem(new MenuItem("Served", new ChangeStatusRoom(Status.RoomStatus.SERVED), rootMenu));
        AtomicInteger index = new AtomicInteger(1);
        roomStatus.getMenuItemList().forEach(i -> i.setId(index.getAndIncrement()));
    }

    private void roomTypeMenuInit(Menu roomType, Menu roomSortAll, Menu roomSortFree) {
        roomType.addItem(new MenuItem("Free", roomSortFree));
        roomType.addItem(new MenuItem("All", roomSortAll));
        AtomicInteger index = new AtomicInteger(1);
        roomType.getMenuItemList().forEach(i -> i.setId(index.getAndIncrement()));
    }
}
