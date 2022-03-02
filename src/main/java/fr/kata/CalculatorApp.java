package fr.kata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CalculatorApp {

    private final static String DELIMITER_DEFAULT = "[,\n]";
    private final List<Function<List<Integer>, List<Integer>>> filters = new ArrayList<>();

    public CalculatorApp() {
        filters.add(filterNegatives());
        filters.add(filterThousands());
    }

    public int add(String input) {
        try {
            Matcher matcher = Pattern.compile("(//(\\[.*]|.*)\n)?([\\d\\W\n]*)").matcher(input);
            if (matcher.matches()) {
                var delimiter = getDelimiter(matcher.group(2));
                var inputNumbers = matcher.group(3);
                var split = inputNumbers.split(delimiter);
                var numbers = formatNumbers(split);
                return numbers.stream().reduce(0, Integer::sum);
            }
        } catch (NumberFormatException ignored) {}
        return 0;
    }

    private String getDelimiter(String delimiter) {
        if (delimiter == null || delimiter.isEmpty()) {
            return DELIMITER_DEFAULT;
        }
        if (Pattern.matches("\\[.*]", delimiter)) {
            return delimiter + "{1,}";
        }
        return delimiter;
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
