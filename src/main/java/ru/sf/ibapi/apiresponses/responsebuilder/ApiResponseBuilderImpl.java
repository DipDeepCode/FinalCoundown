package ru.sf.ibapi.apiresponses.responsebuilder;

import ru.sf.ibapi.apiresponses.responsecodes.ApiResponseCodes;
import ru.sf.ibapi.apiresponses.responses.ApiResponse;
import ru.sf.ibapi.apiresponses.responses.ChangeBalanceResponse;
import ru.sf.ibapi.apiresponses.responses.ErrorResponse;
import ru.sf.ibapi.apiresponses.responses.GetBalanceResponse;

public class ApiResponseBuilderImpl implements ApiResponseBuilder {

    @Override
    public ApiResponse buildSuccessfulResponse(ApiResponseCodes code) {
        return new ChangeBalanceResponse(code.getCode());
    }

    @Override
    public ApiResponse buildErrorResponse(ApiResponseCodes code, String message) {
        return new ErrorResponse(code.getCode(), message);
    }

    @Override
    public ApiResponse buildBalanceResponse(long balance) {
        return new GetBalanceResponse(balance);
    }
}
