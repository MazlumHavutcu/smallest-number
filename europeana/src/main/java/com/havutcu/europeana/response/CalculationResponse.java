package com.havutcu.europeana.response;

import lombok.Builder;
import lombok.Data;

/**
 * @author Mazlum Havutcu
 * @date August 2021
 */
@Data
@Builder
public class CalculationResponse {

    private String message;

    private Long elapsedTime;
}
