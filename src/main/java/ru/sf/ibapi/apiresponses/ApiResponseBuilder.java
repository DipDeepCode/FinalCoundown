package ru.sf.ibapi.apiresponses;

public interface ApiResponseBuilder {
    ApiResponse buildSuccessfulResponse(ApiResponseCodes code);
    ApiResponse buildErrorResponse(ApiResponseCodes code, String message);
    ApiResponse buildBalanceResponse(long balance);
}
