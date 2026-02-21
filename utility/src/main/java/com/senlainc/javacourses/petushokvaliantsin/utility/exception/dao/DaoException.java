package com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao;

import com.senlainc.javacourses.petushokvaliantsin.utility.exception.AppException;

public abstract sealed class DaoException extends AppException
        permits CreateQueryException, ReadQueryException, UpdateQueryException, DeleteQueryException {

    protected DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
