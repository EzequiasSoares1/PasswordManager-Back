package com.Tech.PasswordManager.integrate;
import com.Tech.PasswordManager.integrate.controller.ControllerSuite;
import com.Tech.PasswordManager.integrate.security.SecuritySuite;
import com.Tech.PasswordManager.integrate.service.ServiceSuite;
import org.junit.platform.commons.annotation.Testable;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@Testable
@SelectClasses({
        ControllerSuite.class,
        SecuritySuite.class,
        ServiceSuite.class
})
public class SuiteIntegrateTest {
}
