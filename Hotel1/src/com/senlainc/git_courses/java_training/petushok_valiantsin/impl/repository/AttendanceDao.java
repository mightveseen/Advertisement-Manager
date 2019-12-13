package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.utility.MyList;

public class AttendanceDao implements IAttendanceDao {
    private MyList<Attendance> attendanceMyList = new MyList<>();

    @Override
    public void create(Attendance attendance) {
        attendanceMyList.add(attendance);
    }

    @Override
    public void delete(int index) {
        attendanceMyList.remove(index);
    }

    @Override
    public void update(int index, Attendance attendance) {
        attendanceMyList.add(index, attendance);
    }

    @Override
    public MyList<Attendance> readAll() {
        return attendanceMyList;
    }

    @Override
    public Attendance read(int index) {
        return attendanceMyList.get(index);
    }

//    private Comparator<Attendance> SORT_BY_PRICE = new Comparator<Attendance>() {
//        @Override
//        public int compare(Attendance firstAttendance, Attendance secondAttendance) {
//            return String.valueOf(firstAttendance.getPrice()).compareTo(String.valueOf(secondAttendance.getPrice()));
//        }
//    };
//    private Comparator<Attendance> SORT_BY_SECTION = new Comparator<Attendance>() {
//        @Override
//        public int compare(Attendance firstAttendance, Attendance secondAttendance) {
//            return firstAttendance.getSection().compareTo(secondAttendance.getSection());
//        }
//    };
//
//    @Override
//    public List<Attendance> sortByPrice() {
//        List<Attendance> bufList = new ArrayList<>();
//        bufList.sort(SORT_BY_PRICE);
//        return bufList;
//    }
//
//    @Override
//    public List<Attendance> sortBySection() {
//        List<Attendance> bufList = new ArrayList<>();
//        bufList.sort(SORT_BY_SECTION);
//        return bufList;
//    }
}
