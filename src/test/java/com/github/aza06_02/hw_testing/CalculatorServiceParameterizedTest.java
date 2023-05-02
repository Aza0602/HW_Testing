package com.github.aza06_02.hw_testing;

import com.github.aza06_02.hw_testing.Exception.ExceptionDivideByZero;
import com.github.aza06_02.hw_testing.service.CalculatorService;
import org.assertj.core.data.Offset;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.lang.Nullable;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CalculatorServiceParameterizedTest {

    private final CalculatorService calculatorService = new CalculatorService();

    public static Stream<Arguments> plusTestParams() {
        return Stream.of(
                Arguments.of(4, 5, 9),
                Arguments.of(0, 0, 0),
                Arguments.of(1, 2, 3)
        );
    }

    public static Stream<Arguments> minusTestParams() {
        return Stream.of(
                Arguments.of(4, 5, -1),
                Arguments.of(0, 0, 0),
                Arguments.of(1, 2, -1),
                Arguments.of(3, 2, 1)
        );
    }

    public static Stream<Arguments> multiplyTestParams() {
        return Stream.of(
                Arguments.of(4, 5, 20),
                Arguments.of(0, 0, 0),
                Arguments.of(1, 2, 2)
        );
    }

    public static Stream<Arguments> divideTestParams() {
        return Stream.of(
                Arguments.of(4, 3, 1.0, 0.00001),
                Arguments.of(3, 2, 1.0, null),
                Arguments.of(5, 5, 1, null)
        );
    }

    public static Stream<Arguments> divideNegativeTestParams() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(5, 0),
                Arguments.of(-5, 0)
        );
    }


    @ParameterizedTest
    @MethodSource("plusTestParams")
    public void plusTest(Integer a, Integer b, Number expected) {
        assertThat(calculatorService.plus(a, b)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("minusTestParams")
    public void minusTest(Integer a, Integer b, Number expected) {
        assertThat(calculatorService.minus(a, b)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("multiplyTestParams")
    public void multiplyTest(Integer a, Integer b, Number expected) {
        assertThat(calculatorService.multiply(a, b)).isEqualTo(expected);
    }



    @ParameterizedTest
    @MethodSource("divideTestParams")
    public void divideTest(Integer a, Integer b, Number expected, @Nullable Double delta) {
        if (delta == null) {
            assertThat(calculatorService.divide(a, b).doubleValue()).isEqualTo(expected.doubleValue());
        } else {
            assertThat(calculatorService.divide(a, b).doubleValue())
                    .isEqualTo(expected.doubleValue(), Offset.offset(delta));
        }
    }

    @ParameterizedTest
    @MethodSource("divideNegativeTestParams")
    public void divideNegativeTest(Integer a, Integer b) {
        assertThatThrownBy(() -> calculatorService.divide(a, b))
                .isExactlyInstanceOf(ExceptionDivideByZero.class)
                .hasMessage("Деление на ноль запрещено!");
    }
}
