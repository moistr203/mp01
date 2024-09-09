package edu.grinnell.csc207.util;

public class CipherUtils {
    // Convert letter to integer
    private static int letter2int(char letter) {
        return letter - 'a';
    }

    // Convert integer to letter
    private static char int2letter(int i) {
        return (char) (i + 'a');
    }

    // Caesar Cipher Encryption
    public static String caesarEncrypt(String str, char letter) {
        int key = letter2int(letter);
        StringBuilder result = new StringBuilder();
        for (char ch : str.toCharArray()) {
            int newChar = (letter2int(ch) + key) % 26;
            result.append(int2letter(newChar));
        }
        return result.toString();
    }

    // Caesar Cipher Decryption
    public static String caesarDecrypt(String str, char letter) {
        int key = letter2int(letter);
        StringBuilder result = new StringBuilder();
        for (char ch : str.toCharArray()) {
            int newChar = (letter2int(ch) - key + 26) % 26;
            result.append(int2letter(newChar));
        }
        return result.toString();
    }

    // Vigenère Cipher Encryption
    public static String vigenereEncrypt(String str, String key) {
        StringBuilder result = new StringBuilder();
        int keyLength = key.length();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            char k = key.charAt(i % keyLength);
            int newChar = (letter2int(ch) + letter2int(k)) % 26;
            result.append(int2letter(newChar));
        }
        return result.toString();
    }

    // Vigenère Cipher Decryption
    public static String vigenereDecrypt(String str, String key) {
        StringBuilder result = new StringBuilder();
        int keyLength = key.length();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            char k = key.charAt(i % keyLength);
            int newChar = (letter2int(ch) - letter2int(k) + 26) % 26;
            result.append(int2letter(newChar));
        }
        return result.toString();
    }
}
