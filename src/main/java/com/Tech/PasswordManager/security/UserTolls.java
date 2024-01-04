package com.Tech.PasswordManager.security;

import com.Tech.PasswordManager.model.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserTolls {
    public static void isAutorizate(long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            long userId = user.getId();

            if(id != userId) {throw new RuntimeException("Unauthorized user");}
        }
    }

    public static long getUserContextId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getId();
    }
}
