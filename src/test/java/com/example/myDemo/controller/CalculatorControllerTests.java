package com.example.myDemo.controller;

import com.example.myDemo.model.Calculation;
import com.example.myDemo.service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This class tests all API calls (requests and responses) by testing all methods in CalculatorController.java.
 */
class CalculatorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CalculatorService calculatorService;

    @InjectMocks
    private CalculatorController calculatorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(calculatorController).build();
    }

    @Test
    void testAddNumbers() throws Exception {
    try{
        Calculation calculation = new Calculation("5 + 3", 8.0);
        when(calculatorService.saveCalculation("5 + 3", 8.0)).thenReturn(calculation);

        mockMvc.perform(post("/api/v1/add")
                .param("a", "5")
                .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'operation':'5 + 3','result':8.0}"));

        verify(calculatorService, times(1)).saveCalculation("5 + 3", 8.0);
    }catch (Exception e) {
        e.printStackTrace();
    }
    }
    @Test
    void testDivNumbers() throws Exception {
    try{
        Calculation calculation = new Calculation("6 / 3", 2.0);
        when(calculatorService.saveCalculation("6 / 3", 2.0)).thenReturn(calculation);

        mockMvc.perform(post("/api/v1/divide")
                .param("a", "6")
                .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'operation':'6 / 3','result':2.0}"));

        verify(calculatorService, times(1)).saveCalculation("6 / 3", 2.0);
    }catch (Exception e) {
        e.printStackTrace();
    }
    }


    @Test
    void testSubtractNumbers() throws Exception {
    try{
        Calculation calculation = new Calculation("8 - 3", 5.0);
        when(calculatorService.saveCalculation("8 - 3", 5.0)).thenReturn(calculation);

        mockMvc.perform(post("/api/v1/subtract")
                .param("a", "8")
                .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'operation':'8 - 3','result':5.0}"));

        verify(calculatorService, times(1)).saveCalculation("8 - 3", 5.0);
    }catch (Exception e) {
        e.printStackTrace();
    }
    }


    @Test
    void testMulNumbers() throws Exception {
    try{
        Calculation calculation = new Calculation("5 * 3", 15.0);
        when(calculatorService.saveCalculation("5 * 3", 15.0)).thenReturn(calculation);

        mockMvc.perform(post("/api/v1/multiply")
                .param("a", "5")
                .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'operation':'5 * 3','result':15.0}"));

        verify(calculatorService, times(1)).saveCalculation("5 * 3", 8.0);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetHistory() throws Exception {
        try{
        List<Calculation> calculations = Arrays.asList(
            new Calculation("5 + 3", 8.0),
            new Calculation("6 * 7", 42.0)
        );
        when(calculatorService.getAllCalculations()).thenReturn(calculations);

        mockMvc.perform(get("/api/v1/history")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'operation':'5 + 3','result':8.0},{'operation':'6 * 7','result':42.0}]"));

        verify(calculatorService, times(1)).getAllCalculations();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
