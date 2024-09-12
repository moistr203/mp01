package edu.grinnell.csc207.util;

/**
 * Utility methods for classical encryption.
 */
public class CipherUtils {

    // Constant for alphabet size
    private static final int ALPHABET_SIZE = 26;

    // Prevent instantiation of this utility class
    private CipherUtils() {
        // Prevent instantiation
    }

    /**
     * Converts a letter to an integer.
     *
     * @param letter the letter to convert
     * @return the corresponding integer value of the letter
     */
    private static int letter2int(final char letter) {
        if (!Character.isLowerCase(letter)) {
            throw new IllegalArgumentException("Letter must be a lowercase character.");
        }
        return letter - 'a';
    }

    /**
     * Converts an integer to a letter.
     *
     * @param i the integer to convert
     * @return the corresponding letter
     */
    private static char int2letter(final int i) {
        return (char) (i + 'a');
    }

    /**
     * Encrypts a string using the Caesar cipher.
     *
     * @param str    the string to encrypt
     * @param letter the key letter for encryption
     * @return the encrypted string
     */
    public static String caesarEncrypt(final String str, final char letter) {
        validateString(str);
        int key = letter2int(letter);
        StringBuilder result = new StringBuilder();
        for (char ch : str.toCharArray()) {
            int newChar = (letter2int(ch) + key) % ALPHABET_SIZE;
            result.append(int2letter(newChar));
        }
        return result.toString();
    }

    /**
     * Decrypts a string using the Caesar cipher.
     *
     * @param str    the string to decrypt
     * @param letter the key letter for decryption
     * @return the decrypted string
     */
    public static String caesarDecrypt(final String str, final char letter) {
        validateString(str);
        int key = letter2int(letter);
        StringBuilder result = new StringBuilder();
        for (char ch : str.toCharArray()) {
            int newChar = (letter2int(ch) - key + ALPHABET_SIZE) % ALPHABET_SIZE;
            result.append(int2letter(newChar));
        }
        return result.toString();
    }

    /**
     * Encrypts a string using the Vigenère cipher.
     *
     * @param str the string to encrypt
     * @param key the key for encryption
     * @return the encrypted string
     */
    public static String vigenereEncrypt(final String str, final String key) {
        validateString(str);
        validateKey(key);
        StringBuilder result = new StringBuilder();
        int keyLength = key.length();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            char k = key.charAt(i % keyLength);
            int newChar = (letter2int(ch) + letter2int(k)) % ALPHABET_SIZE;
            result.append(int2letter(newChar));
        }
        return result.toString();
    }

    /**
     * Decrypts a string using the Vigenère cipher.
     *
     * @param str the string to decrypt
     * @param key the key for decryption
     * @return the decrypted string
     */
    public static String vigenereDecrypt(final String str, final String key) {
        validateString(str);
        validateKey(key);
        StringBuilder result = new StringBuilder();
        int keyLength = key.length();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            char k = key.charAt(i % keyLength);
            int newChar = (letter2int(ch) - letter2int(k) + ALPHABET_SIZE) % ALPHABET_SIZE;
            result.append(int2letter(newChar));
        }
        return result.toString();
    }

    // Validates that a string contains only lowercase letters
    private static void validateString(final String str) {
        if (!str.matches("[a-z]+")) {
            throw new IllegalArgumentException("Input string must contain only lowercase letters.");
        }
    }

    // Validates that the key is a non-empty string of lowercase letters
    private static void validateKey(final String key) {
        if (key.isEmpty()) {
            throw new IllegalArgumentException("Key must not be empty.");
        }
        if (!key.matches("[a-z]+")) {
            throw new IllegalArgumentException("Key must contain only lowercase letters.");
        }
    }
}
