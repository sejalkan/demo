package com.example.myDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
        double result = a + b;
        return saveCalculation(a + " + " + b, result);
    }

    // POST request to subtract numbers
    @PostMapping(value = "/subtract")
    public Calculation subNumbers(@RequestParam double a, @RequestParam double b){
        double result = a - b;
        return saveCalculation(a + " - " + b, result);
    }

    // POST request to multiple numbers
    @PostMapping(value = "/multiply")
    public Calculation mulNumbers(@RequestParam double a, @RequestParam double b){
        double result = a * b;
        return saveCalculation(a + " * " + b, result);
    }

    // POST request to divide numbers
    @PostMapping("/divide")
    public Calculation divNumbers(@RequestParam double a, @RequestParam double b) {
        //check for non 0 denominator as division by 0 is not possible
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed"); 
        }
        double result = a / b;
        return saveCalculation(a + " / " + b, result);
    }

    // GET request to view previous calculations
    @GetMapping("/history")
    public Iterable<Calculation> getHistory() {
        return calculatorService.getAllCalculations();

    }

}
