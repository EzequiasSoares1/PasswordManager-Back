package com.Tech.PasswordManager.unit.security;
import com.Tech.PasswordManager.ConfigSpringTest;
import com.Tech.PasswordManager.model.entity.User;
import com.Tech.PasswordManager.security.UserTolls;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import static org.junit.jupiter.api.Assertions.*;

public class UserToolsTest extends ConfigSpringTest {
    private UserDetails userDetails;
    private Authentication authentication;

    @BeforeEach
    public void up(){
        userDetails = new User(1,"testUser", "joao", "sdgfdfgdf");
        authentication = new UsernamePasswordAuthenticationToken(userDetails, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void testIsAuthorizeWhenAuthorized() {
        assertDoesNotThrow(() -> UserTolls.isAutorizate(1));
    }

    @Test
    void testGetUserContextId() {
        User user = (User) userDetails;
        long userId = UserTolls.getUserContextId();
        assertEquals(user.getId(), userId);
    }
}