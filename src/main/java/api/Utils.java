
package api;

import domain.items.Carport;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Arrays;

public class Utils {
    /**
     * @param src String to be stripped
     * @return String without HTML tags
     */
    public static String removeHtml(String src) {
        /*
         * Tak https://www.tutorialspoint.com/how-to-remove-the-html-tags-from-a-given-string-in-java
         */
        return src.replaceAll("\\<.*?\\>", "");
    }

    public static LocalDate timestampToLocalDate(Timestamp timestamp) {
        if (timestamp != null) {
            return timestamp.toLocalDateTime().toLocalDate();
        }
        return null;
    }

    private static final int PASSWORD_ITERATIONS = 65536;
    private static final int PASSWORD_LENGTH = 256; // 32 bytes
    private static final SecretKeyFactory PASSWORD_FACTORY;

    static {
        SecretKeyFactory factory = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        PASSWORD_FACTORY = factory;
    }

    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static byte[] calculateSecret(byte[] salt, String password) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt,
                PASSWORD_ITERATIONS,
                PASSWORD_LENGTH);
        try {
            return PASSWORD_FACTORY.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkPassword(String password, byte[] secret, byte[] salt) {
        return Arrays.equals(secret, calculateSecret(salt, password));
    }

}
