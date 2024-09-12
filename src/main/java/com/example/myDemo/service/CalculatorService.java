package com.example.myDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.example.myDemo.model.Calculation;
import com.example.myDemo.repository.CalculatorRepository;

/**
 * The service class contain business logic and application functionality.
 */
@Service
@ComponentScan(basePackages = {"com.example.myDemo.service"})
public class CalculatorService {

    @Autowired
    private CalculatorRepository calculatorRepository;

    @Autowired
    public CalculatorService(CalculatorRepository calculatorRepository) {
        this.calculatorRepository = calculatorRepository;
    }

    //function to get all calculation from the H2 database
    public Iterable<Calculation> getAllCalculations() {
        return calculatorRepository.findAll();
    }

    //save function to save caculation in H2 database
    public Calculation saveCalculation(String operation, double result) {
        Calculation calculation = new Calculation(operation, result);
        return calculatorRepository.save(calculation);
    }
}
