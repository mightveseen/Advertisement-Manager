package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.sort;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;

import java.util.Comparator;

public enum Sort {
    ATTENDANCE {
            public Comparator<Attendance> getComparator(SortParameter parameter) {
                switch (parameter) {
                    case PRICE:
                        return Comparator.comparing(Attendance::getSection);
                    case SECTION:
                        return Comparator.comparing(Attendance::getPrice);
                    default:
                        return Comparator.comparing(Attendance::getId);
                }
            }
    },
    ORDER {
        public Comparator<Order> getComparator(SortParameter parameter) {
            switch (parameter) {
                case DATE:
                    return Comparator.comparing(Order::getEndDate);
                case ALPHABET:
                    return Comparator.comparing(i -> i.getGuest().getFirstName());
                default:
                    return Comparator.comparing(Order::getId);
            }
        }
    },
    ROOM {
        public Comparator<Room> getComparator(SortParameter parameter) {
            switch (parameter) {
                case PRICE:
                    return Comparator.comparing(Room::getPrice);
                case CLASSIFICATION:
                    return Comparator.comparing(Room::getClassification);
                case ROOM_NUMBER:
                    return Comparator.comparing(Room::getRoomNumber);
                default:
                    return Comparator.comparing(Room::getId);
            }
        }
    };

    public abstract <T> Comparator<T> getComparator(SortParameter parameter);
}
