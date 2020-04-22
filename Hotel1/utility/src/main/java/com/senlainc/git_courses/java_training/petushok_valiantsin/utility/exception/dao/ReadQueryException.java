package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao;

public class ReadQueryException extends RuntimeException {

    public ReadQueryException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadQueryException(String message) {
        super(message);
    }
}
