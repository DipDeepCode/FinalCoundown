package ru.sf.ibapi.exceptionhandler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.sf.ibapi.apiresponses.ApiResponse;
import ru.sf.ibapi.apiresponses.ApiResponseBuilder;
import ru.sf.ibapi.exceptions.ChangeBalanceException;
import static ru.sf.ibapi.apiresponses.ApiResponseCodes.CHANGE_BALANCE_ERROR;
import static ru.sf.ibapi.apiresponses.ApiResponseCodes.GENERAL_ERROR;

@RequiredArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private final ApiResponseBuilder apiResponseBuilder;

    @ExceptionHandler(ChangeBalanceException.class)
    public ResponseEntity<ApiResponse> handleChangeBalanceException(ChangeBalanceException e) {
        ApiResponse response = apiResponseBuilder.buildErrorResponse(CHANGE_BALANCE_ERROR, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception e) {
        ApiResponse response = apiResponseBuilder.buildErrorResponse(GENERAL_ERROR, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
