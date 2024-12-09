/**
 * Utility classes for the Cipher project.
 *
 * <p>This package provides utility methods for classical encryption using
 * Caesar and Vigenère ciphers. These methods handle encryption and
 * decryption of strings using valid lowercase letters and keys.</p>
 */
package edu.grinnell.csc207.util;

/**
 * Utility methods for classical encryption using Caesar and Vigenère ciphers.
 *
 * <p>This class provides methods to encrypt and decrypt strings with the Caesar
 * cipher (single-character key) and Vigenère cipher (multi-character key).
 * Strings must contain only lowercase letters, and keys must be valid
 * as per the cipher type.</p>
 *
 * <p>This is a utility class and cannot be instantiated.</p>
 */
public final class CipherUtils {

    /** Constant for the number of letters in the alphabet. */
    private static final int ALPHABET_SIZE = 26;

    /** Private constructor to prevent instantiation. */
    private CipherUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Converts a lowercase letter to its corresponding integer value.
     *
     * @param letter The lowercase letter.
     * @return The integer value of the letter (0 for 'a', 25 for 'z').
     * @throws IllegalArgumentException If the letter is not lowercase.
     */
    private static int letter2int(final char letter) {
        if (!Character.isLowerCase(letter)) {
            throw new IllegalArgumentException(
                    "Letter must be a lowercase character.");
        }
        return letter - 'a';
    }

    /**
     * Converts an integer value to its corresponding lowercase letter.
     *
     * @param i The integer value.
     * @return The lowercase letter (0 corresponds to 'a', 25 corresponds to 'z').
     */
    private static char int2letter(final int i) {
        return (char) (i + 'a');
    }

    /**
     * Encrypts a string using the Caesar cipher.
     *
     * @param str    The string to encrypt (lowercase letters only).
     * @param letter The single-letter key.
     * @return The encrypted string.
     * @throws IllegalArgumentException If the input string or key is invalid.
     */
    public static String caesarEncrypt(final String str, final char letter) {
        validateString(str);
        int key = letter2int(letter) % ALPHABET_SIZE;
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
     * @param str    The string to decrypt (lowercase letters only).
     * @param letter The single-letter key.
     * @return The decrypted string.
     * @throws IllegalArgumentException If the input string or key is invalid.
     */
    public static String caesarDecrypt(final String str, final char letter) {
        validateString(str);
        int key = letter2int(letter) % ALPHABET_SIZE;
        StringBuilder result = new StringBuilder();
        for (char ch : str.toCharArray()) {
            int newChar = (letter2int(ch) - key + ALPHABET_SIZE)
                    % ALPHABET_SIZE;
            result.append(int2letter(newChar));
        }
        return result.toString();
    }

    /**
     * Encrypts a string using the Vigenère cipher.
     *
     * @param str The string to encrypt (lowercase letters only).
     * @param key The multi-letter key.
     * @return The encrypted string.
     * @throws IllegalArgumentException If the input string or key is invalid.
     */
    public static String vigenereEncrypt(final String str, final String key) {
        validateString(str);
        validateKey(key);
        return processVigenere(str, key, true);
    }

    /**
     * Decrypts a string using the Vigenère cipher.
     *
     * @param str The string to decrypt (lowercase letters only).
     * @param key The multi-letter key.
     * @return The decrypted string.
     * @throws IllegalArgumentException If the input string or key is invalid.
     */
    public static String vigenereDecrypt(final String str, final String key) {
        validateString(str);
        validateKey(key);
        return processVigenere(str, key, false);
    }

    /**
     * Processes Vigenère cipher operations for encryption or decryption.
     *
     * @param str       The input string (lowercase letters only).
     * @param key       The multi-letter key.
     * @param isEncrypt True for encryption, false for decryption.
     * @return The resulting string after applying the cipher.
     */
    private static String processVigenere(
            final String str, final String key, final boolean isEncrypt) {
        StringBuilder result = new StringBuilder();
        int keyLength = key.length();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            char k = key.charAt(i % keyLength);
            int shift = (isEncrypt ? 1 : -1) * letter2int(k);
            int newChar = (letter2int(ch) + shift + ALPHABET_SIZE)
                    % ALPHABET_SIZE;
            result.append(int2letter(newChar));
        }
        return result.toString();
    }

    /**
     * Validates that the input string is non-null, non-empty, and contains only
     * lowercase letters.
     *
     * @param str The string to validate.
     * @throws IllegalArgumentException If the string is invalid.
     */

private static void validateString(final String str) {
    if (str == null || str.isEmpty() || !str.matches("[a-z]+")) {
        throw new IllegalArgumentException(
                "Input string must be non-empty "
                + "and contain only lowercase letters.");
    }
}

    /**
     * Validates that the input key is non-null, non-empty, and contains only
     * lowercase letters.
     *
     * @param key The key to validate.
     * @throws IllegalArgumentException If the key is invalid.
     */
    private static void validateKey(final String key) {
        if (key == null || key.isEmpty() || !key.matches("[a-z]+")) {
            throw new IllegalArgumentException(
                    "Key must be non-empty"
                     + "and contain only lowercase letters.");
        }
    }
} // End of CipherUtils class

