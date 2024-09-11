package com.example.myDemo.repository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myDemo.model.Calculation;

@Repository
@ComponentScan(basePackages = {"com.example.myDemo.repository"})
public interface CalculatorRepository extends JpaRepository<Calculation, Long> {
}
