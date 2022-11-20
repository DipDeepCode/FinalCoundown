package ru.sf.ibapi.exceptionhandler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.sf.ibapi.apiresponses.responsebuilder.ApiResponseBuilder;
import ru.sf.ibapi.apiresponses.responses.ApiResponse;
import ru.sf.ibapi.apiresponses.responses.ApiResponseCode;
import ru.sf.ibapi.exceptions.ChangeBalanceException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private final ApiResponseBuilder apiResponseBuilder;

    @ExceptionHandler(ChangeBalanceException.class)
    protected ResponseEntity<Object> handleApiExceptions(ChangeBalanceException ex) {
        ApiResponse response = apiResponseBuilder.buildErrorResponse(ApiResponseCode.CHANGE_BALANCE_ERROR, ex.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<String> messages = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(objectError -> messages.add(objectError.getDefaultMessage()));
        ApiResponse response = apiResponseBuilder.buildErrorResponse(ApiResponseCode.ARGUMENT_NOT_VALID, messages.toString());
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException (EntityNotFoundException ex) {
        ApiResponse response = apiResponseBuilder.buildErrorResponse(ApiResponseCode.CUSTOMER_NOT_FOUND, ex.getMessage());
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {
        ApiResponse response = apiResponseBuilder.buildErrorResponse(ApiResponseCode.GENERAL_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, status);
    }
}
