package testsuites;

import common.logging.ReportLogging;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import testcases.searchcompanytest.CompanySearchTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		//#registration
//		RegistrationTest.class,
//		RegistrationAttemptTest.class,
		//#search
		CompanySearchTest.class,
		//#login
//		LoginTest.class,
//		LoginAttemptTest.class

})
public class ExampleTestSuite {

	@BeforeClass
	public static void setUp() {
		ReportLogging.createReport();
	}

	@AfterClass
	public static void tearDown() {
		ReportLogging.closeReport();
	}
}
