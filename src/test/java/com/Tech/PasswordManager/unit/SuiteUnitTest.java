package com.Tech.PasswordManager.unit;


import com.Tech.PasswordManager.unit.config.GlobalExceptionHandlerTest;
import com.Tech.PasswordManager.unit.security.CryptPasswordServiceTest;
import com.Tech.PasswordManager.unit.security.CryptUserServiceTest;
import com.Tech.PasswordManager.unit.security.UserToolsTest;
import com.Tech.PasswordManager.unit.service.PasswordConverterTest;

import com.Tech.PasswordManager.unit.service.UserConverterTest;
import org.junit.platform.commons.annotation.Testable;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@Testable
@SelectClasses({
        GlobalExceptionHandlerTest.class,
        CryptPasswordServiceTest.class,
        CryptUserServiceTest.class,
        UserToolsTest.class,
        PasswordConverterTest.class,
        UserConverterTest.class
})
public class SuiteUnitTest {
}
