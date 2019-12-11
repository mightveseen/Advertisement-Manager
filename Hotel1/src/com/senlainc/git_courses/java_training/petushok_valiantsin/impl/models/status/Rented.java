package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.models.status;

import java.time.LocalDateTime;

public class Rented extends Status {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Rented (String status, LocalDateTime startDate, LocalDateTime endDate) {
        super(status);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
