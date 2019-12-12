package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status;

import java.time.LocalDateTime;

public class Served extends Status {
    private String reason;
    private LocalDateTime endTime;

    public Served(String reason, LocalDateTime endTime) {
        this.reason = reason;
        this.endTime = endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
