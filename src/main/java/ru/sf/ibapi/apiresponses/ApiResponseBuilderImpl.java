package ru.sf.ibapi.apiresponses;

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
