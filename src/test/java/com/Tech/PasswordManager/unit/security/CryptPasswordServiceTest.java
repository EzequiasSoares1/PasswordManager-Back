package com.Tech.PasswordManager.unit.security;
import com.Tech.PasswordManager.ConfigSpringTest;
import com.Tech.PasswordManager.security.CryptPasswordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CryptPasswordServiceTest extends ConfigSpringTest{

    @Autowired
    private CryptPasswordService cryptPasswordService;

    @Test
    void testEncodeAndDecode() throws Exception {
        String originalPassword = "myPassword";
        String encodedPassword = cryptPasswordService.encode(originalPassword);
        String decodedPassword = cryptPasswordService.decode(encodedPassword);
        assertEquals(originalPassword, decodedPassword);
    }

    @Test
    void testEncodeWithDifferentPasswords() throws Exception {
        String password1 = "password1";
        String password2 = "password2";

        String encodedPassword1 = cryptPasswordService.encode(password1);
        String encodedPassword2 = cryptPasswordService.encode(password2);

        assertNotEquals(encodedPassword1, encodedPassword2);
    }

    @Test
    void testDecodeInvalidPassword() {
        String invalidEncodedPassword = "invalidEncodedPassword";

        try {
            cryptPasswordService.decode(invalidEncodedPassword);
        } catch (Exception e) {
            return;
        }

        assert false : "Deveria ter lançado uma exceção ao decodificar uma senha inválida.";
    }
}
