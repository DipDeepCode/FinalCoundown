package ru.sf.ibapi.apiresponses.responses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponse extends ApiResponse {
    private final int code;
    private final String message;
}
