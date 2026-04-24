package problem04;

import java.util.Scanner;

public class Code {
    public static final int CODE_LENGTH = 4;
    private final int[] code = new int[CODE_LENGTH];

    public int[] getCode() {
        return code;
    }

    private void initializeCode(int digit1, int digit2, int digit3, int digit4) {
        this.code[0] = digit1;
        this.code[1] = digit2;
        this.code[2] = digit3;
        this.code[3] = digit4;
    }

    public Code() {
    }

    public Code(int digit1, int digit2, int digit3, int digit4) {
        initializeCode(digit1, digit2, digit3, digit4);
    }

    public Code readCode() {
        Scanner scanner = new Scanner(System.in);
        int digit1, digit2, digit3, digit4;

        System.out.print("Enter digit #1: ");
        digit1 = scanner.nextInt();
        System.out.print("Enter digit #2: ");
        digit2 = scanner.nextInt();
        System.out.print("Enter digit #3: ");
        digit3 = scanner.nextInt();
        System.out.print("Enter digit #4: ");
        digit4 = scanner.nextInt();

        // Validate code

        initializeCode(digit1, digit2, digit3, digit4);

        return this;
    }
}
