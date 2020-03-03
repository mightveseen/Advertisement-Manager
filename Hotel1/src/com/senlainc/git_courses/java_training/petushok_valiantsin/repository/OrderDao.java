package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.ConnectionManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization.Serialization;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@DependencyClass
@DependencyPrimary
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "orderDao")
public class OrderDao implements IOrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDao.class.getName());
    @DependencyComponent
    private static Serialization serialization;
    @DependencyComponent
    private ConnectionManager connectionManager;
    @XmlElementWrapper(name = "orderList")
    @XmlElement(name = "order")
    private List<Order> orderList;

    @Override
    public void create(Order order) {
        order.setId(orderList.size() + 1);
        orderList.add(order);
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
