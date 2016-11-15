package testcases.registrationtest.facebook;

import framework.sharedkernel.testcases.BaseTest;
import static testcases.TestCategory.*;

public class FacebookRegistrationTest extends BaseTest {

    @Override
    protected void assignTestCategory() {
        testCategories = new String[]{LOGIN, FACEBOOK_REGISTRATION};
    }
}
