package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.CipherUtils;

/**
 * A class that provides Caesar cipher encoding and decoding for all possible shifts.
 */
public class AllCaesar {

    /**
     * Main method to handle Caesar cipher operations.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        // Validate input parameters
        if (!validateInput(args)) {
            return;
        }

        String action = args[0];
        String str = args[1];

        // Process the action (encode or decode)
        processCaesarAction(action, str);
    }

    /**
     * Validates input arguments for correct number and format.
     *
     * @param args the command line arguments
     * @return true if valid, false otherwise
     */
    private static boolean validateInput(final String[] args) {
        if (args.length != 2) {
            System.err.println("Error: Incorrect number of parameters.");
            return false;
        }

        String action = args[0];
        if (!action.equals("encode") && !action.equals("decode")) {
            System.err.println("Error: Invalid option: \"" + action + "\". Valid options are \"encode\" or \"decode\".");
            return false;
        }

        String str = args[1];
        if (!str.matches("[a-z]+")) {
            System.err.println("Error: String contains characters other than lowercase letters.");
            return false;
        }

        return true;
    }

    /**
     * Processes Caesar cipher operations for all possible shifts (n = a to z).
     *
     * @param action either "encode" or "decode"
     * @param str    the string to encode or decode
     */
    private static void processCaesarAction(final String action, final String str) {
        for (char shift = 'a'; shift <= 'z'; shift++) {
            String result = action.equals("encode")
                    ? CipherUtils.caesarEncrypt(str, shift)
                    : CipherUtils.caesarDecrypt(str, shift);
            System.out.println("n = " + shift + ": " + result);
        }
    }
}
