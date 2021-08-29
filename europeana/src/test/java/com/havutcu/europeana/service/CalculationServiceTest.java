package com.havutcu.europeana.service;

import com.havutcu.europeana.UpperNumber;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CalculationServiceTest {

    @Test
    void should_find_max_number() {
        CalculationService service = new CalculationService();

        final long smallestNumber = service.findSequentialMaxDivider(6);

        assertEquals(3, smallestNumber);

    }

    @Test
    void should_call_findSmallestNumber() {
        CalculationService service = new CalculationService();

        CalculationService mockedService = Mockito.spy(service);
        when(mockedService.findSequentialMaxDivider(anyLong())).thenReturn(10L);

        String message = mockedService.findSequentialDivider(2);

        assertEquals("2 is the smallest number that can be divided by 1 to 10", message);
        verify(mockedService).findSequentialMaxDivider(anyLong());

    }

    @Test
    void should_find_sequential_divider() {
        CalculationService service = new CalculationService();

        String message = service.findSmallestNumber();

        assertEquals("360360 is the smallest number that can be divided by 1 to 15", message);

    }

    @Test
    void should_find_for_given_number() {
        assertEquals(15, UpperNumber.getValue());

        MockedStatic<UpperNumber> mockStatic = Mockito.mockStatic(UpperNumber.class);
        mockStatic.when(UpperNumber::getValue).thenReturn(10);
        CalculationService service = new CalculationService();

        String message = service.findSmallestNumber();

        assertEquals("2520 is the smallest number that can be divided by 1 to 10", message);
        mockStatic.close();
    }
}