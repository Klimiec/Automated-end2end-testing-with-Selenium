package testcases.registrationtest.internal;


import common.users.BackOfficeUser;
import common.users.User;
import common.users.UserFactory;
import framework.actionobject.backoffice.UserSearchAction;
import framework.actionobject.icribis.registration.UserRegistrationAction;
import framework.assertobject.backoffice.BackOfficeUserAssert;
import framework.assertobject.icribis.RegistrationThankYouPageAssert;
import framework.sharedkernel.testcases.BaseTest;
import framework.pageobject.backoffice.OpenCmsAdminPage;
import framework.pageobject.icribis.HomePage;
import framework.pageobject.icribis.RegistrationThankYouPage;
import org.junit.Ignore;
import org.junit.Test;

import static testcases.TestCategory.INTERNAL_REGISTRATION;
import static testcases.TestCategory.REGISTRATION;
public class RegistrationTest extends BaseTest {

    @Test
    public void successfulRegistrationAsPrivatePerson() {

        //given
        HomePage homePage = new HomePage(driver).open();
        //when
        User user = UserFactory.createPrivatePerson();
        RegistrationThankYouPage thankYouPage = homePage.execute(new UserRegistrationAction()
                                                                        .asPrivatePerson()
                                                                        .withData(user));
        //then
        thenRegistrationThankYouPage(thankYouPage).containsWelcomeMessage().hasLoggedInUserAs(user);

        BackOfficeUser backOfficeUser = new OpenCmsAdminPage(driver).open().execute(new UserSearchAction(user)).getUserData();
        thenBackOfficeUser(backOfficeUser).isEqualTo(user).isRegisteredAsPrivatePerson().hasNotConfirmedStatus();
    }


    @Test
    public void successfulRegistrationAsCompany() {

        //given
        HomePage homePage = new HomePage(driver).open();
        //when
        User user = UserFactory.createCompany();
        RegistrationThankYouPage thankYouPage = homePage.execute(new UserRegistrationAction()
                                                                       .asCompany()
                                                                       .withData(user));
        //then
        thenRegistrationThankYouPage(thankYouPage).containsWelcomeMessage().hasLoggedInUserAs(user);

        BackOfficeUser backOfficeUser = new OpenCmsAdminPage(driver).open().execute(new UserSearchAction(user)).getUserData();
        thenBackOfficeUser(backOfficeUser).isEqualTo(user).isRegisteredAsCompany().hasNotConfirmedStatus();
    }

    private BackOfficeUserAssert thenBackOfficeUser(BackOfficeUser backOfficeUser) {
        return new BackOfficeUserAssert(backOfficeUser);
    }

    private RegistrationThankYouPageAssert thenRegistrationThankYouPage(RegistrationThankYouPage thankYouPage) {
        return new RegistrationThankYouPageAssert(thankYouPage);
    }

    @Override
    protected void assignTestCategory() {
        testCategories = new String[]{REGISTRATION, INTERNAL_REGISTRATION};
    }
}
