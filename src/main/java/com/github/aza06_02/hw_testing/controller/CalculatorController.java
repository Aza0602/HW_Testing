package com.github.aza06_02.hw_testing.controller;

import com.github.aza06_02.hw_testing.service.CalculatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping
    public String hello() {
        return "<h1 style=\"text-align: center\">Добро пожаловать в калькулятор!</h1>";
    }

    @GetMapping("/plus")
    public String plus(@RequestParam(value = "num1", required = false) Integer a,
                       @RequestParam(value = "num2", required = false) Integer b) {
        if (Objects.isNull(a) || Objects.isNull(b)) {
            return "Должны передаваться оба параметра!";
        }
        return buildResult(a, b, calculatorService.plus(a, b), " + ");
    }

    @GetMapping("/minus")
    public String minus(@RequestParam(value = "num1", required = false) Integer a,
                        @RequestParam(value = "num2", required = false) Integer b) {
        if (Objects.isNull(a) || Objects.isNull(b)) {
            return "Должны передаваться оба параметра!";
        }
        return buildResult(a, b, calculatorService.minus(a, b), " - ");
    }

    @GetMapping("/multiply")
    public String multiply(@RequestParam(value = "num1", required = false) Integer a,
                           @RequestParam(value = "num2", required = false) Integer b) {
        if (Objects.isNull(a) || Objects.isNull(b)) {
            return "Должны передаваться оба параметра!";
        }
        return buildResult(a, b, calculatorService.multiply(a, b), " * ");
    }

    @GetMapping("/divide")
    public String divide(@RequestParam(value = "num1", required = false) Integer a,
                         @RequestParam(value = "num2", required = false) Integer b) {
        if (Objects.isNull(a) || Objects.isNull(b)) {
            return "Должны передаваться оба параметра!";
        }
        if (b == 0) {
            return "Деление на ноль запрещено!";
        }
        return buildResult(a, b, calculatorService.divide(a, b), " / ");
    }

    private String buildResult(Number a, Number b, Number result, String operation) {
        return String.format("%s %s %s = %s", a, operation, b, result);
    }
}
