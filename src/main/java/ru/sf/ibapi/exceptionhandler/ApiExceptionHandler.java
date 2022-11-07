package ru.sf.ibapi.exceptionhandler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.sf.ibapi.apiresponses.responses.ApiResponse;
import ru.sf.ibapi.apiresponses.responsebuilder.ApiResponseBuilder;
import ru.sf.ibapi.exceptions.ChangeBalanceException;

import static ru.sf.ibapi.apiresponses.responsecodes.ApiResponseCodes.GENERAL_ERROR;

@RequiredArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private final ApiResponseBuilder apiResponseBuilder;

    @ExceptionHandler(ChangeBalanceException.class)
    protected ResponseEntity<Object> handleApiExceptions(ChangeBalanceException ex) {
        ApiResponse response = apiResponseBuilder.buildErrorResponse(ex.getCode(), ex.getMessage());
        HttpStatus status = ex.getStatus();
        return new ResponseEntity<>(response, status);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {
        ApiResponse response = apiResponseBuilder.buildErrorResponse(GENERAL_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, status);
    }
}
