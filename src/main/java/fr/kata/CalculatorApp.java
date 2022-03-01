package fr.kata;

public class CalculatorApp {

    public int add(String numbers) {
        String[] split = numbers.split(",");
        int result = 0;
        try {
            result += Integer.parseInt(split[0]);
            result += Integer.parseInt(split[1]);
        } catch (Exception ignored) {
        }
        return result;
    }
}
