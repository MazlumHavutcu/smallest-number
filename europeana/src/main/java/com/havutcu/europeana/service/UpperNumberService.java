package com.havutcu.europeana.service;

import com.havutcu.europeana.UpperNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Mazlum Havutcu
 * @date August 2021
 */
@Service
@Slf4j
public class UpperNumberService {

    /**
     * Update max control point.
     *
     * @param {Integer}
     */
    public void updateUpperNumber(Integer number) {
        try {
            UpperNumber.setValue(number);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
