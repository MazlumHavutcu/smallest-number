package com.havutcu.europeana.controller;

import com.havutcu.europeana.service.UpperNumberService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UpperNumberController.class)
class UpperNumberControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UpperNumberService service;

    @Test
    void should_call_service() throws Exception {
        //when
        String uri = "/api/v1/upperNumber/updateUpperNumber/33";
        //given
        mvc.perform(put(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //then
        verify(service).updateUpperNumber(33);
        verify(service, never()).updateUpperNumber(34);
    }

    @Test
    void should_call_service_with_media_type_xml() throws Exception {
        //when
        String uri = "/api/v1/upperNumber/updateUpperNumber/44";
        //given
        mvc.perform(put(uri)
                        .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andReturn();

        //then
        verify(service).updateUpperNumber(44);
    }

    @Test
    void should_throw_exception() {
        String uri = "/api/v1/upperNumber/updateUpperNumber/-23";

        NestedServletException thrown = assertThrows(
                NestedServletException.class,
                () -> mvc.perform(put(uri)
                        .contentType(MediaType.APPLICATION_JSON))
        );

        assertTrue(thrown.getMessage().contains("Number must be greater than or equal to 1"));

    }
}