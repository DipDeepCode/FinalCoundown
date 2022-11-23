package ru.sf.ibapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sf.ibapi.custommapper.CustomMapper;
import ru.sf.ibapi.dto.OperationDto;
import ru.sf.ibapi.entities.Operation;
import ru.sf.ibapi.services.operation.OperationService;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/operation")
public class OperationController {
    private final OperationService operationService;
    private final CustomMapper customMapper;

    @Autowired
    public OperationController(OperationService operationService,
                               CustomMapper customMapper) {
        this.operationService = operationService;
        this.customMapper = customMapper;
    }

    @GetMapping("/getList")
    @io.swagger.v3.oas.annotations.Operation(summary = "Получение списка операций с балансом за указанный период",
            description = "При передаче только customerId будет возвращен полный список операций с балансом в формате json." +
                    "При передаче кроме id начальной и конечной даты будет возвращен список операций за указанный период." +
                    "В случае возникновения ошибки при парсинге хотя бы одной из дат, будет возвращен полный список операций.")
    public List<OperationDto> getOperationList(@RequestParam Long customerId,
                                               String startDate,
                                               String endDate) {
        try {
            List<Operation> operationList =
                    operationService.getOperationList(customerId, ZonedDateTime.parse(startDate), ZonedDateTime.parse(endDate));
            return customMapper.mapList(operationList, OperationDto.class);
        } catch (DateTimeParseException | NullPointerException ex) {
            List<Operation> operationList =
                    operationService.getOperationList(customerId);
            return customMapper.mapList(operationList, OperationDto.class);
        }
    }
}
