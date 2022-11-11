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
    @NotBlank(message = "Поле lastname не может быть пустым")
    private String lastname;
    @NotBlank(message = "Поле firstname не может быть пустым")
    private String firstname;
}