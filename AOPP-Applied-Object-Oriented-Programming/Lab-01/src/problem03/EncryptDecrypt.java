package problem03;

import problem04.Code;

import java.util.Scanner;

public class EncryptDecrypt {
    static int encryptDigit(int digit) {
        return (digit + 7) % 10;
    }

    static Code encryptCode(Code code) {
        System.out.println(code.getCode()[2]);

        return code;
    }

    static Code decryptCode(Code code) {
        System.out.println(code.getCode()[2]);

        return code;
    }

    static int decryptDigit(int digit) {
        return (digit + 3) % 10;
    }

    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Code code = new Code();
        int serviceNo;

        System.out.println("""
                Encryption Services (Choose one):
                    - Encrypt code [0]
                    - Decrypt code [1]%n
                """);

        serviceNo = scanner.nextInt();

        code.readCode();

        switch (serviceNo) {
            case 0:
                encryptCode(code).print();
                break;
            case 1:
                decryptCode(code).print();
                break;
            default:
                System.out.println("Invalid service request");
                break;
        }
    }
}
