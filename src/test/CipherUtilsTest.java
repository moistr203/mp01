import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CipherUtilsTest {

    @Test
    public void testCaesarEncrypt() {
        assertEquals("ifmmp", CipherUtils.caesarEncrypt("hello", 'b'), "Caesar encryption failed");
    }

    @Test
    public void testCaesarDecrypt() {
        assertEquals("hello", CipherUtils.caesarDecrypt("ifmmp", 'b'), "Caesar decryption failed");
    }

    @Test
    public void testVigenereEncrypt() {
        assertEquals("jeanolqraf", CipherUtils.vigenereEncrypt("helloworld", "cap"), "Vigenere encryption failed");
    }

    @Test
    public void testVigenereDecrypt() {
        assertEquals("helloworld", CipherUtils.vigenereDecrypt("jeanolqraf", "cap"), "Vigenere decryption failed");
    }

    @Test
    public void testInvalidStringInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CipherUtils.caesarEncrypt("Hello123", 'a');
        });
        assertEquals("Input string must contain only lowercase letters.", exception.getMessage());
    }

    @Test
    public void testEmptyKey() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CipherUtils.vigenereEncrypt("helloworld", "");
        });
        assertEquals("Key must not be empty.", exception.getMessage());
    }
}
