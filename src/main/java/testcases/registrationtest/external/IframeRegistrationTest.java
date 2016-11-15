package testcases.registrationtest.external;


import framework.sharedkernel.testcases.BaseTest;
import static testcases.TestCategory.*;

public class IframeRegistrationTest extends BaseTest {

    @Override
    protected void assignTestCategory() {
        testCategories = new String[]{LOGIN, EXTERNAL_REGISTRATION};
    }
}
