package ru.sf.ibapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto implements Serializable {
    private Long id;
    @NotBlank
    private String lastname;
    @NotBlank
    private String firstname;
}