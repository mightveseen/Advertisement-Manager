package com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao;

public final class UpdateQueryException extends DaoException {

    public UpdateQueryException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
