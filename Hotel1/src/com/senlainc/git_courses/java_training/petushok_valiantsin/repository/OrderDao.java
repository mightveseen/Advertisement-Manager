package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization.Serialization;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "orderDao")
public class OrderDao implements IOrderDao {
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
    }

    @Override
    public void update(Order order) {
        orderList.set(orderList.indexOf(orderList.stream().filter(i -> i.getId() == order.getId()).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new)), order);
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
        orderList = Serialization.getInstance().customUnmarshaller(this).readAll();
    }
}
