package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization.Serialization;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "attendanceDao")
public class AttendanceDao implements IAttendanceDao {
    @XmlElementWrapper(name = "attendanceList")
    @XmlElement(name = "attendance")
    private final List<Attendance> attendanceList = new ArrayList<>();

    @Override
    public void create(Attendance attendance) {
        attendanceList.add(attendance);
        Serialization.getInstance().customMarshaller(this);
        Serialization.getInstance().customUnmarshaller(this);
    }

    @Override
    public void delete(int index) {
        attendanceList.remove(attendanceList.stream().filter(i -> i.getId() == index).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new));
    }

    @Override
    public void update(Attendance attendance) {
        attendanceList.set(attendanceList.indexOf(attendanceList.stream().filter(i -> i.getId() == attendance.getId()).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new)), attendance);
    }

    @Override
    public List<Attendance> readAll() {
        return new ArrayList<>(attendanceList);
    }

    @Override
    public Attendance read(int index) {
        return attendanceList.stream().filter(i -> i.getId() == index).findFirst().orElseThrow(ArrayIndexOutOfBoundsException::new);
    }
}
