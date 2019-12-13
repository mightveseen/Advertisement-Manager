package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.utility.MyList;

public class GuestDao implements IGuestDao {
    private MyList<Guest> guestMyList = new MyList<>();

    @Override
    public void create(Guest guest) {
        guestMyList.add(guest);
    }

    @Override
    public void delete(int index) {
        guestMyList.remove(index);
    }

    @Override
    public void update(int index, Guest guest) {
        guestMyList.add(index, guest);
    }

    @Override
    public MyList<Guest> readAll() {
        return guestMyList;
    }

    @Override
    public Guest read(int index) {
        return guestMyList.get(index);
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
