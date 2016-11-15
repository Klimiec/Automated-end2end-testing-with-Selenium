package framework.sharedkernel.rules;


import common.logging.ReportLogging;
import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;

public class TestExecutionVerification extends TestWatcher {


    private String[] testCategories;
    private WebDriver driver;

    @Override

    protected void succeeded(Description description) {
        ReportLogging.succeeded(testCategories, description);
        closeWebDriver();
    }

    @Override
    protected void failed(Throwable e, Description description) {
        ReportLogging.failed(testCategories, description, e.toString(), driver);
        closeWebDriver();
    }

    @Override
    protected void skipped(AssumptionViolatedException e, Description description) {
        ReportLogging.skipped(testCategories, description);
        closeWebDriver();
    }


    public void setCategories(String[] testCategories) {
        this.testCategories = testCategories;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void closeWebDriver() {
        /*
          WebDriver is closed here because @After is invoked before @Rule.
          @Rule needs webdriver in order to take screenshot of failed tests
         */
        driver.close();
    }
}
