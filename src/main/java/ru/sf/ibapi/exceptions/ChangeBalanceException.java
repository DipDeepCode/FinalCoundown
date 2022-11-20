package ru.sf.ibapi.exceptions;

import lombok.Getter;

@Getter
public class ChangeBalanceException extends Exception {
    private final String message;
    public ChangeBalanceException(String message) {
        super();
        this.message = message;
    }
}
