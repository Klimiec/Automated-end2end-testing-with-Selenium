package framework.actionobject.icribis.login;

import common.users.UserCredentials;
import framework.sharedkernel.annotations.ActionObject;
import framework.sharedkernel.actionobject.BaseAction;
import framework.pageobject.icribis.HomePage;

@ActionObject
public class UserLogInAction implements BaseAction<HomePage, HomePage> {

    private UserCredentials userCredentials;

    @Override
    public HomePage execute(HomePage homePage) {

        return homePage
                .gotoLoginPopUp()
                .fillEmail(userCredentials.email())
                .fillPassword(userCredentials.password())
                .clickLogInOnSuccess();
    }

    public UserLogInAction withCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
        return this;
    }
}
