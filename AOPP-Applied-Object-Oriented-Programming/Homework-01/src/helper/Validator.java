package helper;

public class Validator {
    public static boolean isEmptyString(String input) {
        return input == null || input.isEmpty();
    }

    public static boolean isNegative(double num) {
        return num < 0;
    }

    public static boolean isNegative(int num) {
        return num < 0;
    }

    public static boolean isNonPositive(double num) {
        return num <= 0;
    }

    public static boolean isNonPositive(int num) {
        return num <= 0;
    }
}
