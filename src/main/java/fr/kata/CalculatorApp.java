package fr.kata;

import java.util.Arrays;

public class CalculatorApp {

    private final static String DEFAULT_DELIMITER = "[,\n]";

    public int add(String input) {
        String[] split = split(input);
        try {
            return Arrays.stream(split).map(Integer::parseInt).reduce(0, Integer::sum);
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
