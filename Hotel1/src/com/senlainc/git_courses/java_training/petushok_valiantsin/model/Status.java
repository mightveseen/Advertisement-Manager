package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

public class Status {
    public enum StatusType {
        FREE("Free"),
        RENTED("Rented"),
        SERVED("Served");

        private final String name;

        StatusType(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
