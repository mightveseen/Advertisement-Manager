package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.models.status;

public abstract class Status {
    protected String status;

    public Status(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
