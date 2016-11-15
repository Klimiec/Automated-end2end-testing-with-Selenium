package framework.pageobject.backoffice;

//@formatter:off
import common.users.BackOfficeUser;
import common.users.User;
import framework.sharedkernel.annotations.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import static common.users.BackOfficeUser.BackOfficeUserBuilder;

@PageObject
public class UsersSearchPage extends BackOfficeBasePageObject {

    protected UsersSearchFramePage usersSearchFramePage;
    private Object userData;

    public UsersSearchPage(WebDriver driver) {
        super(driver);
        usersSearchFramePage = new UsersSearchFramePage(driver);
    }

    @Override
    protected void verifyPage() {
        //Not required in backoffice
    }

    //############# API #############

    public UsersSearchPage findUser(User userData) {
        usersSearchFramePage.findUser(userData);
        return this;
    }

    public BackOfficeUser getUserData() {
        return usersSearchFramePage.getUserData();
    }


    class UsersSearchFramePage extends AdminContentFramePage {

        // search input
        @FindBy(css = "#id.userId")
        private WebElement userId;
        @FindBy(css = "#email")
        private WebElement email;
        @FindBy(css = "#firstName")
        private WebElement firstName;
        @FindBy(css = "#familyName")
        private WebElement familyName;
        @FindBy(linkText = "Search")
        private WebElement searchSubmit;

        // user data from one row
        @FindAll(@FindBy(css = ".oddrowbg > td"))
        private List<WebElement> searchedUserDataRow;

        public UsersSearchFramePage(WebDriver driver) {
            super(driver);
        }

        public UsersSearchFramePage findUser(User user) {

            switchToAdminContentFrame();
            fillSearchForm(user);
            submitSearchForm();
            returnFromAdminContentFrameToBodyFrame();
            return this;
        }

        public BackOfficeUser getUserData() {
            switchToAdminContentFrame();
            BackOfficeUser backOfficeUser = parseUserRow();
            returnFromAdminContentFrameToBodyFrame();
            return backOfficeUser;
        }

        public UsersSearchFramePage verifyPage() {
            switchToAdminContentFrame();
            By searchUserList = By.cssSelector(".listArea");
            wait.forElementPresent(searchUserList);
            returnFromAdminContentFrameToBodyFrame();
            return this;
        }

        private UsersSearchFramePage fillSearchForm(User userData) {
            email.sendKeys(userData.email());
            return this;
        }

        private UsersSearchFramePage submitSearchForm() {
            searchSubmit.submit();
            return this;
        }

        private void switchToAdminContentFrame() {
            driver.switchTo().frame(adminContentFrame);
        }

        private void returnFromAdminContentFrameToBodyFrame() {
            driver.switchTo().defaultContent();
            driver.switchTo().frame(bodyFrame);
        }

        private BackOfficeUser parseUserRow() {

            if (searchedUserDataRow.size() != 20) {
                throw new RuntimeException("More then one user found! Only one is allowed");
            }

            final BackOfficeUserBuilder backOfficeUserBuilder = new BackOfficeUserBuilder();

            backOfficeUserBuilder
                    .withEmail(searchedUserDataRow.get(2).findElement(By.tagName("a")).getText())
                    .withFirstName(searchedUserDataRow.get(3).getText())
                    .withLastName(searchedUserDataRow.get(4).getText())
                    .withUserType(searchedUserDataRow.get(14).getText())
                    .withRegistrationStatus(searchedUserDataRow.get(15).getText())
                    .withRegistrationType(searchedUserDataRow.get(16).getText());

            return backOfficeUserBuilder.build();
        }
    }
}
//@formatter:on