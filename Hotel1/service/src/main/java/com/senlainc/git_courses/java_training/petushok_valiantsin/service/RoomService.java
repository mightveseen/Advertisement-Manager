package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.CustomEntityManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration.RoomConfig;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotFoundException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.EntityNotAvailableException;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;

@DependencyClass
@DependencyPrimary
public class RoomService implements IRoomService {

    private static final String ELEMENT_NOT_FOUND = "Room with index: %d dont exists.";
    @DependencyComponent
    private static RoomConfig roomConfig;
    private final EntityManager entityManager = CustomEntityManager.getEntityManager();
    @DependencyComponent
    private IRoomDao roomDao;

    @Override
    public void add(int number, String classification, short roomNumber, short capacity, double price) {
        entityManager.getTransaction().begin();
        if (roomDao.readByNumber(number) != null) {
            throw new EntityNotAvailableException(String.format("Room with number: %d already exists.", number));
        }
        roomDao.create(new Room(number, classification, roomNumber, capacity, price));
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(int index) {
        try {
            entityManager.getTransaction().begin();
            roomDao.delete((long) index);
            entityManager.getTransaction().commit();
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
            entityManager.getTransaction().begin();
            final Room room = roomDao.read((long) index);
            room.setPrice(price);
            roomDao.update(room);
            entityManager.getTransaction().commit();
        } catch (ElementNotFoundException e) {
            throw new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, index), e);
        }
    }

    @Override
    public void changeStatus(int index, String status) {
        if (!roomConfig.getChangeStatus()) {
            throw new EntityNotAvailableException("Property for change status is false");
        }
        try {
            entityManager.getTransaction().begin();
            final Room room = roomDao.read((long) index);
            room.setStatus(RoomStatus.valueOf(status));
            roomDao.update(room);
            entityManager.getTransaction().commit();
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
        myList.sort(Comparator.comparing(Room::getPrice));
    }

    private void sortByClassification(List<Room> myList) {
        myList.sort(Comparator.comparing(Room::getClassification));
    }

    private void sortByRoomNumber(List<Room> myList) {
        myList.sort(Comparator.comparing(Room::getRoomNumber));
    }
}
