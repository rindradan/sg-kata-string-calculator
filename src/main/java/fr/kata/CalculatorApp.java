package fr.kata;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CalculatorApp {

    private final static String DEFAULT_DELIMITER = "[,\n]";

    public int add(String input) {
        try {
            var delimiter = getDelimiter(input);
            var inputNumbers = getInputNumbers(input);
            var split = inputNumbers.split(delimiter);
            var numbers = formatNumbers(split);
            return numbers.stream().reduce(0, Integer::sum);
        } catch (NumberFormatException ignored) {}
        return 0;
    }

    private String getDelimiter(String input) {
        if (input.startsWith("//")) {
            return input.substring(2, 3);
        }
        return DEFAULT_DELIMITER;
    }

    private String getInputNumbers(String input) {
        if (input.startsWith("//")) {
            return input.substring(4);
        }
        return input;
    }

    private List<Integer> formatNumbers(String[] numbersTab) {
        var numbers = Arrays.stream(numbersTab).map(Integer::parseInt).toList();

        var negatives = numbers.stream().filter(num -> num < 0).map(Object::toString).collect(Collectors.joining(","));
        if (!negatives.isEmpty()) { throw new IllegalArgumentException("negatives not allowed: "+ negatives); }

        return numbers.stream().filter(num -> num <= 1000).collect(Collectors.toList());
    }
}
