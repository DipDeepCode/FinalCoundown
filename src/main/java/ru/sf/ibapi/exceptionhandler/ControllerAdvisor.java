package ru.sf.ibapi.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.sf.ibapi.exceptions.BalanceException;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(BalanceException.class)
    public ResponseEntity<ErrorResponse> handleBalanceException(BalanceException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return buildErrorResponse(e, status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return buildErrorResponse(e, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return buildErrorResponse(e, status);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception e, HttpStatus status) {
        ErrorResponse response = new ErrorResponse(status.value(), e.getMessage());
        return new ResponseEntity<>(response, status);
    }

}
