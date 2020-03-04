package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.ConnectionManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QuaryDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QuaryType;
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
    @DependencyComponent
    private ConnectionManager connectionManager;

    @Override
    public void create(Guest guest) {
        final String QAURY = QuaryDao.GUEST.getQuary(QuaryType.CREATE);
        try (PreparedStatement statement = connectionManager.getStatment(QAURY)) {
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
        final String QUARY = QuaryDao.GUEST.getQuary(QuaryType.DELETE);
        try (PreparedStatement statement = connectionManager.getStatment(QUARY)) {
            statement.setInt(1, index);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Guest guest) {
        final String QUARY = QuaryDao.GUEST.getQuary(QuaryType.UPDATE);
        try (PreparedStatement statement = connectionManager.getStatment(QUARY)) {
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
        final String QUARY = QuaryDao.GUEST.getQuary(QuaryType.READ_ALL);
        final List<Guest> guestList = new ArrayList<>();
        try (PreparedStatement statement = connectionManager.getStatment(QUARY)) {
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                guestList.add(createFromQuary(result));
            }
            result.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return guestList;
    }

    @Override
    public Guest read(Integer index) {
        final String QUARY = QuaryDao.GUEST.getQuary(QuaryType.READ);
        try (PreparedStatement statement = connectionManager.getStatment(QUARY)) {
            statement.setInt(1, index);
            final ResultSet result = statement.executeQuery();
            if (result.next()) {
                final Guest guest = createFromQuary(result);
                result.close();
                return guest;
            }
            throw new ElementNotFoundException();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Guest createFromQuary(ResultSet result) throws SQLException {
        final Guest guest = new Guest(result.getString(2), result.getString(3), result.getDate(4).toLocalDate(), result.getString(5));
        guest.setId(result.getInt(1));
        return guest;
    }
}
