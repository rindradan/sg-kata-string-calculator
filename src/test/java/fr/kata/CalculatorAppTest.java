package fr.kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorAppTest {

    @Test
    void should_sum_two_numbers() {
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
    void should_sum_many_numbers() {
        CalculatorApp app = new CalculatorApp();
        int actual = app.add("1,2,3,4,5");
        Assertions.assertEquals(15, actual);
    }

    @Test
    void should_sum_with_break_separator() {
        CalculatorApp app = new CalculatorApp();
        int actual = app.add("1\n2");
        Assertions.assertEquals(3, actual);
    }

    @Test
    void should_sum_with_different_delimiter() {
        CalculatorApp app = new CalculatorApp();
        int actual = app.add("//;\n1;2");
        Assertions.assertEquals(3, actual);
    }

    @Test
    void should_throw_error_on_negative_number() {
        CalculatorApp app = new CalculatorApp();
        Assertions.assertThrows(IllegalArgumentException.class, () -> app.add("3,2,-1"));
    }

    @Test
    void should_sum_numbers_lower_than_1000() {
        CalculatorApp app = new CalculatorApp();
        int actual = app.add("1,2,1000,1001");
        Assertions.assertEquals(1003, actual);
    }

    @Test
    void should_sum_with_long_delimiter() {
        CalculatorApp app = new CalculatorApp();
        int actual = app.add("//[***]\n1***2***3");
        Assertions.assertEquals(6, actual);
    }

    @Test
    void should_sum_with_multiple_delimiters() {
        CalculatorApp app = new CalculatorApp();
        int actual = app.add("//[*][%]\n1*2%3");
        Assertions.assertEquals(6, actual);
    }

    @Test
    void should_sum_with_multiple_long_delimiters() {
        CalculatorApp app = new CalculatorApp();
        int actual = app.add("//[***][%%%%]\n1***2%%%%3");
        Assertions.assertEquals(6, actual);
    }
}
