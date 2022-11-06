package ru.sf.ibapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class CustomerDto implements Serializable {
    private Long id;
    @NotBlank(message = "Обязательное поле")
    private String lastName;
    @NotBlank(message = "Обязательное поле")
    private String firstName;
}