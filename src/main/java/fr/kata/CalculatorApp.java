package fr.kata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CalculatorApp {

    private final static String DEFAULT_DELIMITER = "[,\n]";
    private final List<Function<List<Integer>, List<Integer>>> filters = new ArrayList<>();

    public CalculatorApp() {
        filters.add(filterNegatives());
        filters.add(filterThousands());
    }

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

    private Function<List<Integer>, List<Integer>> filterNegatives() {
        return numbers -> {
            String negatives = numbers.stream().filter(num -> num < 0).map(Object::toString).collect(Collectors.joining(","));
            if (!negatives.isEmpty()) { throw new IllegalArgumentException("negatives not allowed: " + negatives); }
            return numbers;
        };
    }

    private Function<List<Integer>, List<Integer>> filterThousands() {
        return numbers -> numbers.stream().filter(num -> num <= 1000).collect(Collectors.toList());
    }

    private List<Integer> formatNumbers(String[] numbersTab) {
        var numbers = Arrays.stream(numbersTab).map(Integer::parseInt).collect(Collectors.toList());
        for (Function<List<Integer>, List<Integer>> filter : filters) {
            numbers = filter.apply(numbers);
        }
        return numbers;
    }
}
