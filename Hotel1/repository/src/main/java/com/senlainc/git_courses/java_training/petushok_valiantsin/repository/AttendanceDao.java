package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.ConnectionManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QueryDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QueryType;
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
    private static final String ERROR = "Error during connection to Database. Check query.";
    @DependencyComponent
    private ConnectionManager connectionManager;

    @Override
    public void create(Attendance attendance) {
        final String QUERY = QueryDao.ATTENDANCE.getQuery(QueryType.CREATE);
        try (final PreparedStatement statement = connectionManager.getConnection().prepareStatement(QUERY)) {
            statement.setString(1, attendance.getName());
            statement.setString(2, attendance.getSection());
            statement.setDouble(3, attendance.getPrice());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(ERROR, e);
        }
    }

    @Override
    public void delete(Integer index) {
        final String QUERY = QueryDao.ATTENDANCE.getQuery(QueryType.DELETE);
        try (final PreparedStatement statement = connectionManager.getConnection().prepareStatement(QUERY)) {
            statement.setInt(1, index);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(ERROR, e);
        }
    }

    @Override
    public void update(Attendance attendance) {
        final String QUERY = QueryDao.ATTENDANCE.getQuery(QueryType.UPDATE);
        try (final PreparedStatement statement = connectionManager.getConnection().prepareStatement(QUERY)) {
            statement.setString(1, attendance.getName());
            statement.setString(2, attendance.getSection());
            statement.setDouble(3, attendance.getPrice());
            statement.setInt(4, attendance.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(ERROR, e);
        }
    }

    @Override
    public List<Attendance> readAll() {
        final String QUERY = QueryDao.ATTENDANCE.getQuery(QueryType.READ_ALL);
        final List<Attendance> attendanceList = new ArrayList<>();
        try (final PreparedStatement statement = connectionManager.getConnection().prepareStatement(QUERY)) {
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                attendanceList.add(createFromQuery(result));
            }
            result.close();
        } catch (SQLException e) {
            throw new DaoException(ERROR, e);
        }
        return attendanceList;
    }

    @Override
    public Attendance read(Integer index) {
        final String QUERY = QueryDao.ATTENDANCE.getQuery(QueryType.READ);
        try (final PreparedStatement statement = connectionManager.getConnection().prepareStatement(QUERY)) {
            statement.setInt(1, index);
            final ResultSet result = statement.executeQuery();
            if (result.next()) {
                final Attendance attendance = createFromQuery(result);
                result.close();
                return attendance;
            }
            throw new ElementNotFoundException();
        } catch (SQLException e) {
            throw new DaoException(ERROR, e);
        }
    }

    private Attendance createFromQuery(ResultSet result) throws SQLException {
        final Attendance attendance = new Attendance(result.getString("name"), result.getString("section"), result.getDouble("price"));
        attendance.setId(result.getInt("id"));
        return attendance;
    }
}
