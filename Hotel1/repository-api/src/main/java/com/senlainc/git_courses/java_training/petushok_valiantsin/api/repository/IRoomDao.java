package com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;

import java.util.List;

public interface IRoomDao extends ICommonDao<Room, Long> {

    List<Room> readAllFree();

    Long readFreeSize();

    RoomStatus readStatus(long index);

    Boolean readByNumber(Integer number);
}
