package framework.assertobject.ecommerce;

import framework.pageobject.ecommerce.RegistrationPage;
import framework.sharedkernel.annotations.AssertObject;

import static org.assertj.core.api.Assertions.assertThat;


@AssertObject
public class RegistrationPageAssert {

    private RegistrationPage registrationPage;

    public RegistrationPageAssert(RegistrationPage registrationPage) {
        this.registrationPage = registrationPage;
    }

    public RegistrationPageAssert containsEmailErrorMessage() {
        assertThat(registrationPage.emailErrorLabel()).isNotEmpty();
        return this;
    }

    public RegistrationPageAssert containsPasswordConfirmationError() {
        assertThat(registrationPage.passwordConfirmationErrorLabel()).isNotEmpty();
        return this;
    }
}
