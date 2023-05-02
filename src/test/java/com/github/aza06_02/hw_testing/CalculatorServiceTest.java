package com.github.aza06_02.hw_testing;

import com.github.aza06_02.hw_testing.Exception.ExceptionDivideByZero;
import com.github.aza06_02.hw_testing.service.CalculatorService;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    public void plusTest() {
        assertThat(calculatorService.plus(3, 5)).isEqualTo(8);
        assertThat(calculatorService.plus(0, 0)).isEqualTo(0);
    }

    @Test
    public void minusTest() {
        assertThat(calculatorService.minus(4, 3)).isEqualTo(1);
        assertThat(calculatorService.minus(3, 4)).isEqualTo(-1);
    }

    @Test
    public void multiplyTest() {
        assertThat(calculatorService.multiply(4, 3)).isEqualTo(12);
        assertThat(calculatorService.multiply(3, -4)).isEqualTo(-12);
    }

    @Test
    public void divideTest() {
        assertThat(calculatorService.divide(4, 3).doubleValue()).isEqualTo(1.0, Offset.offset(0.00001));
        assertThat(calculatorService.divide(3, 4)).isEqualTo(0);
    }

    @Test
    public void divideNegativeTest() {
        assertThatExceptionOfType(ExceptionDivideByZero.class)
                .isThrownBy(() -> calculatorService.divide(4, 0))
                .withMessage("Деление на ноль запрещено!");
        assertThatExceptionOfType(ExceptionDivideByZero.class)
                .isThrownBy(() -> calculatorService.divide(0,0))
                .withMessage("Деление на ноль запрещено!");
    }
}
