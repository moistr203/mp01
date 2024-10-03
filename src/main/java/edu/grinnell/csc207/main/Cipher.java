package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.CipherUtils;
/*
 * Course: CSC-207
 * Author: Moise Milenge
 * Description: Utility methods for classical encryption.
 */
/**
 * A class to perform Caesar and Vigenère cipher encoding and decoding.
 */
public final class Cipher {

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private Cipher() {
        // Prevent instantiation
    }

    /**
     * Main method to handle Caesar and Vigenère cipher operations.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        if (args.length != 4) {
            System.err.println("Error: Incorrect number of parameters.");
            return;
        }

        String action = null;
        String cipherType = null;
        String str = null;
        String key = null;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-encode") || args[i].equals("-decode")) {
                action = args[i].substring(1);
            } else if (args[i].equals("-caesar") || args[i].equals("-vigenere")) {
                cipherType = args[i].substring(1);
            } else if (str == null) {
                str = args[i];
            } else if (key == null) {
                key = args[i];
            }
        }

        if (action == null || cipherType == null || str == null || key == null) {
            System.err.println("Error: Missing required parameters.");
            return;
        }

        if (cipherType.equals("caesar")) {
            if (key.length() != 1 || !Character.isLowerCase(key.charAt(0))) {
                System.err.println("Error: Invalid key for Caesar cipher.");
                return;
            }
            char keyChar = key.charAt(0);
            String result = action.equals("encode")
                ? CipherUtils.caesarEncrypt(str, keyChar)
                : CipherUtils.caesarDecrypt(str, keyChar);
            System.out.println(result);
        } else if (cipherType.equals("vigenere")) {
            if (!key.matches("[a-z]+")) {
                System.err.println("Error: Invalid key for Vigenère cipher.");
                return;
            }
            String result = action.equals("encode")
                ? CipherUtils.vigenereEncrypt(str, key)
                : CipherUtils.vigenereDecrypt(str, key);
            System.out.println(result);
        } else {
            System.err.println("Error: Unknown cipher type.");
        }
    }
}
