package ru.sf.ibapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link ru.sf.ibapi.entities.Customer} entity
 */
@Data
@NoArgsConstructor
public class CustomerDto implements Serializable {
    private Long id;
    private String lastName;
    private String firstName;
    private Long balanceInRoubles;
}