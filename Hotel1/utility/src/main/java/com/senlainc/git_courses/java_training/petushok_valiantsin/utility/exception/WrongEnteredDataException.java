package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception;

public class WrongEnteredDataException extends RuntimeException {
    public WrongEnteredDataException(String reason) {
        super(reason);
    }

    public WrongEnteredDataException(String reason, Throwable stackTrace) {
        super(reason, stackTrace);
    }
}
