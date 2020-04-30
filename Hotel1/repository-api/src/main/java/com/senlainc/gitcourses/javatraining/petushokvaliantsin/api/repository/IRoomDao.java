package com.senlainc.gitcourses.javatraining.petushokvaliantsin.api.repository;

import com.senlainc.gitcourses.javatraining.petushokvaliantsin.model.Room;
import com.senlainc.gitcourses.javatraining.petushokvaliantsin.model.status.RoomStatus;

import java.util.List;

public interface IRoomDao extends IGenericDao<Room, Long> {

    List<Room> readAllFree(int fistElement, int maxResult);

    List<Room> readAllFree(int fistElement, int maxResult, String parameter);

    Long readFreeSize();

    RoomStatus readStatus(long index);

    Double readPrice(long index);

    boolean readByNumber(int number);
}
