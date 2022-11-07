package ru.sf.ibapi.apiresponses.responsebuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sf.ibapi.apiresponses.responsecodes.ApiResponseCodes;
import ru.sf.ibapi.apiresponses.responses.ChangeBalanceResponse;
import ru.sf.ibapi.apiresponses.responses.ErrorResponse;
import ru.sf.ibapi.apiresponses.responses.GetBalanceResponse;

import static org.junit.jupiter.api.Assertions.*;

import static ru.sf.ibapi.apiresponses.responsecodes.ApiResponseCodes.CHANGE_BALANCE_ERROR;
import static ru.sf.ibapi.apiresponses.responsecodes.ApiResponseCodes.CHANGE_BALANCE_SUCCESSFUL;

class ApiResponseBuilderImplTest {

    ApiResponseBuilder builder;

    @BeforeEach
    void setUp() {
        builder = new ApiResponseBuilderImpl();
    }

    @Test
    void buildSuccessfulResponseTest() {
        ApiResponseCodes expectedCode = CHANGE_BALANCE_SUCCESSFUL;
        ChangeBalanceResponse response = (ChangeBalanceResponse) builder.buildSuccessfulResponse(expectedCode);
        assertEquals(expectedCode.getCode(), response.getCode());
    }

    @Test
    void buildErrorResponseTest() {
        ApiResponseCodes expectedCode = CHANGE_BALANCE_ERROR;
        String expectedMessage = "message";
        ErrorResponse response = (ErrorResponse) builder.buildErrorResponse(expectedCode, expectedMessage);
        assertEquals(expectedCode.getCode(), response.getCode());
        assertEquals(expectedMessage, response.getMessage());
    }

    @Test
    void buildBalanceResponseTest() {
        Long expectedBalance = 1000L;
        GetBalanceResponse response = (GetBalanceResponse) builder.buildBalanceResponse(expectedBalance);
        assertEquals(expectedBalance, response.getBalance());
    }
}