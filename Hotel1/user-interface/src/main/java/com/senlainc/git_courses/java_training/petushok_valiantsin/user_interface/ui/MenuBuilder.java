package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui;

import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.attendance.AddAttendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.attendance.ChangePriceAttendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.exit.MenuExit;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.guest.AddGuest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.guest.ShowNumberGuest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.guest.ShowThreeRoomGuest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.order.AddAttendanceOrder;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.order.AddOrder;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.order.DeleteOrder;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.order.ShowAttendanceOrder;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.order.ShowOrder;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.room.AddRoom;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.room.ChangePriceRoom;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.room.ChangeStatusRoom;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.room.ShowDateRoom;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.room.ShowNumberFreeRoom;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.room.ShowRoom;

import java.util.concurrent.atomic.AtomicInteger;

public class MenuBuilder {
    private final Menu rootMenu = new Menu("Main menu");

    public void buildMenu() {
        final Menu guest = new Menu("Guest Menu");
        final Menu room = new Menu("Room Menu");
        final Menu order = new Menu("Order Menu");
        final Menu attendance = new Menu("Attendance Menu");
        final Menu roomStatus = new Menu("Room Status Menu");
        final Menu orderSort = new Menu("Sort Order Menu");
        final Menu roomSortFree = new Menu("Sort Room Menu");
        final Menu roomSortAll = new Menu("Sort Room Menu");
        final Menu roomType = new Menu("Room Type Menu");

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
        rootMenu.addItem(new MenuItem("Exit", new MenuExit()));
        menuNumeration(rootMenu);
    }

    private void roomMenuInit(Menu room, Menu roomType, Menu roomStatus) {
        room.addItem(new MenuItem("Add room", new AddRoom(), rootMenu));
        room.addItem(new MenuItem("Change price", new ChangePriceRoom(), rootMenu));
        room.addItem(new MenuItem("Change status", roomStatus));
        room.addItem(new MenuItem("Show after date", new ShowDateRoom(), rootMenu));
        room.addItem(new MenuItem("Show number free room's", new ShowNumberFreeRoom(), rootMenu));
        room.addItem(new MenuItem("Show room", roomType));
        room.addItem(new MenuItem("Exit", rootMenu));
        menuNumeration(room);
    }

    private void guestMenuInit(Menu guest) {
        guest.addItem(new MenuItem("Add guest", new AddGuest(), rootMenu));
        guest.addItem(new MenuItem("Show number guest", new ShowNumberGuest(), rootMenu));
        guest.addItem(new MenuItem("Show last tree room's", new ShowThreeRoomGuest(), rootMenu));
        guest.addItem(new MenuItem("Exit", rootMenu));
        menuNumeration(guest);
    }

    private void attendanceMenuInit(Menu attendance) {
        attendance.addItem(new MenuItem("Add attendance", new AddAttendance(), rootMenu));
        attendance.addItem(new MenuItem("Change price", new ChangePriceAttendance(), rootMenu));
        attendance.addItem(new MenuItem("Exit", rootMenu));
        menuNumeration(attendance);
    }

    private void orderMenuInit(Menu order, Menu orderSort) {
        order.addItem(new MenuItem("Add order", new AddOrder(), rootMenu));
        order.addItem(new MenuItem("Disable order", new DeleteOrder(), rootMenu));
        order.addItem(new MenuItem("Add attendance", new AddAttendanceOrder(), rootMenu));
        order.addItem(new MenuItem("Show attendance order", new ShowAttendanceOrder(), rootMenu));
        order.addItem(new MenuItem("Show order's", orderSort));
        order.addItem(new MenuItem("Exit", rootMenu));
        menuNumeration(order);
    }

    private void orderSortMenuInit(Menu orderSort) {
        orderSort.addItem(new MenuItem("Default", new ShowOrder("default"), rootMenu));
        orderSort.addItem(new MenuItem("Sort by date", new ShowOrder("date"), rootMenu));
        orderSort.addItem(new MenuItem("Sort by alphabet", new ShowOrder("alphabet"), rootMenu));
        orderSort.addItem(new MenuItem("Exit", rootMenu));
        menuNumeration(orderSort);
    }

    private void roomSortMenuInit(Menu roomSort, String type) {
        roomSort.addItem(new MenuItem("Default", new ShowRoom(type, "default"), rootMenu));
        roomSort.addItem(new MenuItem("Sort by price", new ShowRoom(type, "price"), rootMenu));
        roomSort.addItem(new MenuItem("Sort by classification", new ShowRoom(type, "classification"), rootMenu));
        roomSort.addItem(new MenuItem("Sort by room number", new ShowRoom(type, "room number"), rootMenu));
        roomSort.addItem(new MenuItem("Exit", rootMenu));
        menuNumeration(roomSort);
    }

    private void roomStatusMenuInit(Menu roomStatus) {
        roomStatus.addItem(new MenuItem("Rented", new ChangeStatusRoom("RENTED"), rootMenu));
        roomStatus.addItem(new MenuItem("Free", new ChangeStatusRoom("FREE"), rootMenu));
        roomStatus.addItem(new MenuItem("Served", new ChangeStatusRoom("SERVED"), rootMenu));
        roomStatus.addItem(new MenuItem("Exit", rootMenu));
        menuNumeration(roomStatus);
    }

    private void roomTypeMenuInit(Menu roomType, Menu roomSortAll, Menu roomSortFree) {
        roomType.addItem(new MenuItem("Free", roomSortFree));
        roomType.addItem(new MenuItem("All", roomSortAll));
        roomType.addItem(new MenuItem("Exit", rootMenu));
        menuNumeration(roomType);
    }

    private void menuNumeration(Menu menu) {
        final AtomicInteger index = new AtomicInteger(1);
        menu.getMenuItemList().forEach(i -> i.setId(index.getAndIncrement()));
    }
}
