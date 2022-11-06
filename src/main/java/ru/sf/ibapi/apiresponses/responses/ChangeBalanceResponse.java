package ru.sf.ibapi.apiresponses.responses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChangeBalanceResponse extends ApiResponse {
    private final int code;
}
