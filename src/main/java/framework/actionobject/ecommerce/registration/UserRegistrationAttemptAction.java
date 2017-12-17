package framework.actionobject.ecommerce.registration;

import common.configuration.Configuration;
import common.users.User;
import framework.sharedkernel.annotations.ActionObject;
import framework.sharedkernel.actionobject.BaseAction;
import framework.pageobject.ecommerce.HomePage;
import framework.pageobject.ecommerce.RegistrationPage;
import framework.pageobject.ecommerce.RegistrationPage.RegistrationType;


@ActionObject
public class UserRegistrationAttemptAction implements BaseAction<HomePage,RegistrationPage> {

    protected User userData;
    protected RegistrationPage.RegistrationType registrationType;

    @Override
    public RegistrationPage execute(HomePage homePage) {

        return homePage
                .gotoRegistrationPage()
                .fillRegistrationForm(registrationType, userData)
                .submitRegisterOnFailure();
    }

    public UserRegistrationAttemptAction asPrivatePerson() {
        registrationType = RegistrationType.PERSON;
        return this;
    }

    public UserRegistrationAttemptAction withEmailAlreadyRegistered() {

        this.userData = new User.UserBuilder(userData)
                            .withEmail(Configuration.credentials().registeredEmail())
                            .build();
        return this;
    }

    public UserRegistrationAttemptAction withIncorrectPasswordConfirmation() {
        this.userData = new User.UserBuilder(userData)
                            .withPasswordConfirmation(userData.passwordConfirmation() + "incorrect")
                            .build();
        return this;
    }

    public UserRegistrationAttemptAction withData(User userData) {
        this.userData = userData;
        return this;
    }


}
