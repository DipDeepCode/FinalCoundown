package ru.sf.ibapi.apiresponses.responsebuilder;

import ru.sf.ibapi.apiresponses.responses.ApiResponse;
import ru.sf.ibapi.apiresponses.responses.ApiResponseCode;

public interface ApiResponseBuilder {
    ApiResponse buildSuccessfulResponse(ApiResponseCode code);
    ApiResponse buildErrorResponse(ApiResponseCode code, String message);
}
