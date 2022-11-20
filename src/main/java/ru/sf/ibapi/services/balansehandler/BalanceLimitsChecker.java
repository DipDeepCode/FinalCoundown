package ru.sf.ibapi.services.balansehandler;

public interface BalanceLimitsChecker {

    boolean isLowerLimitExceeded(Long balance);
    boolean isUpperLimitExceeded(Long balance);
}
