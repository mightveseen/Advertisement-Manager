package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QueryDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QueryType;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.DaoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@DependencyClass
@DependencyPrimary
public class OrderDao extends AbstractDao<Order, Long> implements IOrderDao {

    private static final String ERROR = "Error during connection to Database. Check query.";

    @Override
    public void update(Order order, Attendance attendance) {
        final String query = QueryDao.ORDER.getQuery(QueryType.ADD_ORDER_ATTENDANCE);
        try {

        } catch (Exception e) {
            throw new DaoException(ERROR, e);
        }
    }

    @Override
    public List<Room> readLastRoom(Long index) {
        final String query = QueryDao.ORDER.getQuery(QueryType.READ_LAST_ROOM);
        final List<Room> roomList = new ArrayList<>();
        try {

        } catch (Exception e) {
            throw new DaoException(ERROR, e);
        }
        return roomList;
    }

    @Override
    public List<Room> readAfterDate(LocalDate date) {
        final String query = QueryDao.ORDER.getQuery(QueryType.READ_AFTER_DATE);
        final List<Room> roomList = new ArrayList<>();
        try {

        } catch (Exception e) {
            throw new DaoException(ERROR, e);
        }
        return roomList;
    }
}
