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
        for (int i = 0; i < orderMyList.size(); i++) {
            if (orderMyList.get(i).getId() == index) {
                orderMyList.remove(i);
                return;
            }
        }
    }

    @Override
    public void update(Order order) {
        for (int i = 0; i < orderMyList.size(); i++) {
            if (orderMyList.get(i).getId() == order.getId()) {
                orderMyList.add(i, order);
                return;
            }
        }
    }

    @Override
    public MyList<Order> readAll() {
        return orderMyList;
    }

    @Override
    public Order read(int index) {
        for (int i = 0; i < orderMyList.size(); i++) {
            if (orderMyList.get(i).getId() == index) {
                return orderMyList.get(i);
            }
        }
        return null;
    }
}
