package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

public class Status {
    public enum RoomStatus {
        FREE("Free"),
        RENTED("Rented"),
        SERVED("Served");

        private final String name;

        RoomStatus(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    public enum OrderStatus {
        ACTIVE("Active"),
        DISABLED("Disabled");

        private final String name;

        OrderStatus(String name) {
            this.name = name;
        }
    }
}
