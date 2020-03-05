package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception;

public class DaoException extends RuntimeException {
    public DaoException(String reason) {
        super(reason);
    }

    public DaoException(Throwable stackTrace) {
        super(stackTrace);
    }

    public DaoException() {
        super();
    }

    public DaoException(String message, Throwable stackTrace) {
        super(message, stackTrace);
    }
}
