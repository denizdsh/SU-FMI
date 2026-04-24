package problem02;

import java.util.Scanner;

public class Palindrome {
    static boolean isValidFiveDigitNumber(int num) {
        return num < 100_000 && num > 9_999;
    }

    static boolean isPalindrome(int num) {
        int delimeterPower = 0;
        int mirroredNum = 0;
        int current;

        while (true) {
            current = (num % Math.powExact(10, delimeterPower + 1)) / Math.powExact(10, delimeterPower);
            mirroredNum += current;

            if (num / Math.powExact(10, delimeterPower + 1) > 0) {
                ++delimeterPower;
                mirroredNum *= 10;
            } else {
                break;
            }
        }

        return num == mirroredNum;
    }

    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int originalNumber = 0;
        int absoluteNumberValue = originalNumber;

        while (!isValidFiveDigitNumber(absoluteNumberValue)) {
            System.out.print("Enter a 5-digit number: ");

            originalNumber = scanner.nextInt();
            absoluteNumberValue = Math.abs(originalNumber);

            System.out.println();

            if (isValidFiveDigitNumber(absoluteNumberValue)) {
                break;
            } else {
                System.out.printf("%d is not a valid 5-digit number%n", originalNumber);
            }
        }

        String resultString = isPalindrome(absoluteNumberValue) ?
                String.format("%d is a palindrome%n", originalNumber)
                : String.format("%d is NOT a palindrome%n", originalNumber);

        System.out.println(resultString);
    }
}
