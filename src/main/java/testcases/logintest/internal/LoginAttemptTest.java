package testcases.logintest.internal;

import common.configuration.Configuration;
import common.configuration.Credentials;
import common.users.UserCredentials;
import framework.actionobject.ecommerce.login.UserLogInAttemptAction;
import framework.assertobject.ecommerce.LoginPageAssert;
import framework.sharedkernel.testcases.BaseTest;
import framework.pageobject.ecommerce.HomePage;
import framework.pageobject.ecommerce.LoginPage;
import org.junit.Ignore;
import org.junit.Test;

import static testcases.TestCategory.INTERNAL_LOGIN;
import static testcases.TestCategory.LOGIN;


public class LoginAttemptTest extends BaseTest {

    Credentials credentials =  Configuration.credentials();

    @Ignore
    @Test
    public void unsuccessfulLoggedInWithIncorrectPassword() {
        //given
        UserCredentials user = credentials.registeredUser();
        HomePage homePage = new HomePage(driver).open();
        //when
        LoginPage loginPage = homePage.execute(new UserLogInAttemptAction()
                                                    .withCredentials(user)
                                                    .makeUserPasswordIncorrect());
        //then
        thenLoginPage(loginPage).containsErrorMessage();
    }

    @Test
    public void unsuccessfulLoggedInWithIncorrectEmail() {
        //given
        UserCredentials user = credentials.registeredUser();
        HomePage homePage = new HomePage(driver).open();
        //when
        LoginPage loginPage = homePage.execute(new UserLogInAttemptAction()
                                                    .withCredentials(user)
                                                    .makeUserEmailIncorrect());
        //then
        thenLoginPage(loginPage).containsErrorMessage();
    }

    private LoginPageAssert thenLoginPage(LoginPage loginPage) {
        return new LoginPageAssert(loginPage);
    }

    @Override
    protected void assignTestCategory() {
        testCategories = new String[]{LOGIN, INTERNAL_LOGIN};
    }
}
