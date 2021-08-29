package com.havutcu.europeana.controller;

import com.havutcu.europeana.service.CalculationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.NestedServletException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CalculationController.class)
class CalculationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CalculationService service;

    @BeforeEach
    void setUp() {
        Mockito.when(service.findSequentialDivider(anyLong())).thenReturn("Hello Europeana Team");
        Mockito.when(service.findSmallestNumber()).thenReturn("MAX value");
    }

    @Test
    void should_call_service() throws Exception {
        //when
        String uri = "/api/v1/calculation/findSequentialDivider/10";
        //given
        MvcResult mvcResult = mvc.perform(get(uri)
                        .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isOk()).andReturn();

        //then
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Hello Europeana Team"));
    }

    @Test
    void should_call_json_format() throws Exception {
        //when
        String uri = "/api/v1/calculation/findSequentialDivider/10";
        //given
        MvcResult mvcResult = mvc.perform(get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        //then
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Hello Europeana Team"));
    }

    @Test
    void should_throw_exception() {
        String uri = "/api/v1/calculation/findSequentialDivider/-10";

        NestedServletException thrown = assertThrows(
                NestedServletException.class,
                () -> mvc.perform(get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
        );

        assertTrue(thrown.getMessage().contains("Number must be greater than or equal to 1"));
    }

    @Test
    void should_work_with_xml_media() {
        String uri = "/api/v1/calculation/findSequentialDivider/-10";

        NestedServletException thrown = assertThrows(
                NestedServletException.class,
                () -> mvc.perform(get(uri)
                        .contentType(MediaType.APPLICATION_XML))
        );

        assertTrue(thrown.getMessage().contains("Number must be greater than or equal to 1"));
    }

    @Test
    void should_work_with_xml_media_findSmallestNumber() throws Exception {
        String uri = "/api/v1/calculation/findSmallestNumber";

        MvcResult mvcResult = mvc.perform(get(uri)
                        .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("MAX value"));

    }

    @Test
    void should_work_with_json_media_findSmallestNumber() throws Exception {
        String uri = "/api/v1/calculation/findSmallestNumber";

        MvcResult mvcResult = mvc.perform(get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("MAX value"));

    }
}