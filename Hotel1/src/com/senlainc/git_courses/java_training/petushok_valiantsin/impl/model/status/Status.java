package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status;

public abstract class Status {
    protected String status;

    public Status() {
        this.status = this.getClass().getSimpleName();
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }
}
