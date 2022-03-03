package fr.kata;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CalculatorApp {

    private final static String DELIMITER_START = "//";
    private final static String DELIMITER_END = "\n";
    private final static String DELIMITER_DEFAULT = "[,\n]";
    private final static String BRACKET_OPEN = "[";
    private final static String BRACKET_CLOSE = "]";

    private final static String ERROR_MSG_NEGATIVE_NOT_ALLOWED = "negatives not allowed: %s";

    public int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        var delimiter = getDelimiter(input);
        var inputNumbers = getInputNumbers(input);
        var split = inputNumbers.split(delimiter);
        var numbers = formatNumbers(split);
        validateNumbers(numbers);
        return numbers.stream().reduce(0, Integer::sum);
    }

    private String getDelimiter(String input) {
        if (input.startsWith(DELIMITER_START)) {
            var start = input.indexOf(DELIMITER_START) + 2;
            var end = input.indexOf(DELIMITER_END);
            var delimiter = input.substring(start, end);
            if (delimiter.startsWith(BRACKET_OPEN)) {
                if (delimiter.contains(BRACKET_CLOSE + BRACKET_OPEN)) {
                    return delimiter.replaceAll("]\\[", "") + "{1,}";
                }
                return delimiter + "{1,}";
            }
            return delimiter;
        }
        return DELIMITER_DEFAULT;
    }

    private String getInputNumbers(String input) {
        if (input.startsWith(DELIMITER_START)) {
            var start = input.indexOf(DELIMITER_END) + 1;
            return input.substring(start);
        }
        return input;
    }

    private void validateNumbers(List<Integer> numbers) throws IllegalArgumentException {
        var negatives = numbers.stream().filter(num -> num < 0).map(Object::toString).collect(Collectors.joining(","));
        if (!negatives.isEmpty()) { throw new IllegalArgumentException(String.format(ERROR_MSG_NEGATIVE_NOT_ALLOWED, negatives)); }
    }

    private List<Integer> formatNumbers(String[] numbersTab) {
        return Arrays.stream(numbersTab)
            .map(Integer::parseInt)
            .filter(number -> number <= 1000)
            .collect(Collectors.toList());
    }
}
