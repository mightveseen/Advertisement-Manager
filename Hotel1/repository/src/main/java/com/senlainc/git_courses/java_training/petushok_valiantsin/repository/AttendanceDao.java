package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import org.springframework.stereotype.Repository;

@Repository
public class AttendanceDao extends AbstractDao<Attendance, Long> implements IAttendanceDao {

}
