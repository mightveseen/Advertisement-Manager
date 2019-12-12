package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.utility.MyList;

public class OrderDao implements IOrderDao {
    private MyList<Order> orderMyList = new MyList<>();

    @Override
    public void create(Order order) {
        orderMyList.add(order);
    }
    @Override
    public void delete(int index) {

    }
    @Override
    public void update(int index, Order order) {

    }
    @Override
    public MyList readAll() {
        return orderMyList;
    }
    @Override
    public Order readById(int index) {
        return orderMyList.get(index);
    }
}
