package com.Tech.PasswordManager;
import com.Tech.PasswordManager.unit.SuiteUnitTest;
import org.junit.platform.commons.annotation.Testable;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;


@Suite
@Testable
@SelectClasses({
		SuiteUnitTest.class
})
public class AllSuitesTest {
}