package framework.assertobject.backoffice;

import common.users.BackOfficeUser;
import common.users.User;
import framework.sharedkernel.annotations.AssertObject;

import static org.assertj.core.api.Assertions.assertThat;


@AssertObject
public class BackOfficeUserAssert {

    private BackOfficeUser backOfficeUser;

    public BackOfficeUserAssert(BackOfficeUser backOfficeUser) {

        this.backOfficeUser = backOfficeUser;
    }

    public BackOfficeUserAssert isEqualTo(User userData) {
        assertThat(userData.email()).isEqualTo(backOfficeUser.email());
        assertThat(userData.firstName()).isEqualTo(backOfficeUser.firstName());
        assertThat(userData.lastName()).isEqualTo(backOfficeUser.lastName());
        return this;
    }

    public BackOfficeUserAssert isRegisteredAsPrivatePerson() {
        assertThat(backOfficeUser.userType()).isEqualTo("Individual");
        assertThat(backOfficeUser.registrationType()).isEqualTo("Light");
        return this;
    }

    public BackOfficeUserAssert isRegisteredAsCompany() {
        assertThat(backOfficeUser.userType()).isEqualTo("Company");
        assertThat(backOfficeUser.registrationType()).isEqualTo("Light");
        return this;
    }

    public BackOfficeUserAssert hasNotConfirmedStatus() {
        assertThat(backOfficeUser.registrationStatus()).isEqualTo("Unconfirmed");
        return this;
    }
}
