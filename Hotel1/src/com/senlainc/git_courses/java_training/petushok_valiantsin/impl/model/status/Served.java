package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status;

import java.time.LocalTime;

public class Served extends Status {
    protected String reason;
    protected LocalTime endTime;

    public Served(String reason, LocalTime endTime) {
        super();
        this.reason = reason;
        this.endTime = endTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return status + " [" + reason + ", " + endTime + "] ";
    }
}
