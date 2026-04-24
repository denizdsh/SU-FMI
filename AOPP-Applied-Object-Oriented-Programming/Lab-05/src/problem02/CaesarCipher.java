package problem02;

public class CaesarCipher {
    public static String encrypt(int key, String plainText) {
        char[] plainArr = plainText.toCharArray();
        char[] cipherArr = new char[plainArr.length];

        for (int i = 0; i < plainArr.length; i++) {
            cipherArr[i] = (char) ('A' + (plainArr[i] - 'A' + key + 26) % 26);
        }

        return new String(cipherArr);
    }

    public static String decrypt(int key, String cipherText) {
        char[] cipherArr = cipherText.toCharArray();
        char[] plainArr = new char[cipherArr.length];

        for (int i = 0; i < cipherArr.length; i++) {
            plainArr[i] = (char) ((cipherArr[i] - 'A' - key + 26) % 26 + 'A');
        }

        return new String(plainArr);
    }

    static void main(String[] args) {
        int key = 3;
        String result = CaesarCipher.encrypt(key, "TOY");

        System.out.println(result);
        System.out.println(CaesarCipher.decrypt(key, result));
    }
}
