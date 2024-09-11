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

    //Test request to check connection
    @PostMapping("/resource")
    public String handlePostRequest() {
        return "POST request received";
    }

    // POST request to add numbers
    @PostMapping(value = "/add")
    public Calculation addNumbers(@RequestBody Calculation calculation){
        double result = calculation.getOperand1() + calculation.getOperand2();
        calculation.setResult(result);
        return calculatorService.saveCalculation(calculation);
    }

    // POST request to subtract numbers
    @PostMapping(value = "/subtract")
    public Calculation subNumbers(@RequestBody Calculation calculation){
        double result = calculation.getOperand1() - calculation.getOperand2();
        calculation.setResult(result);
        return calculatorService.saveCalculation(calculation);
    }

    // POST request to multiple numbers
    @PostMapping(value = "/multiply")
    public Calculation mulNumbers(@RequestBody Calculation calculation){
        double result = calculation.getOperand1() * calculation.getOperand2();
        calculation.setResult(result);
        return calculatorService.saveCalculation(calculation);
    }

    // POST request to divide numbers
    @PostMapping("/divide")
    public Calculation divNumbers(@RequestBody Calculation calculation) {
        //check for non 0 denominator as division by 0 is not possible
        if (calculation.getOperand2() == 0) {
            throw new ArithmeticException("Division by zero is not allowed"); 
        }
        double result = calculation.getOperand1() / calculation.getOperand2();
        calculation.setResult(result);
        return calculatorService.saveCalculation(calculation);
    }

    // GET request to view previous calculations
    @GetMapping("/history")
    public Iterable<Calculation> getHistory() {
        return calculatorService.getAllCalculations();

    }

}
