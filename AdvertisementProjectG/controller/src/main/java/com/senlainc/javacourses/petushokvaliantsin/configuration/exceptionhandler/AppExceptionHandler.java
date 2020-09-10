package com.senlainc.javacourses.petushokvaliantsin.configuration.exceptionhandler;

import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityAlreadyExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotAvailableException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.ExceededLimitException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.IncorrectCastException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.MappingException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.PermissionDeniedException;
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

    @ExceptionHandler({ExceededLimitException.class})
    public ResponseEntity<ExceptionTemplate> notAcceptable(RuntimeException exc) {
        logger.warn(exc.getMessage());
        return new ResponseEntity<>(ExceptionTemplate.of(exc.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler({EntityNotAvailableException.class, PermissionDeniedException.class})
    public ResponseEntity<ExceptionTemplate> forbidden(RuntimeException exc) {
        logger.warn(exc.getMessage());
        return new ResponseEntity<>(ExceptionTemplate.of(exc.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({EntityAlreadyExistException.class, EntityNotExistException.class})
    public ResponseEntity<ExceptionTemplate> conflict(RuntimeException exc) {
        logger.warn(exc.getMessage());
        return new ResponseEntity<>(ExceptionTemplate.of(exc.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({WrongEnteredDataException.class, IncorrectCastException.class, MappingException.class})
    public ResponseEntity<ExceptionTemplate> badRequest(RuntimeException exc) {
        logger.warn(exc.getMessage());
        return new ResponseEntity<>(ExceptionTemplate.of(exc.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ReadQueryException.class, DeleteQueryException.class, UpdateQueryException.class, CreateQueryException.class})
    public ResponseEntity<ExceptionTemplate> internalServerError(RuntimeException exc) {
        logger.warn(exc.getMessage());
        return new ResponseEntity<>(ExceptionTemplate.of(exc.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
