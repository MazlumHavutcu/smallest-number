package com.havutcu.europeana.service;

import com.havutcu.europeana.UpperNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * @author Mazlum Havutcu
 * @date August 2021
 */
@Service
@Slf4j
public class CalculationService {

    /**
     * This method finds the sequential dividers for given number and return a message for max value
     *
     * @param {Long} Given number
     * @return {Integer} Sequential divider max value message
     */
    public String findSequentialDivider(long givenNumber) {
        long maxNumber = findSequentialMaxDivider(givenNumber);
        log.info("Requested number for calculation:[{}], Calculated max divider:[{}]", givenNumber, maxNumber);
        return MessageFormat.format("{0} is the smallest number that can be divided by 1 to {1}", String.valueOf(givenNumber), maxNumber);
    }

    /**
     * @param givenNumber
     * @return
     */
    public long findSequentialMaxDivider(long givenNumber) {
        for (long i = 1; i <= givenNumber; i++) {
            if ((givenNumber % i) != 0) {
                return i - 1;
            }
        }
        return givenNumber;
    }

    /**
     * Find the smallest number that can be divided by 1 to given number(upper)
     *
     * @return
     */
    public String findSmallestNumber() {
        long smallestNumber = getMinNumber();
        return MessageFormat.format("{0} is the smallest number that can be divided by 1 to {1}", String.valueOf(smallestNumber), UpperNumber.getValue());

    }

    private long checkMod(long a, long b) {
        if (a % b != 0) {
            return checkMod(b, a % b);
        } else {
            return b;
        }
    }

    private long getMinNumber() {
        long minNumber = 1;
        for (long i = 1; i <= UpperNumber.getValue(); i++) {
            minNumber = (minNumber * i) / (checkMod(minNumber, i));
        }
        return minNumber;
    }
}
