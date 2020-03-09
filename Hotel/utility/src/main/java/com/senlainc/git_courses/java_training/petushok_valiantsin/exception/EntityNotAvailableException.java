package com.senlainc.git_courses.java_training.petushok_valiantsin.exception;

public class EntityNotAvailableException extends RuntimeException {
    public EntityNotAvailableException(String reason) {
        super(reason);
    }
}
