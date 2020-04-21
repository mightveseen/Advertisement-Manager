package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.data.MaxResult;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.CreateQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.DeleteQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.ReadQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.UpdateQueryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@PropertySource(value = {"classpath:/properties/room.properties"}, ignoreResourceNotFound = true)
public class RoomService implements IRoomService {

    private static final Logger LOGGER = LogManager.getLogger(RoomService.class);
    private final IRoomDao roomDao;
    @Value("${ROOM_CONFIG.CHANGE_STATUS_VALUE:true}")
    private boolean changeStatusProperty;

    @Autowired
    public RoomService(IRoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    @Transactional
    public void add(int number, String classification, short roomNumber, short capacity, double price) {
        if (roomDao.readByNumber(number)) {
            LOGGER.info("Room with number: {} already exists.", number);
            return;
        }
        try {
            roomDao.create(new Room(number, classification, roomNumber, capacity, price));
            LOGGER.info("Add room in database");
        } catch (CreateQueryException e) {
            LOGGER.warn("Error while creating room", e);
        }
    }

    @Override
    @Transactional
    public void delete(long index) {
        try {
            roomDao.delete(index);
            LOGGER.info("Delete room with index: {} from database", index);
        } catch (DeleteQueryException e) {
            LOGGER.warn("Error while deleting room", e);
        }
    }

    @Override
    @Transactional
    public void changePrice(long index, double price) {
        try {
            final Room room = roomDao.read(index);
            room.setPrice(price);
            roomDao.update(room);
            LOGGER.info("Change room price");
        } catch (UpdateQueryException e) {
            LOGGER.warn("Error while updating room. Update operation: change price.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn("Room with index {} don't exists.", index, e);
        }
    }

    @Override
    @Transactional
    public void changeStatus(long index, String status) {
        if (changeStatusProperty) {
            try {
                final Room room = roomDao.read(index);
                room.setStatus(RoomStatus.valueOf(status));
                roomDao.update(room);
                LOGGER.info("Change room status");
            } catch (UpdateQueryException e) {
                LOGGER.warn("Error while updating room. Update operation: change status.", e);
            } catch (ReadQueryException e) {
                LOGGER.warn("Room with index {} don't exists.", index, e);
            }
        } else {
            LOGGER.info("Property for change status is false");
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
            }
            return roomDao.readAll(roomDao.readSize().intValue() - maxResult, maxResult);
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
