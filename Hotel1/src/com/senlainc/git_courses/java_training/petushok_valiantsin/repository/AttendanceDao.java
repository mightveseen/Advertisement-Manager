package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization.Serialization;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "attendanceDao")
public class AttendanceDao implements IAttendanceDao {
    private static final Logger LOGGER = Logger.getLogger(AttendanceDao.class.getName());
    @XmlElementWrapper(name = "attendanceList")
    @XmlElement(name = "attendance")
    private List<Attendance> attendanceList;

    @Override
    public void create(Attendance attendance) {
        attendanceList.add(attendance);
        Serialization.getInstance().customMarshaller(this);
    }

    @Override
    public void delete(int index) {
        attendanceList.remove(attendanceList.stream().filter(i -> i.getId() == index).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new));
        Serialization.getInstance().customMarshaller(this);
    }

    @Override
    public void update(Attendance attendance) {
        attendanceList.set(attendanceList.indexOf(attendanceList.stream().filter(i -> i.getId() == attendance.getId()).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new)), attendance);
        Serialization.getInstance().customMarshaller(this);
    }

    @Override
    public List<Attendance> readAll() {
        return new ArrayList<>(attendanceList);
    }

    @Override
    public Attendance read(int index) {
        return attendanceList.stream().filter(i -> i.getId() == index).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new);
    }

    public void setAll() {
        try {
            attendanceList = Serialization.getInstance().customUnmarshaller(this).readAll();
        } catch (RuntimeException e) {
            attendanceList = new ArrayList<>();
            LOGGER.log(Level.WARNING, e.getMessage() + ", create empty list", e);
        }
    }
}
