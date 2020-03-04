package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.ConnectionManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.DaoException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@DependencyClass
@DependencyPrimary
public class GuestDao implements IGuestDao {
    @DependencyComponent
    private ConnectionManager connectionManager;

    @Override
    public void create(Guest guest) {
        final String SQL_CREATE_QUARY = "INSERT INTO `Guest`(`first_name`, `second_name`, `birthday`, `info_contact`) VALUES (?, ?, ?, ?);";
        try (PreparedStatement statement = connectionManager.getStatment(SQL_CREATE_QUARY)) {
            statement.setString(1, guest.getFirstName());
            statement.setString(2, guest.getSecondName());
            statement.setDate(3, Date.valueOf(guest.getBirthday()));
            statement.setString(4, guest.getInfoContact());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer index) {
        final String SQL_DELETE_QUARY = "DELETE FROM `Guest` WHERE `id` = ?;";
        try (PreparedStatement statement = connectionManager.getStatment(SQL_DELETE_QUARY)) {
            statement.setInt(1, index);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Guest guest) {
        final String SQL_UPDATE_QUARY = "UPDATE `Guest` SET `first_name` = ?, `second_name` = ?, `birthday` = ?, `info_contact` = ? WHERE `id` = ?;";
        try (PreparedStatement statement = connectionManager.getStatment(SQL_UPDATE_QUARY)) {
            statement.setString(1, guest.getFirstName());
            statement.setString(2, guest.getSecondName());
            statement.setDate(3, Date.valueOf(guest.getBirthday()));
            statement.setString(4, guest.getInfoContact());
            statement.setInt(5, guest.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Guest> readAll() {
        final String SQL_READ_ALL_QUARY = "SELECT * FROM `Guest`;";
        final List<Guest> guestList = new ArrayList<>();
        try (PreparedStatement statement = connectionManager.getStatment(SQL_READ_ALL_QUARY)) {
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                final Guest guest = new Guest(result.getString(2), result.getString(3), result.getDate(4).toLocalDate(), result.getString(5));
                guest.setId(result.getInt(1));
                guestList.add(guest);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return guestList;
    }

    @Override
    public Guest read(Integer index) {
        final String SQL_READ_QUARY = "SELECT * FROM `Guest` WHERE `id` = ?;";
        try (PreparedStatement statement = connectionManager.getStatment(SQL_READ_QUARY)) {
            statement.setInt(1, index);
            final ResultSet result = statement.executeQuery();
            if (result.next()) {
                final Guest guest = new Guest(result.getString(2), result.getString(3), result.getDate(4).toLocalDate(), result.getString(5));
                guest.setId(result.getInt(1));
                return guest;
            }
            throw new DaoException();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
