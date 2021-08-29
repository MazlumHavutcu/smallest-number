package com.havutcu.europeana;

import javax.validation.constraints.Min;

/**
 * @author Mazlum Havutcu
 * @date August 2021
 */
public class UpperNumber {

    /**
     *
     */
    private static Integer value = 15;

    private static final Object lockObj = new Object();

    private UpperNumber() {}

    /**
     * Update value
     *
     * @param {Integer}
     */
    public static void setValue(@Min(1) int newValue) {
        synchronized (lockObj) {
            value = newValue;
        }
    }

    /**
     * Get control value
     *
     * @return
     */
    public static int getValue() {
        synchronized (lockObj) {
            return value;
        }
    }
}
