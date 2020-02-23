package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.FileNotExistException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization.Serialization;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@DependencyClass
@DependencyPrimary
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "orderDao")
public class OrderDao implements IOrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDao.class.getName());
    @DependencyComponent
    private static Serialization serialization;
    @XmlElementWrapper(name = "orderList")
    @XmlElement(name = "order")
    private List<Order> orderList;

    @Override
    public void create(Order order) {
        order.setId(orderList.size() + 1);
        orderList.add(order);
        saveAll();
    }

    @Override
    public void delete(int index) {
        orderList.remove(orderList.stream()
                .filter(i -> i.getId() == index)
                .findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new));
        saveAll();
    }

    @Override
    public void update(Order order) {
        orderList.set(orderList.indexOf(orderList.stream()
                .filter(i -> i.getId() == order.getId())
                .findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new)), order);
        saveAll();
    }

    @Override
    public List<Order> readAll() {
        return new ArrayList<>(orderList);
    }

    @Override
    public Order read(int index) {
        return orderList.stream()
                .filter(i -> i.getId() == index)
                .findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new);
    }

    @Override
    public void setAll() {
        try {
            orderList = serialization.customUnmarshaller(this).readAll();
        } catch (FileNotExistException e) {
            orderList = new ArrayList<>();
            LOGGER.log(Level.WARNING, e.getMessage() + ", create empty list", e);
        }
    }

    @Override
    public void saveAll() {
        try {
            serialization.customMarshaller(this);
        } catch (FileNotExistException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }
}
