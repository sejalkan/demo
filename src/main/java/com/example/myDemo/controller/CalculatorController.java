package com.example.myDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.myDemo.exception.CalculationException;
import com.example.myDemo.exception.InvalidOperationException;
import com.example.myDemo.model.Calculation;
import com.example.myDemo.service.CalculatorService;

@RestController
@RequestMapping("/api/v1")
public class CalculatorController {

    @Autowired
    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    // Helper method to save a calculation
    private Calculation saveCalculation(String operation, double result) {
        return calculatorService.saveCalculation(operation, result);
    }

    //Test request to check connection
    @PostMapping("/resource")
    public String handlePostRequest() {
        return "POST request received";
    }

    // POST request to add numbers
    @PostMapping("/add")
    public Calculation addNumbers(@RequestParam double a, @RequestParam double b) {
        try{
        double result = a + b;
        return saveCalculation(a + " + " + b, result);
        }catch (Exception e) {
            throw new CalculationException("Failed to add numbers");
        }
    }

    // POST request to subtract numbers
    @PostMapping(value = "/subtract")
    public Calculation subNumbers(@RequestParam double a, @RequestParam double b){
        try{
        double result = a - b;
        return saveCalculation(a + " - " + b, result);
        }catch (Exception e) {
            throw new CalculationException("Failed to subtract numbers");
        }
    }

    // POST request to multiple numbers
    @PostMapping(value = "/multiply")
    public Calculation mulNumbers(@RequestParam double a, @RequestParam double b){
        try{
        double result = a * b;
        return saveCalculation(a + " * " + b, result);
        }catch (Exception e) {
            throw new CalculationException("Failed to multiply numbers");
        }
    }

    // POST request to divide numbers
    @PostMapping("/divide")
    public Calculation divNumbers(@RequestParam double a, @RequestParam double b) {
        //check for non 0 denominator as division by 0 is not possible
        if (b == 0) {
            throw new InvalidOperationException("Division by zero is not allowed"); 
        }
        try{
        double result = a / b;
        return saveCalculation(a + " / " + b, result);
        }catch (Exception e) {
            throw new CalculationException("Failed to divide numbers");
        }
    }

    // GET request to view previous calculations
    @GetMapping("/history")
    public Iterable<Calculation> getHistory() {
        return calculatorService.getAllCalculations();

    }

}
