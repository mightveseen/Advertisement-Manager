package com.senlainc.gitcourses.javatraining.petushokvaliantsin.exceptionhandler;

import com.senlainc.gitcourses.javatraining.petushokvaliantsin.utility.exception.ElementAlreadyExistsException;
import com.senlainc.gitcourses.javatraining.petushokvaliantsin.utility.exception.ElementNotAvailableException;
import com.senlainc.gitcourses.javatraining.petushokvaliantsin.utility.exception.ElementNotFoundException;
import com.senlainc.gitcourses.javatraining.petushokvaliantsin.utility.exception.dao.ReadQueryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<Object> elementNotFound(ElementNotFoundException e) {
        logger.info(e);
        return new ResponseEntity<>(new ExceptionTemplate(e.getMessage(), e.getStackTrace()[0]), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ElementNotAvailableException.class)
    public ResponseEntity<Object> elementNotAvailable(ElementNotAvailableException e) {
        logger.info(e);
        return new ResponseEntity<>(new ExceptionTemplate(e.getMessage(), e.getStackTrace()[0]), HttpStatus.LOCKED);
    }

    @ExceptionHandler(ElementAlreadyExistsException.class)
    public ResponseEntity<Object> elementAlreadyExists(ElementAlreadyExistsException e) {
        logger.info(e);
        return new ResponseEntity<>(new ExceptionTemplate(e.getMessage(), e.getStackTrace()[0]), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ReadQueryException.class)
    public ResponseEntity<Object> queryException(ReadQueryException e) {
        logger.info(e);
        return new ResponseEntity<>(new ExceptionTemplate(e.getMessage(), e.getStackTrace()[0]), HttpStatus.CONFLICT);
    }
}
