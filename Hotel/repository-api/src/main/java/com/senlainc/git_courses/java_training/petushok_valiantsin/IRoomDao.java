package com.senlainc.git_courses.java_training.petushok_valiantsin;

import java.util.List;

public interface IRoomDao extends ICommonDao<Room, Integer> {
    List<Room> readAllFree();

    Integer readFreeSize();

    Room readByNumber(Integer number);
}
