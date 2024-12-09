package edu.grinnell.csc207.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class CipherUtilsTest {

    // Caesar Cipher Tests
    @Test
    public void testCaesarEncrypt() {
        assertEquals("ifmmp", CipherUtils.caesarEncrypt("hello", 'b'),
                "Caesar encryption with key 'b' failed.");
    }

    @Test
    public void testCaesarDecrypt() {
        assertEquals("hello", CipherUtils.caesarDecrypt("ifmmp", 'b'),
                "Caesar decryption with key 'b' failed.");
    }

    @Test
    public void testCaesarEncryptWrapAround() {
        assertEquals("b", CipherUtils.caesarEncrypt("z", 'c'),
                "Caesar encryption wrap-around with key 'c' failed.");
    }

    @Test
    public void testCaesarDecryptWrapAround() {
        assertEquals("z", CipherUtils.caesarDecrypt("b", 'c'),
                "Caesar decryption wrap-around with key 'c' failed.");
    }

    // Vigenère Cipher Tests
    @Test
    public void testVigenereEncrypt() {
        assertEquals("jeanolqraf", CipherUtils.vigenereEncrypt("helloworld", "cap"),
                "Vigenère encryption with key 'cap' failed.");
    }

    @Test
    public void testVigenereDecrypt() {
        assertEquals("helloworld", CipherUtils.vigenereDecrypt("jeanolqraf", "cap"),
                "Vigenère decryption with key 'cap' failed.");
    }

    @Test
    public void testVigenereEncryptWrapAround() {
        assertEquals("b", CipherUtils.vigenereEncrypt("z", "c"),
                "Vigenère encryption wrap-around with key 'c' failed.");
    }

    @Test
    public void testVigenereDecryptWrapAround() {
        assertEquals("z", CipherUtils.vigenereDecrypt("b", "c"),
                "Vigenère decryption wrap-around with key 'c' failed.");
    }

    // Validation Tests
    @Test
    public void testInvalidStringInputCaesar() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CipherUtils.caesarEncrypt("Hello123", 'a');
        });
        assertEquals("Input string must be non-empty and contain only lowercase letters.",
                exception.getMessage());
    }

    @Test
    public void testInvalidKeyCaesar() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CipherUtils.caesarEncrypt("hello", '1');
        });
        assertEquals("Letter must be a lowercase character.", exception.getMessage());
    }

    @Test
    public void testInvalidStringInputVigenere() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CipherUtils.vigenereEncrypt("Hello123", "abc");
        });
        assertEquals("Input string must be non-empty and contain only lowercase letters.",
                exception.getMessage());
    }

    @Test
    public void testEmptyKeyVigenere() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CipherUtils.vigenereEncrypt("helloworld", "");
        });
        assertEquals("Key must be non-empty and contain only lowercase letters.",
                exception.getMessage());
    }

    @Test
    public void testInvalidKeyCharactersVigenere() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CipherUtils.vigenereEncrypt("helloworld", "abc123");
        });
        assertEquals("Key must be non-empty and contain only lowercase letters.",
                exception.getMessage());
    }

    // Edge Case Tests
    @Test
    public void testEmptyInputString() {
        assertEquals("", CipherUtils.caesarEncrypt("", 'a'),
                "Encryption of an empty string failed.");
        assertEquals("", CipherUtils.vigenereEncrypt("", "key"),
                "Vigenère encryption of an empty string failed.");
    }

    @Test
    public void testKeySameAsAlphabetSize() {
        assertEquals("gdkkn", CipherUtils.caesarEncrypt("hello", 'z'),
                "Caesar encryption with key 'z' failed.");
        assertEquals("ifmmp", CipherUtils.caesarDecrypt("hello", 'z'),
                "Caesar decryption with key 'z' failed.");
    }
}