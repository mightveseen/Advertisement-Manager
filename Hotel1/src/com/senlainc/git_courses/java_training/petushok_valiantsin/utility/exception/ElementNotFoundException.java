package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String reason) {
        super(reason);
    }

    public ElementNotFoundException(String reason, Throwable stackTrace) {
        super(reason, stackTrace);
    }
}
