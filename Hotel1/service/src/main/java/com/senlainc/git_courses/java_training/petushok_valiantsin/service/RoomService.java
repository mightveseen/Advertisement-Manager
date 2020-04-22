package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IRoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.data.MaxResult;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementAlreadyExistsException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@PropertySource(value = {"classpath:/properties/room.properties"}, ignoreResourceNotFound = true)
public class RoomService implements IRoomService {

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
            throw new ElementAlreadyExistsException("Room with number: " + number + " already exists.");
        }
        roomDao.create(new Room(number, classification, roomNumber, capacity, price));
    }

    @Override
    @Transactional
    public void delete(long index) {
        roomDao.delete(index);
    }

    @Override
    @Transactional
    public void changePrice(long index, double price) {
        final Room room = roomDao.read(index);
        room.setPrice(price);
        roomDao.update(room);
    }

    @Override
    @Transactional
    public void changeStatus(long index, String status) {
        if (changeStatusProperty) {
            final Room room = roomDao.read(index);
            room.setStatus(RoomStatus.valueOf(status));
            roomDao.update(room);
        } else {
            throw new ElementNotAvailableException("Property for change status is false");
        }
    }

    @Override
    public Long numFreeRoom() {
        return roomDao.readFreeSize();
    }

    @Override
    public List<Room> getRoomList(String parameter) {
        final int maxResult = MaxResult.ROOM.getMaxResult();
        final int firstElement = roomDao.readSize().intValue() - maxResult;
        if (parameter.equals("free")) {
            return roomDao.readAllFree(firstElement, maxResult);
        }
        return roomDao.readAll(firstElement, maxResult);
    }

    @Override
    public List<Room> sort(String type, String parameter) {
        final int maxResult = MaxResult.ROOM.getMaxResult();
        final int firstElement = roomDao.readSize().intValue() - maxResult;
        if (parameter.equals("default")) {
            return getRoomList(type);
        }
        if (type.equals("free")) {
            return roomDao.readAllFree(firstElement, maxResult, parameter);
        }
        return roomDao.readAll(firstElement, maxResult, parameter);
    }
}
