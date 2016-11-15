package framework.pageobject.backoffice;

import framework.sharedkernel.pageobject.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public abstract class BackOfficeBasePageObject extends BasePageObject {

    protected AdminMenuFramePage adminMenuFrame;

    public BackOfficeBasePageObject(WebDriver driver) {
        super(driver);
        adminMenuFrame = new AdminMenuFramePage(driver);
    }

    // ##### #####  Admin Menu ##### #####
    public UsersSearchPage clickUsersSearchLink() {
        return adminMenuFrame.usersSearch();
    }

    public OrdersHistoryPage ordersHistory() {
        return adminMenuFrame.ordersHistory();
    }

    private class AdminMenuFramePage {

        @FindBy(css = "frame[name='admin_menu']")
        private WebElement adminMenuFrame;
        @FindBy(css = "frame[name='body']")
        private WebElement bodyFrame;

        @FindBy(linkText = "Users search")
        private WebElement usersSearchLink;
        @FindBy(linkText = "Orders history")
        private WebElement ordersHistoryLink;

        private final WebDriver driver;

        public AdminMenuFramePage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        public UsersSearchPage usersSearch() {
            switchToAdminMenuFrame();
            usersSearchLink.click();
            returnFromAdminMenuFrameToBodyFrame();
            return new UsersSearchPage(driver);
        }

        public OrdersHistoryPage ordersHistory() {
            switchToAdminMenuFrame();
            ordersHistoryLink.click();
            returnFromAdminMenuFrameToBodyFrame();
            return new OrdersHistoryPage(driver);
        }

        private void returnFromAdminMenuFrameToBodyFrame() {
            driver.switchTo().defaultContent();
            driver.switchTo().frame(bodyFrame);
        }

        private void switchToAdminMenuFrame() {
            driver.switchTo().frame(adminMenuFrame);
        }
    }

    // ##### #####  Right Menu  methods ##### #####
    //          declare in concrete classes
    // ##### ##### ##### ##### ##### ##### ##### ##

    abstract class AdminContentFramePage {

        @FindBy(css = "frame[name='admin_content']")
        protected WebElement adminContentFrame;
        @FindBy(css = "frame[name='body']")
        protected WebElement bodyFrame;

        protected  WebDriver driver;

        public AdminContentFramePage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        private void switchToAdminContentFrame() {
            driver.switchTo().frame(adminContentFrame);
        }

        private void returnFromAdminContentFrameToBodyFrame() {
            driver.switchTo().defaultContent();
            driver.switchTo().frame(bodyFrame);
        }
    }
}
