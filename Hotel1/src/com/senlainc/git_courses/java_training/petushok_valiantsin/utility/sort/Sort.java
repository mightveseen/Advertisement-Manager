package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.sort;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Order;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;

import java.util.Comparator;

public enum Sort {
    ATTENDANCE {
        private final Comparator<Attendance> SECTION = Comparator.comparing(Attendance::getSection);
        private final Comparator<Attendance> PRICE = Comparator.comparing(Attendance::getPrice);
        private final Comparator<Attendance> DEFAULT = Comparator.comparing(Attendance::getId);

        public Comparator<Attendance> getComparator(String parameter) {
            if (parameter.toUpperCase().equals("SECTION")) {
                return SECTION;
            }
            if (parameter.toUpperCase().equals("PRICE")) {
                return PRICE;
            }
            return DEFAULT;
        }
    },
    ORDER {
        private final Comparator<Order> DATE = Comparator.comparing(Order::getEndDate);
        private final Comparator<Order> DEFAULT = Comparator.comparing(Order::getId);

        public Comparator<Order> getComparator(String parameter) {
            if (parameter.toUpperCase().equals("DATE")) {
                return DATE;
            }
            return DEFAULT;
        }
    },
    GUEST {
        private final Comparator<Guest> ALPHABET = Comparator.comparing(Guest::getFirstName);
        private final Comparator<Guest> DEFAULT = Comparator.comparing(Guest::getId);

        public Comparator<Guest> getComparator(String parameter) {
            if (parameter.toUpperCase().equals("ALPHABET")) {
                return ALPHABET;
            }
            return DEFAULT;
        }
    },
    ROOM {
        private final Comparator<Room> PRICE = Comparator.comparing(Room::getPrice);
        private final Comparator<Room> CLASSIFICATION = Comparator.comparing(Room::getClassification);
        private final Comparator<Room> ROOM_NUMBER = Comparator.comparing(Room::getRoomNumber);
        private final Comparator<Room> DEFAULT = Comparator.comparing(Room::getId);

        public Comparator<Room> getComparator(String parameter) {
            if (parameter.toUpperCase().equals("PRICE")) {
                return PRICE;
            }
            if (parameter.toUpperCase().equals("CLASSIFICATION")) {
                return CLASSIFICATION;
            }
            if (parameter.toUpperCase().equals("ROOM_NUMBER")) {
                return ROOM_NUMBER;
            }
            return DEFAULT;
        }
    };

    public abstract <T> Comparator<T> getComparator(String parameter);
}
