package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception;

public class DaoException extends RuntimeException {
    public DaoException(String reason) {
        super(reason);
    }

    public DaoException(String reason, Throwable stackTrace) {
        super(reason, stackTrace);
    }
}
