package ru.sf.ibapi.apiresponses.responsecodes;

import lombok.Getter;

@Getter
public enum ApiResponseCodes {
    CHANGE_BALANCE_SUCCESSFUL (1),
    CHANGE_BALANCE_ERROR (0),
    GENERAL_ERROR (-1);

    private final int code;

    ApiResponseCodes(int code) {
        this.code = code;
    }
}
