package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IOrderDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.DaoException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

public class OrderDao extends AbstractDao<Order, Long> implements IOrderDao {

    private static final String ERROR = "Error during connection to Database. Check query.";

    @Override
    public void update(Order order, Attendance attendance) {
        try {
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        } catch (Exception e) {
            throw new DaoException(ERROR, e);
        }
    }

    @Override
    public List<Room> readLastRoom(Long index, Integer limit) {
        try {
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
            final Root<Room> root = criteriaQuery.from(Room.class);
            final Join<Room, Order> join = root.join("order");
            final Predicate predicate = criteriaBuilder.equal(join.get("guest").get("id"), index);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .where(predicate))
                    .setMaxResults(limit)
                    .getResultList();
        } catch (Exception e) {
            throw new DaoException(ERROR, e);
        }
    }

    @Override
    public List<Room> readAfterDate(LocalDate date) {
        try {
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
            final Root<Room> root = criteriaQuery.from(Room.class);
            final Join<Order, Room> join = root.join("order");
            final Predicate predicate = criteriaBuilder.lessThan(join.get("endDate"), date);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .where(predicate))
                    .getResultList();
        } catch (Exception e) {
            throw new DaoException(ERROR, e);
        }
    }
}
