package framework.assertobject.icribis;


import common.users.User;
import common.users.UserCredentials;
import framework.assertobject.icribis.featureobject.MenuHeaderPageAssert;
import framework.sharedkernel.annotations.AssertObject;
import framework.pageobject.icribis.RegistrationThankYouPage;

import static org.assertj.core.api.Assertions.assertThat;

@AssertObject
public class RegistrationThankYouPageAssert {

    private RegistrationThankYouPage thankYouPage;
    private MenuHeaderPageAssert menuHeaderPageAssert;

    public RegistrationThankYouPageAssert(RegistrationThankYouPage thankYouPage) {
        this.thankYouPage = thankYouPage;
        this.menuHeaderPageAssert = new MenuHeaderPageAssert(thankYouPage.menuHeader);
    }

    public RegistrationThankYouPageAssert containsWelcomeMessage() {
        assertThat(thankYouPage.welcomeMessage()).isNotEmpty();
        return this;
    }

    public RegistrationThankYouPageAssert hasLoggedInUserAs(User user) {
        UserCredentials userCredentials = new UserCredentials(user.email(), user.password(), user.firstName(), user.lastName());
        menuHeaderPageAssert.isLoggedInAs(userCredentials);
        return this;
    }
}
