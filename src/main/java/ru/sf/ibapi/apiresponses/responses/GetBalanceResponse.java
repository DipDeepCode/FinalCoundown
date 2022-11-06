package ru.sf.ibapi.apiresponses.responses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetBalanceResponse extends ApiResponse {
    private final long balance;
}
