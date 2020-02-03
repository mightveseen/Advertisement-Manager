package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "orderDao")
public class OrderDao implements IOrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDao.class.getName());
    @XmlElementWrapper(name = "orderList")
    @XmlElement(name = "order")
    private List<Order> orderList;

    @Override
    public void create(Order order) {
        orderList.add(order);
        Serialization.getInstance().customMarshaller(this);
    }

    @Override
    public void delete(int index) {
        orderList.remove(orderList.stream().filter(i -> i.getId() == index).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new));
        Serialization.getInstance().customMarshaller(this);
    }

    @Override
    public void update(Order order) {
        orderList.set(orderList.indexOf(orderList.stream().filter(i -> i.getId() == order.getId()).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new)), order);
        Serialization.getInstance().customMarshaller(this);
    }

    @Override
    public List<Order> readAll() {
        return new ArrayList<>(orderList);
    }

    @Override
    public Order read(int index) {
        return orderList.stream().filter(i -> i.getId() == index).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new);
    }

    @Override
    public void setAll() {
        try {
            orderList = Serialization.getInstance().customUnmarshaller(this).readAll();
        } catch (RuntimeException e) {
            orderList = new ArrayList<>();
            LOGGER.log(Level.WARNING, e.getMessage() + ", create empty list", e);
        }
    }
}
