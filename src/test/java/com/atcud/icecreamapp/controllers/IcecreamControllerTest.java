package com.atcud.icecreamapp.controllers;

import com.atcud.icecreamapp.entities.Icecream;
import com.atcud.icecreamapp.services.IcecreamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(IcecreamController.class) // Simulate requests instead of starting server
@AutoConfigureMockMvc(addFilters = false)
@WebAppConfiguration
@ActiveProfiles(value = "test")
public class IcecreamControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IcecreamService icecreamService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetAllIcecreams() throws Exception {

        List<Icecream> icecreams = new ArrayList<>(Arrays.asList(
                new Icecream((long) 1, "Chocolate Icecream", "New Icecream"),
                new Icecream((long) 2, "Vanilla Icecream", "New Icecream")));

        given(icecreamService.getAllIcecreams()).willReturn(icecreams);

        List<Icecream> expectedResult = new ArrayList<>(Arrays.asList(
                new Icecream((long) 1, "Chocolate Icecream", "New Icecream"),
                new Icecream((long) 2, "Vanilla Icecream", "New Icecream")));

        String expectedResponseBody = objectMapper.writeValueAsString(expectedResult);
        MvcResult mvcResult = mvc.perform(get("/icecreams")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(expectedResponseBody).isEqualToIgnoringWhitespace(actualResponseBody);

    }
}
