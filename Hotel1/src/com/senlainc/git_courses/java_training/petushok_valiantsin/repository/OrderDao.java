package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.MyList;


public class OrderDao implements IOrderDao {
    private final MyList<Order> orderMyList = new MyList<>();

    @Override
    public void create(Order order) {
        orderMyList.add(order);
    }

    @Override
    public void delete(int index) {
        orderMyList.remove(index);
    }

    @Override
    public void update(int index, Order order) {
        orderMyList.add(index, order);
    }

    @Override
    public MyList<Order> readAll() {
        return orderMyList;
    }

    @Override
    public Order read(int index) {
        return orderMyList.get(index);
    }
}
