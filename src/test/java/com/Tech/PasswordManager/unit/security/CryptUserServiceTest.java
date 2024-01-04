package com.Tech.PasswordManager.unit.security;
import com.Tech.PasswordManager.ConfigSpringTest;
import com.Tech.PasswordManager.security.CryptUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class CryptUserServiceTest extends ConfigSpringTest {

    @Autowired
    private CryptUserService cryptUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testEncodePassword() {
        String password = "myPassword";
        String encodedPassword = cryptUserService.encodePassword(password);

        assertTrue(passwordEncoder.matches(password, encodedPassword));
    }

    @Test
    void testMatchesPassword() {
        String password = "myPassword";
        String encodedPassword = passwordEncoder.encode(password);

        assertTrue(cryptUserService.matchesPassword(password, encodedPassword));
    }
}