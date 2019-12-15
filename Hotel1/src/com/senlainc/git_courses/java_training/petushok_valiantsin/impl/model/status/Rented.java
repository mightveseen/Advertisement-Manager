package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status;

import java.time.LocalDate;

public class Rented extends Status {
    private LocalDate startDate;
    private LocalDate endDate;

    public Rented(LocalDate endDate) {
        super();
        this.startDate = LocalDate.now();
        this.endDate = endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    @Override
    public String toString() {
        return status + " [" + startDate + ", " + endDate + "] ";
    }
}
