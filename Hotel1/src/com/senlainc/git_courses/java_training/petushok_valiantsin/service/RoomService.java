package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration.RoomConfig;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.sort.Sort;

import java.util.List;
import java.util.stream.Collectors;

@DependencyClass
public class RoomService implements IRoomService {
    @DependencyComponent
    private static IRoomService instance;
    @DependencyComponent
    private static IRoomDao roomDao;

    public static IRoomService getInstance() {
        return instance;
    }

    @Override
    public void load() {
        roomDao.setAll();
    }

    @Override
    public void add(Room room) {
        if (roomDao.readAll().stream().anyMatch(i -> i.getNumber() == room.getNumber())) {
            throw new RuntimeException("Room with number: " + room.getNumber() + " already exists.");
        }
        roomDao.create(room);
    }

    @Override
    public void delete(int index) {
        try {
            roomDao.delete(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Room with index: " + index + " dont exists.");
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
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Room with index: " + index + " dont exists.", e);
        }
    }

    @Override
    public void changePrice(int index, double price) {
        try {
            final Room room = roomDao.read(index);
            room.setPrice(price);
            roomDao.update(room);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Room with index: " + index + " dont exists.", e);
        }
    }

    @Override
    public void changeStatus(int index, RoomStatus status) {
        if (!RoomConfig.getInstance().getChangeStatus()) {
            throw new RuntimeException("Property for change status is false");
        }
        try {
            final Room room = roomDao.read(index);
            room.setStatus(status);
            roomDao.update(room);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Room with index: " + index + " dont exists.", e);
        }
    }

    @Override
    public List<Room> show(String parameter, List<Room> myList) {
        if (parameter.equals("free")) {
            return showFreeRoom(myList);
        }
        return myList;
    }

    private List<Room> showFreeRoom(List<Room> myList) {
        return myList.stream().filter(i -> i.getStatus().equals(RoomStatus.FREE)).collect(Collectors.toList());
    }

    @Override
    public long numFreeRoom() {
        return roomDao.readAll().stream().filter(i -> i.getStatus().equals(RoomStatus.FREE)).count();
    }

    @Override
    public List<Room> sort(String parameter) {
        final List<Room> myList = roomDao.readAll();
        switch (parameter) {
            case "price":
                sortByPrice(myList);
                break;
            case "classification":
                sortByClassification(myList);
                break;
            case "room number":
                sortByRoomNumber(myList);
                break;
        }
        return myList;
    }

    private void sortByPrice(List<Room> myList) {
        myList.sort(Sort.ROOM.getComparator("PRICE"));
    }

    private void sortByClassification(List<Room> myList) {
        myList.sort(Sort.ROOM.getComparator("CLASSIFICATION"));
    }

    private void sortByRoomNumber(List<Room> myList) {
        myList.sort(Sort.ROOM.getComparator("ROOM_NUMBER"));
    }
}
