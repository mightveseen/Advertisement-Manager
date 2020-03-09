package com.senlainc.git_courses.java_training.petushok_valiantsin;

import com.senlainc.git_courses.java_training.petushok_valiantsin.base_conection.ConnectionManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.RoomConfig;
import com.senlainc.git_courses.java_training.petushok_valiantsin.exception.ElementNotFoundException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.exception.EntityNotAvailableException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.sort.Sort;
import com.senlainc.git_courses.java_training.petushok_valiantsin.sort.SortParameter;
import com.senlainc.git_courses.java_training.petushok_valiantsin.status.RoomStatus;

import java.util.List;

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
        if (roomDao.readByNumber(room.getNumber()) != null) {
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
    public List<Room> getRoomList(String parameter) {
        if (parameter.equals("free")) {
            return roomDao.readAllFree();
        }
        return roomDao.readAll();
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
    public long numFreeRoom() {
        return roomDao.readFreeSize();
    }

    @Override
    public List<Room> sort(String type, String parameter) {
        final List<Room> myList = getRoomList(type);
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
