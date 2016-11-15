package framework.actionobject.icribis.registration;

import common.users.User;
import framework.sharedkernel.actionobject.BaseAction;
import framework.sharedkernel.annotations.ActionObject;
import framework.pageobject.icribis.HomePage;
import framework.pageobject.icribis.RegistrationPage.RegistrationType;
import framework.pageobject.icribis.RegistrationThankYouPage;

@ActionObject
public class UserRegistrationAction implements BaseAction<HomePage,RegistrationThankYouPage> {

    protected User userData;
    protected RegistrationType registrationType;

    @Override
    public RegistrationThankYouPage execute(HomePage homePage) {

        return homePage
                .gotoRegistrationPage()
                .fillRegistrationForm(registrationType, userData)
                .submitRegisterOnSuccess();
    }

    public UserRegistrationAction asPrivatePerson() {
        registrationType = RegistrationType.PERSON;
        return this;
    }

    public UserRegistrationAction asCompany() {
        registrationType = RegistrationType.COMPANY;
        return this;
    }

    public UserRegistrationAction withData(User userData) {
        this.userData = userData;
        return this;
    }
}
