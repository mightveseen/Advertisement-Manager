package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String reason, Throwable stackTrace) {
        super(reason, stackTrace);
    }

    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(String reason) {
        super(reason);
    }
}
