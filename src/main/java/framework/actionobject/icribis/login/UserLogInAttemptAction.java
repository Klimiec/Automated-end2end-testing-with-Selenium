package framework.actionobject.icribis.login;


import framework.sharedkernel.annotations.ActionObject;
import framework.sharedkernel.actionobject.BaseAction;
import framework.pageobject.icribis.HomePage;
import framework.pageobject.icribis.LoginPage;

@ActionObject
public class UserLogInAttemptAction implements BaseAction<HomePage, LoginPage> {

    private UserCredentials user;

    @Override
    public LoginPage execute(HomePage homePage) {

        return homePage
                .gotoLoginPopUp()
                .fillEmail(user.getEmail())
                .fillPassword(user.getPassword())
                .clickLogInOnFailure();
    }

    public UserLogInAttemptAction makeUserPasswordIncorrect() {
        user.setPassword("incorrect");
        return this;
    }

    public UserLogInAttemptAction makeUserEmailIncorrect() {
        user.setEmail("incorrect@gmail.com");
        return this;
    }

    public UserLogInAttemptAction withCredentials(common.users.UserCredentials user) {
        this.user = new UserCredentials(user.email(), user.password());
        return this;
    }

    private class UserCredentials {
        private String email;
        private String password;

        public UserCredentials(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
