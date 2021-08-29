package com.havutcu.europeana.controller;

import com.havutcu.europeana.service.UpperNumberService;
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
@RequestMapping(value = "api/v1/upperNumber")
public class UpperNumberController {

    private final UpperNumberService service;

    public UpperNumberController(UpperNumberService service) {
        this.service = service;
    }

    @PutMapping(value = "/updateUpperNumber/{number}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public void updateUpperNumber(@Min(value = 1,message = "Number must be greater than or equal to 1") @PathVariable(value = "number") Integer number) {
            service.updateUpperNumber(number);
    }
}
