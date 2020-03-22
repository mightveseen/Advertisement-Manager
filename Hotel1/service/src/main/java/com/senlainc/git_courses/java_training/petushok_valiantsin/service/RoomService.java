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
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.CreateQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.DeleteQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.ReadQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.UpdateQueryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;

@DependencyClass
@DependencyPrimary
public class RoomService implements IRoomService {

    private static final Logger LOGGER = LogManager.getLogger(RoomService.class);
    @DependencyComponent
    private static RoomConfig roomConfig;
    private final EntityManager entityManager = CustomEntityManager.getEntityManager();
    @DependencyComponent
    private IRoomDao roomDao;

    @Override
    public void add(int number, String classification, short roomNumber, short capacity, double price) {
        if (roomDao.readByNumber(number).equals(Boolean.TRUE)) {
            LOGGER.info("Room with number: {} already exists.", number);
            return;
        }
        try {
            entityManager.getTransaction().begin();
            roomDao.create(new Room(number, classification, roomNumber, capacity, price));
            entityManager.getTransaction().commit();
            LOGGER.info("Add room in list");
        } catch (CreateQueryException e) {
            entityManager.getTransaction().rollback();
            LOGGER.warn("Error while creating room", e);
        }
    }

    @Override
    public void delete(long index) {
        try {
            entityManager.getTransaction().begin();
            roomDao.delete(index);
            entityManager.getTransaction().commit();
        } catch (DeleteQueryException e) {
            entityManager.getTransaction().rollback();
            LOGGER.warn("Error while deleting room", e);
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
    public void changePrice(long index, double price) {
        try {
            entityManager.getTransaction().begin();
            final Room room = roomDao.read(index);
            room.setPrice(price);
            entityManager.getTransaction().begin();
            roomDao.update(room);
            entityManager.getTransaction().commit();
        } catch (UpdateQueryException e) {
            entityManager.getTransaction().rollback();
            LOGGER.warn("Error while updating room - change price.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn("Room with index {} don't exists.", index, e);
        }
    }

    @Override
    public void changeStatus(long index, String status) {
        if (!roomConfig.getChangeStatus()) {
            LOGGER.info("Property for change status is false");
            return;
        }
        try {
            final boolean transactionActivity = entityManager.getTransaction().isActive();
            final Room room = roomDao.read(index);
            room.setStatus(RoomStatus.valueOf(status));
            if (!transactionActivity) {
                entityManager.getTransaction().begin();
            }
            roomDao.update(room);
            if (!transactionActivity) {
                entityManager.getTransaction().commit();
            }
        } catch (UpdateQueryException e) {
            entityManager.getTransaction().rollback();
            LOGGER.warn("Error while updating room - change status.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn("Room with index {} don't exists.", index, e);
        }
    }

    @Override
    public long numFreeRoom() {
        return roomDao.readFreeSize();
    }

    @Override
    public List<Room> sort(String type, String parameter) {
        final List<Room> rooms = getRoomList(type);
        switch (parameter) {
            case "price":
                sortByPrice(rooms);
                break;
            case "classification":
                sortByClassification(rooms);
                break;
            case "room number":
                sortByRoomNumber(rooms);
                break;
            default:
                sortById(rooms);
                break;
        }
        return rooms;
    }

    private void sortByPrice(List<Room> rooms) {
        rooms.sort(Comparator.comparing(Room::getPrice));
    }

    private void sortByClassification(List<Room> rooms) {
        rooms.sort(Comparator.comparing(Room::getClassification));
    }

    private void sortByRoomNumber(List<Room> rooms) {
        rooms.sort(Comparator.comparing(Room::getRoomNumber));
    }

    private void sortById(List<Room> rooms) {
        rooms.sort(Comparator.comparing(Room::getId));
    }
}
