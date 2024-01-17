package com.Tech.PasswordManager.security;
import org.springframework.stereotype.Component;

@Component
public class CodeAccess {
    private static final long constant = 987654321L;
    public static long genCode(long number) {
        return number + constant;
    }

    public static long reversCode(long result) {
        return result - constant;
    }
}
