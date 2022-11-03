package ru.sf.ibapi.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link ru.sf.ibapi.entities.Customer} entity
 */
@Data
public class CustomerDto implements Serializable {
    private final Long id;
    private final String lastName;
    private final String firstName;
}