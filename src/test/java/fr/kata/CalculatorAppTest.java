package fr.kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorAppTest {

    @Test
    void should_return_sum_of_two_numbers() {
        CalculatorApp app = new CalculatorApp();
        int result = app.add("1,2");
        Assertions.assertEquals(3, result);
    }

    @Test
    void should_return_zero_when_empty() {
        CalculatorApp app = new CalculatorApp();
        int result = app.add("");
        Assertions.assertEquals(0, result);
    }

    @Test
    void should_return_number_when_alone() {
        CalculatorApp app = new CalculatorApp();
        int result = app.add("1");
        Assertions.assertEquals(1, result);
    }
}
