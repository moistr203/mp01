package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.CipherUtils;

/**
 * A utility class to perform Caesar and Vigenère cipher encoding and decoding.
 *
 * <p>The program processes four arguments in any order:</p>
 * <ul>
 *   <li>`-encode` or `-decode` specifies the action.</li>
 *   <li>`-caesar` or `-vigenere` specifies the cipher type.</li>
 *   <li>A string to process (must consist only of lowercase letters).</li>
 *   <li>A key (a single lowercase letter for Caesar, a lowercase string for
 *       Vigenère).</li>
 * </ul>
 *
 * <p>Course: CSC-207<br>
 * Author: Moise Milenge<br>
 * Description: Utility methods for classical encryption.</p>
 */
public final class Cipher {

    /** Command for encoding. */
    private static final String ENCODE = "encode";

    /** Command for decoding. */
    private static final String DECODE = "decode";

    /** Identifier for Caesar cipher. */
    private static final String CAESAR = "caesar";

    /** Identifier for Vigenère cipher. */
    private static final String VIGENERE = "vigenere";

    /** Error message for missing parameters. */
    private static final String ERROR_MISSING_PARAMETERS =
            "Error: Missing required parameters.";

    /** Error message for invalid key. */
    private static final String ERROR_INVALID_KEY =
            "Error: Invalid key for the selected cipher.";

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Cipher() {
        throw new UnsupportedOperationException(
                "Utility class cannot be instantiated.");
    }

    /**
     * Main method to handle Caesar and Vigenère cipher operations.
     *
     * @param args Command-line arguments specifying the action, cipher type,
     *             string, and key.
     */
    public static void main(final String[] args) {
        // Validate the number of arguments
        if (args.length != 4) {
            System.err.println(
                    "Error: Incorrect number of parameters. Expected 4 parameters.");
            return;
        }

        // Parse command-line arguments
        String action = null;
        String cipherType = null;
        String str = null;
        String key = null;

        for (String arg : args) {
            if ("-encode".equals(arg) || "-decode".equals(arg)) {
                action = arg.substring(1); // Remove the leading dash
            } else if ("-caesar".equals(arg) || "-vigenere".equals(arg)) {
                cipherType = arg.substring(1); // Remove the leading dash
            } else if (str == null) {
                str = arg; // Assign the first non-flag argument as the string
            } else if (key == null) {
                key = arg; // Assign the second non-flag argument as the key
            }
        }

        // Validate parsed arguments
        if (!validateArguments(action, cipherType, str, key)) {
            return;
        }

        // Process based on the cipher type
        switch (cipherType) {
            case CAESAR:
                processCaesarCipher(action, str, key);
                break;
            case VIGENERE:
                processVigenereCipher(action, str, key);
                break;
            default:
                System.err.println(
                        "Error: Unknown cipher type. Valid options are '-caesar' "
                        + "or '-vigenere'.");
        }
    }

    /**
     * Validates parsed arguments.
     *
     * @param action     The action to perform ("encode" or "decode").
     * @param cipherType The type of cipher ("caesar" or "vigenere").
     * @param str        The string to process.
     * @param key        The key for encryption/decryption.
     * @return {@code true} if all arguments are valid; {@code false} otherwise.
     */
    private static boolean validateArguments(
            final String action, final String cipherType, final String str, final String key) {
        if (action == null || cipherType == null || str == null || key == null) {
            System.err.println(ERROR_MISSING_PARAMETERS);
            return false;
        }

        if (!str.matches("[a-z]+")) {
            System.err.println(
                    "Error: The string to process must be non-empty "
                    + "and contain only lowercase letters.");
            return false;
        }

        return true;
    }

    /**
     * Processes Caesar cipher operations.
     *
     * @param action Either "encode" or "decode".
     * @param str    The string to process.
     * @param key    The key for the Caesar cipher (a single lowercase letter).
     */
    private static void processCaesarCipher(
            final String action, final String str, final String key) {
        if (!isValidKey(key, true)) {
            System.err.println(ERROR_INVALID_KEY);
            return;
        }

        char keyChar = key.charAt(0);
        String result = ENCODE.equals(action)
                ? CipherUtils.caesarEncrypt(str, keyChar)
                : CipherUtils.caesarDecrypt(str, keyChar);
        System.out.println("Result: " + result);
    }

    /**
     * Processes Vigenère cipher operations.
     *
     * @param action Either "encode" or "decode".
     * @param str    The string to process.
     * @param key    The key for the Vigenère cipher (a lowercase string).
     */
    private static void processVigenereCipher(
            final String action, final String str, final String key) {
        if (!isValidKey(key, false)) {
            System.err.println(ERROR_INVALID_KEY);
            return;
        }

        String result = ENCODE.equals(action)
                ? CipherUtils.vigenereEncrypt(str, key)
                : CipherUtils.vigenereDecrypt(str, key);
        System.out.println("Result: " + result);
    }

    /**
     * Validates the key based on the cipher type.
     *
     * @param key      The key to validate.
     * @param isCaesar True if the key is for Caesar cipher; false for Vigenère
     *                 cipher.
     * @return True if the key is valid; false otherwise.
     */
    private static boolean isValidKey(final String key, final boolean isCaesar) {
        return isCaesar
                ? key.length() == 1 && Character.isLowerCase(key.charAt(0))
                : key.matches("[a-z]+");
    }
}
