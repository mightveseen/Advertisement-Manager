package com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao;

public final class CreateQueryException extends DaoException {

    public CreateQueryException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
