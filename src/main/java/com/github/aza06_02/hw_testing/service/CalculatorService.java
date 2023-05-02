package com.github.aza06_02.hw_testing.service;

import com.github.aza06_02.hw_testing.Exception.ExceptionDivideByZero;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public Integer plus(Integer a, Integer b) {
        return a + b;
    }

    public Integer minus(Integer a, Integer b) {
        return a - b;
    }

    public Integer multiply(Integer a, Integer b) {
        return a * b;
    }

    public Integer divide(Integer a, Integer b) {
        if (b == 0) {
            throw new ExceptionDivideByZero("Деление на ноль запрещено!");
        }
        return (int) (a.doubleValue() / b);
    }
}
