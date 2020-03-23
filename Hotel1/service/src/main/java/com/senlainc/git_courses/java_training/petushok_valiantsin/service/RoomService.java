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
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.data.MaxResult;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.CreateQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.DeleteQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.ReadQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.UpdateQueryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.Collections;
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
        if (roomDao.readByNumber(number)) {
            LOGGER.info("Room with number: {} already exists.", number);
            return;
        }
        try {
            entityManager.getTransaction().begin();
            roomDao.create(new Room(number, classification, roomNumber, capacity, price));
            entityManager.getTransaction().commit();
            LOGGER.info("Add room in database");
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
            LOGGER.info("Delete room with index: {} from database", index);
        } catch (DeleteQueryException e) {
            entityManager.getTransaction().rollback();
            LOGGER.warn("Error while deleting room", e);
        }
    }

    @Override
    public void changePrice(long index, double price) {
        try {
            final Room room = roomDao.read(index);
            room.setPrice(price);
            entityManager.getTransaction().begin();
            roomDao.update(room);
            entityManager.getTransaction().commit();
            LOGGER.info("Change room price");
        } catch (UpdateQueryException e) {
            entityManager.getTransaction().rollback();
            LOGGER.warn("Error while updating room. Update operation: change price.", e);
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
            LOGGER.info("Change room status");
        } catch (UpdateQueryException e) {
            entityManager.getTransaction().rollback();
            LOGGER.warn("Error while updating room. Update operation: change status.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn("Room with index {} don't exists.", index, e);
        }
    }

    @Override
    public Long numFreeRoom() {
        try {
            final long numFree = roomDao.readFreeSize();
            LOGGER.info("Show umber of free room");
            return numFree;
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read room's. Read operation: number of free room's.", e);
        }
        return null;
    }

    @Override
    public List<Room> getRoomList(String parameter) {
        final int maxResult = MaxResult.ROOM.getMaxResult();
        try {
            if (parameter.equals("free")) {
                return roomDao.readAllFree(roomDao.readSize().intValue() - maxResult, maxResult);
            } else {
                return roomDao.readAll(roomDao.readSize().intValue() - maxResult, maxResult);
            }
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read room's.", e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Room> sort(String type, String parameter) {
        final int maxResult = MaxResult.ROOM.getMaxResult();
        try {
            if (parameter.equals("default")) {
                return getRoomList(type);
            }
            if (type.equals("free")) {
                return roomDao.readAllFree(roomDao.readSize().intValue() - maxResult, maxResult, parameter);
            }
            return roomDao.readAll(roomDao.readSize().intValue() - maxResult, maxResult, parameter);
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read room's.", e);
        }
        return Collections.emptyList();
    }
}
