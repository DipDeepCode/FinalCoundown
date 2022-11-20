package ru.sf.ibapi.apiresponses.responses;

import lombok.Getter;

@Getter
public enum ApiResponseCode {
    CHANGE_BALANCE_ERROR (0),
    CHANGE_BALANCE_SUCCESSFUL (1),
    TRANSFER_SUCCESSFUL(2),
    CUSTOMER_NOT_FOUND(3),
    ARGUMENT_NOT_VALID(4),
    GENERAL_ERROR (-1);

    private final int code;

    ApiResponseCode(int code) {
        this.code = code;
    }
}
