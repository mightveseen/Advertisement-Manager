package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.ConnectionManager;

import java.util.ArrayList;
import java.util.List;

@DependencyClass
@DependencyPrimary
public class OrderDao implements IOrderDao {
    @DependencyComponent
    private ConnectionManager connectionManager;
    private List<Order> orderList;

    @Override
    public Order create(Order order) {
        final String SQL_CREATE_QUARY = "SELECT *\n"
                + "FROM `Order`\n"
                + "INNER JOIN `Room` ON `room`.`id` = `Order`.`room_id`\n"
                + "INNER JOIN `Guest` ON `guest`.`id` = `Order`.`guest_id`;";
        return null;
    }

    @Override
    public void delete(Integer index) {
        orderList.remove(orderList.stream()
                .filter(i -> i.getId() == index)
                .findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new));
    }

    @Override
    public void update(Order order) {
        orderList.set(orderList.indexOf(orderList.stream()
                .filter(i -> i.getId() == order.getId())
                .findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new)), order);
    }

    @Override
    public List<Order> readAll() {
        return new ArrayList<>(orderList);
    }

    @Override
    public Order read(Integer index) {
        return orderList.stream()
                .filter(i -> i.getId() == index)
                .findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new);
    }
}
