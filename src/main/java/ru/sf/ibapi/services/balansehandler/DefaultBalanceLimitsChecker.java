package ru.sf.ibapi.services.balansehandler;

public class DefaultBalanceLimitsChecker implements BalanceLimitsChecker {
    private final Long LOWER_LIMIT;
    private final Long UPPER_LIMIT;

    public DefaultBalanceLimitsChecker(Long lower_limit, Long upper_limit) {
        LOWER_LIMIT = lower_limit;
        UPPER_LIMIT = upper_limit;
    }

    @Override
    public boolean isLowerLimitExceeded(Long balance) {
        return balance < LOWER_LIMIT;
    }

    @Override
    public boolean isUpperLimitExceeded(Long balance) {
        return balance > UPPER_LIMIT;
    }
}
