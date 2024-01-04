package com.Tech.PasswordManager.unit;


import com.Tech.PasswordManager.unit.config.ConfigSuite;
import com.Tech.PasswordManager.unit.security.SecuritySuite;
import com.Tech.PasswordManager.unit.service.ServiceSuite;
import org.junit.platform.commons.annotation.Testable;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@Testable
@SelectClasses({
    ConfigSuite.class,
    SecuritySuite.class,
    ServiceSuite.class
})
public class SuiteUnitTest {
}
