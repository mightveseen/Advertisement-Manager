package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDao implements IOrderDao {
    private final List<Order> orderList = new ArrayList<>();

    @Override
    public void create(Order order) {
        orderList.add(order);
    }

    @Override
    public void delete(int index) {
        try {
            orderList.remove(orderList.stream().filter(i -> i.getId() == index).findFirst().orElse(null));
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    public void update(Order order) {
        try {
            orderList.add(orderList.stream().filter(i -> i.getId() == order.getId()).findFirst().orElse(null).getId(), order);
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    public List<Order> readAll() {
        return orderList;
    }

    @Override
    public Order read(int index) {
        return orderList.stream().filter(i -> i.getId() == index).findFirst().orElse(null);
    }
}
