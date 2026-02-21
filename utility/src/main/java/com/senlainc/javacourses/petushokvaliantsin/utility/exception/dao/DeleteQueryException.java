package com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao;

public final class DeleteQueryException extends DaoException {

    public DeleteQueryException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
