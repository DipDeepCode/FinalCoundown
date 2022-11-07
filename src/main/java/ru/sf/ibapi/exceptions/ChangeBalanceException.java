package ru.sf.ibapi.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import ru.sf.ibapi.apiresponses.responsecodes.ApiResponseCodes;

@Getter
public class ChangeBalanceException extends Exception {
    private final ApiResponseCodes code;
    private final HttpStatus status;
    private final String message;
    public ChangeBalanceException(String message, ApiResponseCodes code, HttpStatus status) {
        super();
        this.code = code;
        this.status = status;
        this.message = message;
    }
}