package com.app.util;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * This utility class provides methods for encrypting and decrypting passwords using the AES-GCM encryption algorithm.
 */
public class PasswordUtil {
    private static final int TAG_LENGTH_BIT = 128; // tag length in bits
    private static final int IV_LENGTH_BYTE = 12; // IV length in bytes
    private static final int SALT_LENGTH_BYTE = 16; // salt length in bytes
    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final String KEY = "A1B2C3D4E5F6G7H8"; // 16 character key

    /**
     * Encrypts the given password using the AES-GCM encryption algorithm.
     *
     * @param password the password to encrypt
     * @return the encrypted password as a Base64-encoded string
     * @throws Exception if an error occurs during encryption
     */
    public static String encrypt(String password) throws Exception {
        byte[] salt = new byte[SALT_LENGTH_BYTE];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_LENGTH_BIT, salt);

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        byte[] combinedBytes = new byte[encryptedBytes.length + SALT_LENGTH_BYTE];

        System.arraycopy(salt, 0, combinedBytes, 0, SALT_LENGTH_BYTE);
        System.arraycopy(encryptedBytes, 0, combinedBytes, SALT_LENGTH_BYTE, encryptedBytes.length);

        return Base64.getEncoder().encodeToString(combinedBytes);
    }

    /**
     * Decrypts the given password using the AES-GCM encryption algorithm.
     *
     * @param encryptedPassword the encrypted password as a Base64-encoded string
     * @return the decrypted password
     * @throws Exception if an error occurs during decryption
     */
    public static String decrypt(String encryptedPassword) throws Exception {
        byte[] combinedBytes = Base64.getDecoder().decode(encryptedPassword);
        byte[] salt = new byte[SALT_LENGTH_BYTE];
        byte[] encryptedBytes = new byte[combinedBytes.length - SALT_LENGTH_BYTE];

        System.arraycopy(combinedBytes, 0, salt, 0, SALT_LENGTH_BYTE);
        System.arraycopy(combinedBytes, SALT_LENGTH_BYTE, encryptedBytes, 0, encryptedBytes.length);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_LENGTH_BIT, salt);

        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        return new String(decryptedBytes);
    }

    /**
     * Checks if a password matches an encrypted password.
     *
     * @param password          the plain text password to check
     * @param encryptedPassword the encrypted password to match against
     * @return true if the passwords match, false otherwise
     * @throws Exception if an error occurs during decryption
     */
    public static boolean passwordsMatch(String password, String encryptedPassword) throws Exception {
        String decryptedPassword = decrypt(encryptedPassword);
        return decryptedPassword.equals(password);
    }
}