package framework.assertobject.icribis.featureobject;


import common.users.UserCredentials;
import framework.sharedkernel.annotations.AssertObject;
import framework.featureobject.MenuHeaderPage;

import static org.assertj.core.api.Assertions.assertThat;

@AssertObject
public class MenuHeaderPageAssert {

    private MenuHeaderPage menuHeader;

    public MenuHeaderPageAssert(MenuHeaderPage menuHeader) {
        this.menuHeader = menuHeader;
    }

    public MenuHeaderPageAssert isLoggedInAs(UserCredentials user) {
        assertThat(menuHeader.loggedInUserName()).isEqualToIgnoringCase(user.name() + " " + user.surname());
        return this;
    }

}
