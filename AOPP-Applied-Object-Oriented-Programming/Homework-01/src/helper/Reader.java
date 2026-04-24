package helper;

import java.util.Scanner;
import java.util.function.Function;

public class Reader {
    private static <T> T readNext(Class<T> type, Scanner scanner) {
        if (type == Integer.class || type == int.class) {
            return (T) (Integer) scanner.nextInt();
        } else if (type == Double.class || type == double.class) {
            return (T) (Double) scanner.nextDouble();
        } else if (type == Boolean.class || type == boolean.class) {
            return (T) (Boolean) scanner.nextBoolean();
        } else if (type == String.class) {
            return (T) scanner.nextLine();
        } else {
            throw new IllegalArgumentException("Scanner next method does not yet support %s type".formatted(type.getName()));
        }
    }

    private static <T> T readUntilNot(Class<T> type, Function<T, Boolean> validator, String message) {
        Scanner scanner = new Scanner(System.in);
        T value;

        do {
            System.out.println(message);
            value = readNext(type, scanner);
        }
        while (validator.apply(value));

        return value;
    }

    public static String readStringUntilNot(Function<String, Boolean> validator, String message) {
        return readUntilNot(String.class, validator, message);
    }

    public static Integer readIntUntilNot(Function<Integer, Boolean> validator, String message) {
        return readUntilNot(Integer.class, validator, message);
    }

    public static Double readDoubleUntilNot(Function<Double, Boolean> validator, String message) {
        return readUntilNot(Double.class, validator, message);
    }
}
