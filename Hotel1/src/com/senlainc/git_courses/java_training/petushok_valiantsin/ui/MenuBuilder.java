package com.senlainc.git_courses.java_training.petushok_valiantsin.ui;

import java.time.LocalDate;

public class MenuBuilder {
    private final Menu rootMenu = new Menu("Main menu");
    private final Hotel hotel;

    public MenuBuilder(Hotel hotel) {
        this.hotel = hotel;
    }

    public void buildMenu() {
        MenuItem menuItemRoomList = new MenuItem("Show room's", rootMenu);
        MenuItem menuItemFreeRoomList = new MenuItem("Show free room's", rootMenu);
        MenuItem menuItemGuestList = new MenuItem("Show guest's and them room's", rootMenu);
        MenuItem menuItemNumberFreeRoom = new MenuItem("Number free room's", rootMenu);
        MenuItem menuItemNumberGuest = new MenuItem("Number guest's", rootMenu);
        MenuItem menuItemFreeAfterDate = new MenuItem("Room free after date", rootMenu);
        MenuItem menuItemThreeLastGuest = new MenuItem("Show three last guest", rootMenu);
        MenuItem menuItemGuestAttendance = new MenuItem("Guest attendance", rootMenu);

        hotel.createAttendance();
        hotel.createGuest();
        hotel.createRoom();
        hotel.createOrder();

        menuItemRoomList.setAction(() -> hotel.sortRoom("price"));

        menuItemFreeRoomList.setAction(() -> hotel.showRoom("free"));

        menuItemGuestList.setAction(hotel::showOrder);

        menuItemNumberFreeRoom.setAction(hotel::numFreeRoom);

        menuItemNumberGuest.setAction(hotel::numGuest);

        menuItemFreeAfterDate.setAction(() -> hotel.showAfterDate(LocalDate.of(2019, 1, 25)));

        menuItemThreeLastGuest.setAction(() -> hotel.showGuestRoom(2));

        menuItemGuestAttendance.setAction(() -> {
            hotel.addAttendance(1, 2);
            hotel.showAttendance(1);
        });

        rootMenu.addItem(menuItemRoomList);
        rootMenu.addItem(menuItemFreeRoomList);
        rootMenu.addItem(menuItemGuestList);
        rootMenu.addItem(menuItemNumberFreeRoom);
        rootMenu.addItem(menuItemNumberGuest);
        rootMenu.addItem(menuItemFreeAfterDate);
        rootMenu.addItem(menuItemThreeLastGuest);
        rootMenu.addItem(menuItemGuestAttendance);
    }

    public Menu getRootMenu() {
        buildMenu();
        return this.rootMenu;
    }
}
