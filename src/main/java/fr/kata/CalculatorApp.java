package fr.kata;

public class CalculatorApp {

    public int add(String numbers) {
        String[] split = numbers.split(",");
        int result = 0;
        try {
            for (String s : split) {
                result += Integer.parseInt(s);
            }
        } catch (Exception ignored) {
        }
        return result;
    }
}
