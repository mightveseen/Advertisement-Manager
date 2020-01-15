package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Status;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RoomService implements IRoomService {
    private final IRoomDao roomDao;
    private final Comparator<Room> SORT_BY_PRICE = Comparator.comparing(room -> String.valueOf(room.getPrice()));
    private final Comparator<Room> SORT_BY_CLASSIFICATION = Comparator.comparing(Room::getClassification);
    private final Comparator<Room> SORT_BY_ROOM_NUMBER = Comparator.comparing(room -> String.valueOf(room.getRoomNumber()));

    public RoomService(IRoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public void add(Room room) {
        roomDao.create(room);
    }

    @Override
    public void delete(int index) {
        try {
            roomDao.delete(index);
        } catch (NullPointerException e){
            System.err.println("Room with index: " + index + " dont exists.");
        }
    }

    @Override
    public List<Room> getRoomList() {
        return roomDao.readAll();
    }

    @Override
    public Room getRoom(int index) {
        try {
            return roomDao.read(index);
        } catch (NullPointerException e){
            throw new NullPointerException("Room with index: " + index + " dont exists.");
        }
    }
    @Override
    public void changePrice(int index, double price) {
        try {
            Room room = roomDao.read(index);
            room.setPrice(price);
            roomDao.update(room);
        } catch (NullPointerException e) {
            System.err.println("Room with index: " + index + " dont exists.");
        }
    }

    @Override
    public void changeStatus(int index, Status.RoomStatus status) {
        try {
            Room room = roomDao.read(index);
            room.setStatus(status);
            roomDao.update(room);
        } catch (NullPointerException e) {
            System.err.println("Room with index: " + index + " dont exists.");
        }
    }

    @Override
    public void show(String parameter) {
        switch (parameter) {
            case "all":
                showAllRoom();
                break;
            case "free":
                showFreeRoom();
                break;
        }
    }

    @Override
    public void show(List<Room> myList) {
        for (Room room : myList) {
            System.out.println(room);
        }
    }

    private void showAllRoom() {
        for (Room room : roomDao.readAll()) {
            System.out.println(room);
        }
    }

    private void showFreeRoom() {
        for(Room room : roomDao.readAll()) {
            if (room.getStatus().equals(Status.RoomStatus.FREE)) {
                System.out.println(room);
            }
        }
    }

    @Override
    public void numFreeRoom() {
        final long counter = roomDao.readAll().stream().filter(i -> i.getStatus().equals(Status.RoomStatus.FREE)).count();
        System.out.println("\nNumber of free room: " + counter);
    }

    @Override
    public List<Room> sort(String parameter) {
        List<Room> myList = new ArrayList<>(roomDao.readAll());
        switch (parameter) {
            case "price":
                sortByPrice(myList);
                return myList;
            case "classification":
                sortByClassification(myList);
                return myList;
            case "room number":
                sortByRoomNumber(myList);
                return myList;
        }
        return null;
    }

    private void sortByPrice(List<Room> myList) {
        myList.sort(SORT_BY_PRICE);
    }

    private void sortByClassification(List<Room> myList) {
        myList.sort(SORT_BY_CLASSIFICATION);
    }

    private void sortByRoomNumber(List<Room> myList) {
        myList.sort(SORT_BY_ROOM_NUMBER);
    }
}
