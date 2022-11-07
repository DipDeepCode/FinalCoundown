package ru.sf.ibapi.apiresponses.responsebuilder;

import ru.sf.ibapi.apiresponses.responsecodes.ApiResponseCodes;
import ru.sf.ibapi.apiresponses.responses.ApiResponse;

public interface ApiResponseBuilder {
    ApiResponse buildSuccessfulResponse(ApiResponseCodes code);
    ApiResponse buildErrorResponse(ApiResponseCodes code, String message);
    ApiResponse buildBalanceResponse(long balance);
}
