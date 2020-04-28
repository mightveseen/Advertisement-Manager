package com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;

import java.util.List;

public interface IRoomDao extends IGenericDao<Room, Long> {

    List<Room> readAllFree(int fistElement, int maxResult);

    List<Room> readAllFree(int fistElement, int maxResult, String parameter);

    Long readFreeSize();

    RoomStatus readStatus(long index);

    boolean readByNumber(int number);
}
