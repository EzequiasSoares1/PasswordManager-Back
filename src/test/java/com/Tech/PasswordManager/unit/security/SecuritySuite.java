package com.Tech.PasswordManager.unit.security;
import org.junit.platform.commons.annotation.Testable;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@Testable
@SelectClasses({
        CryptUserServiceTest.class,
        CryptPasswordServiceTest.class,
        UserToolsTest.class
})
public class SecuritySuite {
}
