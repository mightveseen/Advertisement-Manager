package com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.MyList;

public interface IOrderDao {
    void create(Order order);

    void delete(int index);

    void update(int index, Order order);

    MyList<Order> readAll();

    Order read(int index);

}
