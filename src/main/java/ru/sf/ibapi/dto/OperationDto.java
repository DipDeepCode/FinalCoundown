package ru.sf.ibapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sf.ibapi.operationcodes.OperationCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OperationDto implements Serializable {
    @NotNull
    private OperationCode operationCode;
    @NotNull
    private ZonedDateTime operationTimestamp;
    @NotNull
    private Long amount;
}