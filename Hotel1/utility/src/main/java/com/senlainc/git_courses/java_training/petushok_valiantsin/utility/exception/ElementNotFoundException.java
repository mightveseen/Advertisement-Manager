package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception;

public class ElementNotFoundException extends RuntimeException {

    public ElementNotFoundException(String message, Throwable stackTrace) {
        super(message, stackTrace);
    }

    public ElementNotFoundException(String message) {
        super(message);
    }
}
