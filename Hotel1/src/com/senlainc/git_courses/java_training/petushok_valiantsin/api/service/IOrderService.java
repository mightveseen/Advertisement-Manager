package com.senlainc.git_courses.java_training.petushok_valiantsin.api.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.MyList;

public interface IOrderService {
    void add(Order order);

    void delete(int index);

    void show();

    void show(MyList<Order> myList);

    void showGuestRoom(int index);

    MyList<Order> sort(String parameter);

    void addAttendance(int orderIndex, int attendanceIndex);

    void showAttendance(int orderIndex);
}
