package fr.kata;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CalculatorApp {

    private final static String DEFAULT_DELIMITER = "[,\n]";

    public int add(String input) {
        try {
            var split = split(input);
            var numbers = Arrays.stream(split).map(Integer::parseInt).toList();
            var negatives = numbers.stream().filter(num -> num < 0).map(Object::toString).collect(Collectors.joining(","));
            if (!negatives.isEmpty()) { throw new IllegalArgumentException("negatives not allowed: "+ negatives); }
            return numbers.stream().reduce(0, Integer::sum);
        } catch (NumberFormatException ignored) {}
        return 0;
    }

    private String[] split(String input) {
        String delimiter = DEFAULT_DELIMITER;
        String numbers = input;
        if (input.startsWith("//")) {
            delimiter = input.substring(2, 3);
            numbers = input.replaceFirst("//"+delimiter+"\n", "");
        }
        return numbers.split(delimiter);
    }
}
