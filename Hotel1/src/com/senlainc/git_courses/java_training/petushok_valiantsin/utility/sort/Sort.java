package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.sort;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;

import java.util.Comparator;

public enum Sort {
    ATTENDANCE {
        public Comparator<Attendance> getComparator(SortParameter parameter) {
            if (parameter.equals(SortParameter.SECTION)) {
                return Comparator.comparing(Attendance::getSection);
            }
            if (parameter.equals(SortParameter.PRICE)) {
                return Comparator.comparing(Attendance::getPrice);
            }
            return Comparator.comparing(Attendance::getId); // Default
        }
    },
    ORDER {
        public Comparator<Order> getComparator(SortParameter parameter) {
            if (parameter.equals(SortParameter.DATE)) {
                return Comparator.comparing(Order::getEndDate);
            }
            return Comparator.comparing(Order::getId); // Default
        }
    },
    GUEST {
        public Comparator<Guest> getComparator(SortParameter parameter) {
            if (parameter.equals(SortParameter.ALPHABET)) {
                return Comparator.comparing(Guest::getFirstName);
            }
            return Comparator.comparing(Guest::getId); // Default
        }
    },
    ROOM {
        public Comparator<Room> getComparator(SortParameter parameter) {
            if (parameter.equals(SortParameter.PRICE)) {
                return Comparator.comparing(Room::getPrice);
            }
            if (parameter.equals(SortParameter.CLASSIFICATION)) {
                return Comparator.comparing(Room::getClassification);
            }
            if (parameter.equals(SortParameter.ROOM_NUMBER)) {
                return Comparator.comparing(Room::getRoomNumber);
            }
            return Comparator.comparing(Room::getId); // Default
        }
    };

    public abstract <T> Comparator<T> getComparator(SortParameter parameter);
}
