package com.havutcu.europeana.service;

import com.havutcu.europeana.UpperNumber;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class UpperNumberServiceTest {

    @Test
    void should_update_value() {
        UpperNumberService service = new UpperNumberService();
        //given
        service.updateUpperNumber(90);
        //then
        int value = UpperNumber.getValue();
        assertEquals(90, value);
    }

    @Test
    void should_call_update_method() {
        assertEquals(15, UpperNumber.getValue());

        MockedStatic<UpperNumber> mockStatic = Mockito.mockStatic(UpperNumber.class);
        mockStatic.when(UpperNumber::getValue).thenReturn(11);
        assertThat(UpperNumber.getValue()).isEqualTo(11);

        UpperNumberService service = new UpperNumberService();

        service.updateUpperNumber(-12);

        int value = UpperNumber.getValue();

        assertEquals(11, value);

        mockStatic.close();
    }
}