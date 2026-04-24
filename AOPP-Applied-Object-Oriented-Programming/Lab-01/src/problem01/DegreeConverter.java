package problem01;

import java.util.Objects;
import java.util.Scanner;

public class DegreeConverter {
    static double fahrenheitToCelsius(double f) {
        return 5.0 / 9.0 * (f - 32);
    }

    static double celsiusToFahrenheit(double c) {
        return 9.0 / 5.0 * (c + 32);
    }

    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String response;
        double degreeValue;

        System.out.print("Calculate Celsius -> Fahrenheit? [Y/n]: ");
        response = scanner.hasNext() ? scanner.next() : "Y";
        System.out.println();

        System.out.printf("Enter degrees in %s: ",
                (Objects.equals(response, "n") ? "Fahrenheit" : "Celsius"));

        degreeValue = scanner.nextDouble();

        if (Objects.equals(response, "n")) {
            System.out.printf("%.2fF is equal to %.2fC%n", degreeValue, fahrenheitToCelsius((degreeValue)));
        } else {
            System.out.printf("%.2fC is equal to %.2fF%n", degreeValue, celsiusToFahrenheit((degreeValue)));
        }
    }
}
