package framework.assertobject.icribis;

import framework.sharedkernel.annotations.AssertObject;
import framework.pageobject.icribis.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

@AssertObject
public class LoginPageAssert {

    private LoginPage loginPage;

    public LoginPageAssert(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    public LoginPageAssert containsErrorMessage() {
        assertThat(loginPage.loginErrorMessage()).isNotEmpty();
        return this;
    }
}
