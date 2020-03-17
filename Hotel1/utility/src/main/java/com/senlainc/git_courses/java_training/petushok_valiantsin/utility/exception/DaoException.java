package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception;

public class DaoException extends RuntimeException {

    public DaoException(Throwable stackTrace) {
        super(stackTrace);
    }

    public DaoException(String message, Throwable stackTrace) {
        super(message, stackTrace);
    }
}
