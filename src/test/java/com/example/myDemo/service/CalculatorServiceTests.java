package com.example.myDemo.service;

//USing BDD Mockito 
import static org.mockito.BDDMockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.myDemo.model.Calculation;
import com.example.myDemo.repository.CalculatorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

/**
 * This class tests the business logic and functionality of the application by calling CalculationService.java methods.
 */
class CalculatorServiceTests {

    @Mock
  private CalculatorRepository calculatorRepository;

    @InjectMocks
  private CalculatorService calculatorService;
  @Test
    void testSaveCalculation() {
        // create a mock calculation result
        Calculation calculation = new Calculation("5 + 3", 8.0);
        
        // mock repository's save behavior
        when(calculatorRepository.save(any(Calculation.class))).thenReturn(calculation);

        // call service method to save the calculation
        Calculation savedCalculation = calculatorService.saveCalculation("5 + 3", 8.0);

        // check that the saved calculation has the expected result
        assertEquals(8.0, savedCalculation.getResult());
        assertEquals("5 + 3", savedCalculation.getOperation());

        // verify that the repository's save method was called exactly once
        verify(calculatorRepository, times(1)).save(any(Calculation.class));
    }

  @Test
  void testGetAllCalculations() {
      List<Calculation> calculations = Arrays.asList(
          new Calculation("5 + 3", 8.0),
          new Calculation("6 * 7", 42.0)
      );
      when(calculatorRepository.findAll()).thenReturn(calculations);

      List<Calculation> result = (List<Calculation>) calculatorService.getAllCalculations();
      assertEquals(2, result.size());
      assertEquals("5 + 3", result.get(0).getOperation());
      assertEquals("6 * 7", result.get(1).getOperation());

      verify(calculatorRepository, times(1)).findAll();
  }
}
