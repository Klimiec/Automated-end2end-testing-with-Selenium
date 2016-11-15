package framework.sharedkernel.testcases;

import common.configuration.BrowserSize;
import common.configuration.Configuration;
import common.driverprovider.DriverProvider;
import framework.sharedkernel.rules.TestExecutionVerification;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.WebDriver;


public abstract class BaseTest {

    protected WebDriver driver;
    protected String[] testCategories;


    @Rule
    public TestExecutionVerification testWatcher = new TestExecutionVerification();

    @Before
    public void initializeWebDriver() {

        driver = DriverProvider.driver();
        testWatcher.setDriver(driver);

        setBrowserWindowSize();
    }

    @After
    public void closeWebDriver() {
        /*
          WebDriver is closed in @Rule TestExecutionVerification.
          @After method is executed before @Rule starts.
          TestExecutionVerification make use of webdriver when taking screenshots.
         */
    }

    // base constructor for setting test categories
    public BaseTest() {

        assignTestCategory();
        testWatcher.setCategories(testCategories);
    }

    private void setBrowserWindowSize() {

        final BrowserSize browserSize = Configuration.browserSize();

        if (browserSize.isMaximized()) {
            driver.manage().window().maximize();
        } else {
            driver.manage().window().setSize(browserSize.dimension());
        }
    }

    protected abstract void assignTestCategory();
}
