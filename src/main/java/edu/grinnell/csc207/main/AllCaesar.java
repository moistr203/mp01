package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.CipherUtils;

public class AllCaesar {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Error: Incorrect number of parameters.");
            return;
        }

        String action = args[0];
        String str = args[1];

        if (!action.equals("encode") && !action.equals("decode")) {
            System.err.println("Error: Invalid option: \"" + action + "\". Valid options are \"encode\" or \"decode\".");
            return;
        }

        if (!str.matches("[a-z]+")) {
            System.err.println("Error: String contains characters other than lowercase letters.");
            return;
        }

        for (char c = 'a'; c <= 'z'; c++) {
            String result = action.equals("encode") 
                ? CipherUtils.caesarEncrypt(str, c) 
                : CipherUtils.caesarDecrypt(str, c);
            System.out.println("n = " + c + ": " + result);
        }
    }
}
