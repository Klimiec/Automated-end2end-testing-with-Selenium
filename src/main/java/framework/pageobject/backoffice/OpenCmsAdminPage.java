package framework.pageobject.backoffice;

//@formatter:off
import common.configuration.Configuration;
import common.configuration.Credentials;
import framework.sharedkernel.annotations.PageObject;
import framework.sharedkernel.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

@PageObject
public class OpenCmsAdminPage extends BasePageObject {

    //login popup
    @FindBy(css = "#ocUname")
    private WebElement userName;
    @FindBy(css = "#ocPword")
    private WebElement password;
    @FindBy(css = ".loginbutton")
    private WebElement loginSubmitButton;

    // frames
    @FindBy(css = "frame[name='head']")
    private WebElement headFrame;
    @FindBy(css = "frame[name='body']")
    private WebElement bodyFrame;

    // head frame
    @FindBy(css = "body > table > tbody > tr > td:nth-child(11) > form > div > select")
    private WebElement siteDropDown;
    @FindBy(css = "body > table > tbody > tr > td:nth-child(15) > form > div > select")
    private WebElement viewDropDown;

    public OpenCmsAdminPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void verifyPage() {
        /* Not required in page loaded by open()function
         * responsibility of this method is moved to verifyLoadedPage()
         */
    }

    //############# API #############

    public OpenCmsAdminPage open() {

        goToAdminPageUrl()
                .provideSecurityPassword()
                .loginToAdminPage()
                .closePopUpWindow()
                .verifyLoadedPage();
        return this;
    }

    public BackOfficePage navigateToBackOffice() {

        switchToHeadFrame()
                .selectSite(Configuration.country())
                .selectAdministrationView()
                .returnFromFrame()
                .switchToBodyFrame();
        return new BackOfficePage(driver);
    }

    private OpenCmsAdminPage verifyLoadedPage() {
        By headFrame = By.cssSelector("frame[name='head']");
        wait.forElementPresent(headFrame);
        By bodyFrame = By.cssSelector("frame[name='body']");
        wait.forElementPresent(bodyFrame);
        return this;
    }

    //############# API end

    private OpenCmsAdminPage goToAdminPageUrl() {
        String homePageURL = urlBuilder.urlForCmsAdminPage();
        driver.get(homePageURL);
        return this;
    }

    private OpenCmsAdminPage provideSecurityPassword() {
        if(wait.isAlertPopUpPresent()) {
            driver.switchTo().alert().accept();
        }
        return this;
    }

    private OpenCmsAdminPage loginToAdminPage() {
        Credentials credentials =  Configuration.credentials();
        userName.sendKeys(credentials.adminUserName());
        password.sendKeys(credentials.adminPassword());
        loginSubmitButton.submit();
        return this;
    }

    private OpenCmsAdminPage closePopUpWindow() {

        final String parentWindow = driver.getWindowHandle();

        // switch to popup
        for(String winHandle : driver.getWindowHandles()){
            if(!winHandle.equals(parentWindow)) {
                driver.switchTo().window(winHandle);
            }
        }

        final String backOfficeURL = driver.getCurrentUrl();
        driver.close();
        driver.switchTo().window(parentWindow);
        driver.navigate().to(backOfficeURL);

        return this;
    }

    private OpenCmsAdminPage switchToHeadFrame() {
        driver.switchTo().frame(headFrame);
        return this;
    }

    private OpenCmsAdminPage switchToBodyFrame() {
        driver.switchTo().frame(bodyFrame);
        return this;
    }

    public OpenCmsAdminPage returnFromFrame() {
        driver.switchTo().defaultContent();
        return this;
    }

    private OpenCmsAdminPage selectSite(String country) {

        Select siteDropDown = new Select(this.siteDropDown);
        switch (country) {
            case "IT":
                siteDropDown.selectByValue("/sites/italy");
                break;
            case "AT":
                siteDropDown.selectByValue("/sites/austria");
                break;
            case "CZ":
                siteDropDown.selectByValue("/sites/czech");
                break;
            case "PL":
                siteDropDown.selectByValue("/sites/poland");
                break;
        }
        return this;
    }

    private OpenCmsAdminPage selectAdministrationView() {

        Select viewDropDown = new Select(this.viewDropDown);
        viewDropDown.selectByVisibleText("iCrif Administration");
        return this;
    }
}
//@formatter:on