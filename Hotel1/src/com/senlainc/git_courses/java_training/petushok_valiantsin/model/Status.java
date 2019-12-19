package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

public class Status {
    public enum Type {
        FREE("Free"),
        RENTED("Rented"),
        SERVED("Served");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
