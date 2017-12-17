package testcases.registrationtest.internal;


import common.users.User;
import common.users.UserFactory;
import framework.actionobject.ecommerce.registration.UserRegistrationAttemptAction;
import framework.assertobject.ecommerce.RegistrationPageAssert;
import framework.sharedkernel.testcases.BaseTest;
import framework.pageobject.ecommerce.HomePage;
import framework.pageobject.ecommerce.RegistrationPage;
import org.junit.Test;
import static testcases.TestCategory.*;
public class RegistrationAttemptTest extends BaseTest {


    @Test
    public void unsuccessfulRegistrationAsPrivatePersonWithEmailAlreadyRegistered() {

        //given
        HomePage homePage = new HomePage(driver).open();
        //when
        User user = UserFactory.createPrivatePerson();
        RegistrationPage registrationPage = homePage.execute(new UserRegistrationAttemptAction()
                                                                    .asPrivatePerson()
                                                                    .withData(user)
                                                                    .withEmailAlreadyRegistered());
        //then
        thenRegistrationPage(registrationPage).containsEmailErrorMessage();
    }

    @Test
    public void unsuccessfulRegistrationAsPrivatePersonWithWrongPasswordConfirmation() {

        //given
        HomePage homePage = new HomePage(driver).open();
        //when
        User user = UserFactory.createPrivatePerson();
        RegistrationPage registrationPage = homePage.execute(new UserRegistrationAttemptAction()
                                                                    .asPrivatePerson()
                                                                    .withData(user)
                                                                    .withIncorrectPasswordConfirmation());
        //then
        thenRegistrationPage(registrationPage).containsPasswordConfirmationError();
    }

    private RegistrationPageAssert thenRegistrationPage(RegistrationPage registrationPage) {
        return new RegistrationPageAssert(registrationPage);
    }

    @Override
    protected void assignTestCategory() {
        testCategories = new String[]{REGISTRATION, INTERNAL_REGISTRATION};
    }
}
