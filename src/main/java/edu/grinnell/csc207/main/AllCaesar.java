/**
 * This package contains the main entry point classes for encryption-related tasks.
 *
 * <p>Classes in this package handle user input and output for
 * Caesar and Vigenère cipher operations.</p>
 */
package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.CipherUtils;

/**
 * A class that provides Caesar cipher encoding and decoding for all possible shifts.
 *
 * <p>This program takes two arguments:</p>
 * <ul>
 *   <li>The first argument specifies the action: "encode" or "decode".</li>
 *   <li>The second argument specifies the string to process
 *       (must be lowercase letters only).</li>
 * </ul>
 *
 * <p>The program outputs all possible encodings or decodings
 * using shifts from 'a' to 'z'.</p>
 */
public final class AllCaesar {

    /** Command for encoding. */
    private static final String ENCODE = "encode";

    /** Command for decoding. */
    private static final String DECODE = "decode";

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private AllCaesar() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Main method to handle Caesar cipher operations.
     *
     * @param args Command-line arguments:
     *             <ul>
     *               <li>args[0]: "encode" or "decode"</li>
     *               <li>args[1]: The string to encode or decode</li>
     *             </ul>
     */
    public static void main(final String[] args) {
        if (!validateInput(args)) {
            return;
        }

        String action = args[0];
        String str = args[1];
        processCaesarAction(action, str);
    }

    /**
     * Validates input arguments for correct number and format.
     *
     * @param args The command-line arguments:
     *             <ul>
     *               <li>args[0]: The operation ("encode" or "decode")</li>
     *               <li>args[1]: The string to process (lowercase letters only)</li>
     *             </ul>
     * @return {@code true} if arguments are valid; {@code false} otherwise.
     */
    private static boolean validateInput(final String[] args) {
        // Check the number of parameters
        if (args.length != 2) {
            System.err.println(
                    "Error: Incorrect number of parameters. Expected 2 parameters.");
            return false;
        }

        // Validate the action parameter
        String action = args[0];
        if (!action.equals(ENCODE) && !action.equals(DECODE)) {
            System.err.println(
                    "Error: Invalid option \"" + action + "\". Valid options are \""
                    + ENCODE + "\" or \"" + DECODE + "\".");
            return false;
        }

        // Validate the input string parameter
        String str = args[1];
        if (!str.matches("[a-z]+")) {
            System.err.println(
                    "Error: The input string must be non-empty "
                    + "and contain only lowercase letters.");
            return false;
        }

        return true;
    }

    /**
     * Processes Caesar cipher operations for all possible shifts (n = a to z).
     *
     * @param action Either "encode" or "decode".
     * @param str    The string to encode or decode.
     */
    private static void processCaesarAction(final String action, final String str) {
        for (char shift = 'a'; shift <= 'z'; shift++) {
            String result = ENCODE.equals(action)
                    ? CipherUtils.caesarEncrypt(str, shift)
                    : CipherUtils.caesarDecrypt(str, shift);
            System.out.println("n = " + shift + ": " + result);
        }
    }
}
