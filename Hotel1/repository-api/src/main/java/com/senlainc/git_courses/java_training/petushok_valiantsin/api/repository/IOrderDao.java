package com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface IOrderDao extends ICommonDao<Order, Long> {

    List<Room> readLastRoom(Long index, Integer limit);

    List<Room> readAfterDate(LocalDate date);
}
