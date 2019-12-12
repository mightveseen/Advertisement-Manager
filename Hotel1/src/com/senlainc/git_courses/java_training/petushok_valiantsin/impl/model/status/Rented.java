package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status;

import java.time.LocalDate;

public class Rented extends Status {
    private LocalDate startDate;
    private LocalDate endDate;

    public Rented (String status, LocalDate startDate, LocalDate endDate) {
        super(status);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
