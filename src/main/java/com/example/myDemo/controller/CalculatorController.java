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


    /**
     * This method generates a POST API request to add two numbers.
     * @param a value is part of url.
     * @param b value is part of url.
     * @return saves the calculated result in the database.
     * Response is a json output contain ID, operation and results.

     */
    @PostMapping("/add")
    public Calculation addNumbers(@RequestParam double a, @RequestParam double b) {
        try{
        double result = a + b;
        return saveCalculation(a + " + " + b, result);
        }catch (Exception e) {
            throw new CalculationException("Failed to add numbers");
        }
    }

    /**
     * This method generates a POST API request to subtract two numbers.
     * @param a is part of url.
     * @param b is part of url.
     * @return saves the calculated result in the database.
     * Response is a json output contain ID, operation and results.
     */
    @PostMapping(value = "/subtract")
    public Calculation subNumbers(@RequestParam double a, @RequestParam double b){
        try{
        double result = a - b;
        return saveCalculation(a + " - " + b, result);
        }catch (Exception e) {
            throw new CalculationException("Failed to subtract numbers");
        }
    }

    /**
     * This method generates a POST API request to multiply two numbers.
     * @param a is a part of url
     * @param b is a part of url
     * @return saves the calculated result in the database.
     * Response is a json output contain ID, operation and results.
     */
    @PostMapping(value = "/multiply")
    public Calculation mulNumbers(@RequestParam double a, @RequestParam double b){
        try{
        double result = a * b;
        return saveCalculation(a + " * " + b, result);
        }catch (Exception e) {
            throw new CalculationException("Failed to multiply numbers");
        }
    }

    /**
     * This method generates a POST API request to divide two numbers.
     * @param a is a part of url.
     * @param b is part of url.
     * @return saves the calculated results in the database.
     * if b = 0, exception is thrown.
     * Response is a json output contain ID, operation and results.
     */
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

    /**
     * This methods generest GET API request to print all saved calculations 
     * @return prints calculation in a json format.
     */
    @GetMapping("/history")
    public Iterable<Calculation> getHistory() {
        return calculatorService.getAllCalculations();

    }

}
