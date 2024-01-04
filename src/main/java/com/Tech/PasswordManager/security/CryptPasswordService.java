package com.Tech.PasswordManager.security;
import org.springframework.stereotype.Component;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class CryptPasswordService {
    private static final String ALGORITHM = "AES";
    private String secretKey = "3a6eb0790f39ac87c94f3856b2dd2c5d110e6811602261a9a923d3bb23adc8b7";

    public String encode(String password) throws Exception {
        SecretKey key = generateSecretKey(secretKey);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encryptedBytes = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decode(String encryptedPassword) throws Exception {
        SecretKey key = generateSecretKey(secretKey);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedPassword);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    private SecretKey generateSecretKey(String secretKey) throws Exception {
        byte[] keyBytes = new byte[secretKey.length() / 2];
        for (int i = 0; i < keyBytes.length; i++) {
            int index = i * 2;
            int j = Integer.parseInt(secretKey.substring(index, index + 2), 16);
            keyBytes[i] = (byte) j;
        }

        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

}
