package com.senlainc.javacourses.petushokvaliantsin.configuration.exceptionhandler;

import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityAlreadyExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.IncorrectCastException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.WrongEnteredDataException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.CreateQueryException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.DeleteQueryException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.ReadQueryException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.UpdateQueryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({WrongEnteredDataException.class, EntityAlreadyExistException.class, EntityNotExistException.class, IncorrectCastException.class})
    public ResponseEntity<Object> badRequest(RuntimeException exc) {
        return new ResponseEntity<>(ExceptionTemplate.of(exc.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ReadQueryException.class, DeleteQueryException.class, UpdateQueryException.class, CreateQueryException.class})
    public ResponseEntity<Object> internalServerError(RuntimeException exc) {
        return new ResponseEntity<>(ExceptionTemplate.of(exc.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
