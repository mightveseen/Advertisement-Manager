package com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Order;

public interface IOrderDao {
    void create(Order order);
    void delete(int index);
    void update(int index, Order order);
    Order[] readAll();
    Order readById(int index);
}
