package com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;

import java.util.List;

public interface IOrderDao {
    void create(Order order);

    void delete(int index);

    void update(Order order);

    List<Order> readAll();

    Order read(int index);

    void setAll();
}
