package ru.sf.ibapi.operationcodes;

import lombok.Getter;

@Getter
public enum OperationCode {
    PUT_MONEY(1),
    TAKE_MONEY(2),
    TRANSFER_MONEY_SEND(3),
    TRANSFER_MONEY_RECEIVE(4);

    private final int operationCode;
    OperationCode(int operationCode) {
        this.operationCode = operationCode;
    }
}
