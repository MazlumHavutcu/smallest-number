package com.havutcu.europeana.controller;

import com.havutcu.europeana.response.CalculationResponse;
import com.havutcu.europeana.service.CalculationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

/**
 * @author Mazlum Havutcu
 * @date August 2021
 */
@Validated
@RestController
@Slf4j
@RequestMapping(value = "api/v1/calculation")
public class CalculationController {

    private final CalculationService service;

    public CalculationController(CalculationService service) {
        this.service = service;
    }

    @GetMapping(value = "/findSequentialDivider/{number}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public CalculationResponse findSequentialDivider(@PathVariable @Min(value = 1, message = "Number must be greater than or equal to 1") long number) {
        long startTime = System.currentTimeMillis();  // TODO: AOP for log and elapsed time
        String message = service.findSequentialDivider(number);
        long endTime = System.currentTimeMillis();
        log.info("findSequentialDivider: Elapsed Time: {}", (endTime - startTime));
        return CalculationResponse.builder()
                .elapsedTime((endTime - startTime))
                .message(message).build();
    }

    @GetMapping(value = "/findSmallestNumber", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public CalculationResponse findSmallestNumber() {
        long startTime = System.currentTimeMillis();
        String message = service.findSmallestNumber();
        long endTime = System.currentTimeMillis();
        log.info("findSmallestNumber: Elapsed Time: {}", (endTime - startTime));
        return CalculationResponse.builder()
                .elapsedTime((endTime - startTime))
                .message(message).build();
    }
}
