package testcases.logintest.internal;


import common.configuration.Configuration;
import common.users.UserCredentials;
import framework.actionobject.icribis.login.UserLogInAction;
import framework.assertobject.icribis.HomePageAssert;
import framework.sharedkernel.testcases.BaseTest;
import framework.pageobject.icribis.HomePage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import static testcases.TestCategory.INTERNAL_LOGIN;
import static testcases.TestCategory.LOGIN;
@RunWith(Parameterized.class)
public class LoginTest extends BaseTest {

    @Parameter
    public UserCredentials user;

    @Parameters(name = "{index} : {0}")
    public static Iterable<?> registeredUserCredentials() {
        return Configuration.credentials().registeredUsers();
    }

    @Test
    public void successfulLoggedIn() {
        //given
        HomePage homePage = new HomePage(driver).open();
        //when
        homePage = homePage.execute(new UserLogInAction().withCredentials(user));
        //then
        thenHomePage(homePage).hasLoggedInUserAs(user);
    }

    private HomePageAssert thenHomePage(HomePage homePage) {
        return new HomePageAssert(homePage);
    }

    @Override
    protected void assignTestCategory() {
        testCategories = new String[]{LOGIN, INTERNAL_LOGIN};
    }
}