package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.ConnectionManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QuaryDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QuaryType;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.DaoException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@DependencyClass
@DependencyPrimary
public class AttendanceDao implements IAttendanceDao {
    @DependencyComponent
    private ConnectionManager connectionManager;

    @Override
    public Attendance create(Attendance attendance) {
        final String QUARY = QuaryDao.ATTENDANCE.getQuary(QuaryType.CREATE);
        try (final PreparedStatement statement = connectionManager.getStatment(QUARY)) {
            statement.setString(1, attendance.getName());
            statement.setString(2, attendance.getSection());
            statement.setDouble(3, attendance.getPrice());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return attendance;
    }

    @Override
    public void delete(Integer index) {
        final String QUARY = QuaryDao.ATTENDANCE.getQuary(QuaryType.DELETE);
        try (final PreparedStatement statement = connectionManager.getStatment(QUARY)) {
            statement.setInt(1, index);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Attendance attendance) {
        final String QUARY = QuaryDao.ATTENDANCE.getQuary(QuaryType.UPDATE);
        try (final PreparedStatement statement = connectionManager.getStatment(QUARY)) {
            statement.setString(1, attendance.getName());
            statement.setString(2, attendance.getSection());
            statement.setDouble(3, attendance.getPrice());
            statement.setInt(4, attendance.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Attendance> readAll() {
        final String QUARY = QuaryDao.ATTENDANCE.getQuary(QuaryType.READ_ALL);
        final List<Attendance> attendanceList = new ArrayList<>();
        try (final PreparedStatement statement = connectionManager.getStatment(QUARY)) {
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                attendanceList.add(createFromQuary(result));
            }
            return attendanceList;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Attendance read(Integer index) {
        final String QUARY = QuaryDao.ATTENDANCE.getQuary(QuaryType.READ);
        try (final PreparedStatement statement = connectionManager.getStatment(QUARY)) {
            statement.setInt(1, index);
            final ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createFromQuary(result);
            }
            throw new ElementNotFoundException();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Attendance createFromQuary(ResultSet result) throws SQLException {
        final Attendance attendance = new Attendance(result.getString(2), result.getString(3), result.getDouble(4));
        attendance.setId(result.getInt(1));
        return attendance;
    }
}
