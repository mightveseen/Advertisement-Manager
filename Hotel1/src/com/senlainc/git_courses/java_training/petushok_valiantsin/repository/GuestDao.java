package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.ConnectionManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QueryDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QueryType;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.DaoException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotFoundException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@DependencyClass
@DependencyPrimary
public class GuestDao implements IGuestDao {
    private static final String ERROR = "Error during connection to Database. Check query.";
    @DependencyComponent
    private ConnectionManager connectionManager;

    @Override
    public void create(Guest guest) {
        final String QAURY = QueryDao.GUEST.getQuery(QueryType.CREATE);
        try (PreparedStatement statement = connectionManager.getStatment(QAURY)) {
            statement.setString(1, guest.getFirstName());
            statement.setString(2, guest.getSecondName());
            statement.setDate(3, Date.valueOf(guest.getBirthday()));
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(ERROR, e);
        }
    }

    @Override
    public void delete(Integer index) {
        final String QUERY = QueryDao.GUEST.getQuery(QueryType.DELETE);
        try (PreparedStatement statement = connectionManager.getStatment(QUERY)) {
            statement.setInt(1, index);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(ERROR, e);
        }
    }

    @Override
    public void update(Guest guest) {
        final String QUERY = QueryDao.GUEST.getQuery(QueryType.UPDATE);
        try (PreparedStatement statement = connectionManager.getStatment(QUERY)) {
            statement.setString(1, guest.getFirstName());
            statement.setString(2, guest.getSecondName());
            statement.setDate(3, Date.valueOf(guest.getBirthday()));
            statement.setInt(4, guest.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Guest> readAll() {
        final String QUERY = QueryDao.GUEST.getQuery(QueryType.READ_ALL);
        final List<Guest> guestList = new ArrayList<>();
        try (PreparedStatement statement = connectionManager.getStatment(QUERY)) {
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                guestList.add(createFromQuery(result));
            }
            result.close();
        } catch (SQLException e) {
            throw new DaoException(ERROR, e);
        }
        return guestList;
    }

    @Override
    public Integer readSize() {
        final String QUERY = QueryDao.GUEST.getQuery(QueryType.SIZE);
        try (PreparedStatement statement = connectionManager.getStatment(QUERY)) {
            final ResultSet result = statement.executeQuery();
            if (result.next()) {
                final Integer size = result.getInt(1);
                result.close();
                return size;
            }
            throw new ElementNotFoundException();
        } catch (SQLException e) {
            throw new DaoException(ERROR, e);
        }
    }

    @Override
    public Guest read(Integer index) {
        final String QUERY = QueryDao.GUEST.getQuery(QueryType.READ);
        try (PreparedStatement statement = connectionManager.getStatment(QUERY)) {
            statement.setInt(1, index);
            final ResultSet result = statement.executeQuery();
            if (result.next()) {
                final Guest guest = createFromQuery(result);
                result.close();
                return guest;
            }
            throw new ElementNotFoundException();
        } catch (SQLException e) {
            throw new DaoException(ERROR, e);
        }
    }

    private Guest createFromQuery(ResultSet result) throws SQLException {
        final Guest guest = new Guest(result.getString("first_name"), result.getString("second_name"), result.getDate("birthday").toLocalDate());
        guest.setId(result.getInt("id"));
        return guest;
    }
}
