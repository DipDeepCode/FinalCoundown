package ru.sf.ibapi.exceptions;

import lombok.Getter;
import ru.sf.ibapi.apiresponses.responsecodes.ApiResponseCodes;

@Getter
public class ChangeBalanceException extends Exception {
    private final ApiResponseCodes code;
    private final String message;
    public ChangeBalanceException(String message, ApiResponseCodes code) {
        super();
        this.code = code;
        this.message = message;
    }
}
