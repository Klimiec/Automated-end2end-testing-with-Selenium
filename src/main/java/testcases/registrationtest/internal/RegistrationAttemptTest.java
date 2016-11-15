package testcases.registrationtest.internal;


import common.users.User;
import common.users.UserFactory;
import framework.actionobject.icribis.registration.UserRegistrationAttemptAction;
import framework.assertobject.icribis.RegistrationPageAssert;
import framework.sharedkernel.testcases.BaseTest;
import framework.pageobject.icribis.HomePage;
import framework.pageobject.icribis.RegistrationPage;
import org.junit.Ignore;
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
