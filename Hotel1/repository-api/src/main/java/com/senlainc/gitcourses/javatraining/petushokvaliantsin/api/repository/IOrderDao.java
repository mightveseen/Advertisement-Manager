package com.senlainc.gitcourses.javatraining.petushokvaliantsin.api.repository;

import com.senlainc.gitcourses.javatraining.petushokvaliantsin.model.Order;
import com.senlainc.gitcourses.javatraining.petushokvaliantsin.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface IOrderDao extends IGenericDao<Order, Long> {

    List<Order> readAll(int fistElement, int maxResult, String sortObject, String sortParameter);

    List<Room> readLastRoom(Long index, Integer limit);

    List<Room> readAfterDate(LocalDate date);
}
