package ru.sf.ibapi.services;

import lombok.Getter;

@Getter
public enum OperationCodes {
    PUT_MONEY (1),
    GET_MONEY (2);

    private final int code;

    OperationCodes(int code) {
        this.code = code;
    }
}
