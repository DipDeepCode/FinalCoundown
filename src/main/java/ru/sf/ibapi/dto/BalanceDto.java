package ru.sf.ibapi.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link ru.sf.ibapi.entities.Balance} entity
 */
@Data
public class BalanceDto implements Serializable {
    private final Long id;
    private final CustomerDto customer;
}