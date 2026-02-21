package com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao;

public final class ReadQueryException extends DaoException {

    public ReadQueryException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
