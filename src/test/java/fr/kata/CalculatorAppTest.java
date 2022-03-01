package fr.kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorAppTest {

    @Test
    void should_return_sum_of_two_numbers() {
        CalculatorApp app = new CalculatorApp();
        int actual = app.add("1,2");
        Assertions.assertEquals(3, actual);
    }

    @Test
    void should_return_zero_when_empty() {
        CalculatorApp app = new CalculatorApp();
        int actual = app.add("");
        Assertions.assertEquals(0, actual);
    }

    @Test
    void should_return_number_when_alone() {
        CalculatorApp app = new CalculatorApp();
        int actual = app.add("1");
        Assertions.assertEquals(1, actual);
    }

    @Test
    void should_return_sum_of_many_numbers() {
        CalculatorApp app = new CalculatorApp();
        int actual = app.add("1,2,3,4,5");
        Assertions.assertEquals(15, actual);
    }

    @Test
    void should_accept_break_separator() {
        CalculatorApp app = new CalculatorApp();
        int actual = app.add("1\n2");
        Assertions.assertEquals(3, actual);
    }

    @Test
    void should_support_different_delimiter() {
        CalculatorApp app = new CalculatorApp();
        int actual = app.add("//;\n1;2");
        Assertions.assertEquals(3, actual);
    }
}
