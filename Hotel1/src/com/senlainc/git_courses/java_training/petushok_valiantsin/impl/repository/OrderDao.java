package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Order;

public class OrderDao implements IOrderDao {
    private Order orderArray[] = new Order[256];

    @Override
    public void create(Order order) {

    }
    @Override
    public void delete(int index) {

    }
    @Override
    public void update(int index, Order order) {

    }
    @Override
    public Order[] readAll() {
        return orderArray;
    }
    @Override
    public Order readById(int index) {
        return orderArray[index];
    }
}
