package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.CustomEntityManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QueryDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QueryType;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.DaoException;

import javax.persistence.EntityManager;
import java.util.List;

@DependencyClass
@DependencyPrimary
public class RoomDao extends AbstractDao<Room, Long> implements IRoomDao {

    private static final String ERROR = "Error during connection to Database. Check query.";
    private final EntityManager entityManager;

    public RoomDao() {
        super(Room.class);
        this.entityManager = CustomEntityManager.getEntityManager();
    }

    @Override
    public List<Room> readAllFree() {
        try {
            return entityManager.createQuery("FROM " + getTableName() + " WHERE `status` = 'FREE';").getResultList();
        } catch (Exception e) {
            throw new DaoException(ERROR, e);
        }
    }

    @Override
    public Integer readFreeSize() {
        try {
            return entityManager.createQuery(QueryDao.ROOM.getQuery(QueryType.FREE_SIZE)).getFirstResult();
        } catch (Exception e) {
            throw new DaoException(ERROR, e);
        }
    }

    @Override
    public Room readByNumber(Integer number) {
        try {
            return (Room) entityManager.createQuery(QueryDao.ROOM.getQuery(QueryType.READ_BY_NUMBER)).getSingleResult();
        } catch (Exception e) {
            throw new DaoException(ERROR, e);
        }
    }

}
