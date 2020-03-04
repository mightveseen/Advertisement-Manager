package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.ConnectionManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration.RoomConfig;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotFoundException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.EntityNotAvailableException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.sort.Sort;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.sort.SortParameter;

import java.util.List;
import java.util.stream.Collectors;

@DependencyClass
@DependencyPrimary
public class RoomService implements IRoomService {
    private static final String ELEMENT_NOT_FOUND = "Room with index: %d dont exists.";
    @DependencyComponent
    private static RoomConfig roomConfig;
    @DependencyComponent
    private IRoomDao roomDao;
    @DependencyComponent
    private ConnectionManager connectionManager;

    @Override
    public void add(Room room) {
        if (roomDao.readAll().stream().anyMatch(i -> i.getNumber() == room.getNumber())) {
            throw new EntityNotAvailableException(String.format("Room with number: %d already exists.", room.getNumber()));
        }
        roomDao.create(room);
        connectionManager.commit();
    }

    @Override
    public void delete(int index) {
        try {
            roomDao.delete(index);
            connectionManager.commit();
        } catch (ElementNotFoundException e) {
            throw new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, index), e);
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
        } catch (ElementNotFoundException e) {
            throw new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, index), e);
        }
    }

    @Override
    public void changePrice(int index, double price) {
        try {
            final Room room = roomDao.read(index);
            room.setPrice(price);
            roomDao.update(room);
            connectionManager.commit();
        } catch (ElementNotFoundException e) {
            throw new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, index), e);
        }
    }

    @Override
    public void changeStatus(int index, RoomStatus status) {
        if (!roomConfig.getChangeStatus()) {
            throw new EntityNotAvailableException("Property for change status is false");
        }
        try {
            final Room room = roomDao.read(index);
            room.setStatus(status);
            roomDao.update(room);
            connectionManager.commit();
        } catch (ElementNotFoundException e) {
            throw new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, index), e);
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
        return myList.stream()
                .filter(i -> i.getStatus().equals(RoomStatus.FREE))
                .collect(Collectors.toList());
    }

    @Override
    public long numFreeRoom() {
        return roomDao.readAll().stream()
                .filter(i -> i.getStatus().equals(RoomStatus.FREE))
                .count();
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
            default:
                break;
        }
        return myList;
    }

    private void sortByPrice(List<Room> myList) {
        myList.sort(Sort.ROOM.getComparator(SortParameter.PRICE));
    }

    private void sortByClassification(List<Room> myList) {
        myList.sort(Sort.ROOM.getComparator(SortParameter.CLASSIFICATION));
    }

    private void sortByRoomNumber(List<Room> myList) {
        myList.sort(Sort.ROOM.getComparator(SortParameter.ROOM_NUMBER));
    }
}
