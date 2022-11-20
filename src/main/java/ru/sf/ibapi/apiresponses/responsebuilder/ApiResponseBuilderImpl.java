package ru.sf.ibapi.apiresponses.responsebuilder;

import ru.sf.ibapi.apiresponses.responses.*;

public class ApiResponseBuilderImpl implements ApiResponseBuilder {

    @Override
    public ApiResponse buildSuccessfulResponse(ApiResponseCode code) {
        return new ChangeBalanceResponse(code.getCode());
    }

    @Override
    public ApiResponse buildErrorResponse(ApiResponseCode code, String message) {
        return new ErrorResponse(code.getCode(), message);
    }
}
