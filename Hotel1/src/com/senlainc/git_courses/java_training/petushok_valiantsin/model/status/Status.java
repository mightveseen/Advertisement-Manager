package com.senlainc.git_courses.java_training.petushok_valiantsin.model.status;

public abstract class Status {
    protected final String status;

    public Status() {
        this.status = this.getClass().getSimpleName();
    }

    public String getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
