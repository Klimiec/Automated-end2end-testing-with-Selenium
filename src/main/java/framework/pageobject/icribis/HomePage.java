package framework.pageobject.icribis;

import framework.sharedkernel.annotations.PageObject;
import framework.sharedkernel.pageobject.BasePageObject;
import framework.featureobject.MenuHeaderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

@PageObject
public class HomePage extends BasePageObject {

    //#### login ###
    @FindBy(css = ".chiudi")
    private WebElement loginPopUp;
    @FindBy(css = ".dx > form > input:nth-child(1)")
    private WebElement loginEmail;
    @FindBy(css = ".dx > form > input:nth-child(2)")
    private WebElement loginPassword;
    @FindBy(css = ".dx > form > input.login")
    private WebElement loginSubmitButton;

    //#### registration ###
    @FindBy(css = "li.noOver:nth-child(3) > a:nth-child(1)")
    private WebElement registrationButtonInHeader;

    //#### search ###
    @FindBy(css = "dd.tab-title:nth-child(1) > a")
    private WebElement chooseSearchForCompany;
    @FindBy(css = "#companySearchForm > div:nth-child(2) > div:nth-child(1) > label:nth-child(1)")
    private WebElement companyByName;
    @FindBy(css = "#companySearchForm > div:nth-child(2) > div:nth-child(1) > label:nth-child(2)")
    private WebElement companyByTaxCode;
    @FindBy(css = "label.last:nth-child(3)")
    private WebElement companyByVatCode;
    @FindBy(css = "label.last:nth-child(4)")
    private WebElement companyByReaCode;
    @FindBy(css = "#companySearchFormInput")
    private WebElement companySearchInput;
    @FindBy(css = "#company_search_province")
    private WebElement companyProvince;

    @FindBy(css = "dd.tab-title:nth-child(2) > a")
    private WebElement chooseSearchForPerson;
    @FindBy(css = "#personSearchForm > div:nth-child(2) > div:nth-child(1) > label:nth-child(1)")
    private WebElement personByName;
    @FindBy(css = "#personSearchForm > div:nth-child(2) > div:nth-child(1) > label:nth-child(2)")
    private WebElement personByTaxCode;
    @FindBy(css = "#personSearchNameFormInput")
    private WebElement personName;
    @FindBy(css = "#personSearchSurnameFormInput")
    private WebElement personSurname;
    @FindBy(css = "#person_search_province")
    private WebElement personProvinceSelection;

    @FindBy(css = "div#companySearch input.button.tiny.search.rightIcon.lightRound")
    private WebElement companySearchSubmitButton;
    @FindBy(css = "div#personSearch input.button.tiny.search.rightIcon.lightRound")
    private WebElement personSearchSubmitButton;

    //## feature page
    public MenuHeaderPage menuHeader;

    public HomePage(WebDriver driver) {
        super(driver);
        menuHeader = new MenuHeaderPage(driver);
    }

    //############# GENERAL #############//

    public HomePage open() {
        goToHomePageUrl();
        provideSecurityPassword();
        verifyLoadedPage();
        return this;
    }

    private void goToHomePageUrl() {
        String homePageURL = urlBuilder.urlForEcommerceHomePage();
        driver.get(homePageURL);
    }

    private void provideSecurityPassword() {
        if(wait.isAlertPopUpPresent()) {
            driver.switchTo().alert().accept();
        }
    }

    @Override
    protected void verifyPage() {
        /* Not required in page loaded by open()function
         * responsibility of this method is moved to verifyLoadedPage()
         */
    }

    private HomePage verifyLoadedPage() {
        By sliders = By.cssSelector(".swiper-slide.contenitore");
        wait.forElementPresent(sliders);
        return this;
    }

    //############# API #############
    //############# LOGIN

    public HomePage gotoLoginPopUp() {
        loginPopUp.click();
        return this;
    }

    public HomePage fillEmail(String email) {
        loginEmail.sendKeys(email);
        return this;
    }

    public HomePage fillPassword(String password) {
        loginPassword.sendKeys(password);
        return this;
    }

    public HomePage clickLogInOnSuccess() {
        loginSubmitButton.click();
        return new HomePage(driver);
    }

    public LoginPage clickLogInOnFailure() {
        loginSubmitButton.click();
        return new LoginPage(driver);
    }

    //############# SEARCH

    public HomePage selecSearchForCompany() {
        chooseSearchForCompany.click();
        return this;
    }

    public HomePage selectSearchCompanyByName() {
        companyByName.click();
        return this;
    }

    public HomePage selectSearchCompanyByTaxCode() {
        companyByTaxCode.click();
        return this;
    }

    public HomePage selectSearchCompanyByVatCode() {
        companyByVatCode.click();
        return this;
    }

    public HomePage selectSearchCompanyByReaCode() {
        companyByReaCode.click();
        return this;
    }

    public HomePage selectCompanyProvince(String province) {
        Select provinceDropDown = new Select(companyProvince);
        provinceDropDown.selectByValue(province);
        return this;
    }

    public HomePage search(String searchInput) {
        companySearchInput.sendKeys(searchInput);
        return this;
    }

    public CompanySearchPage submitCompanySearchForCompanySearchPage() {
        companySearchSubmitButton.submit();
        return new CompanySearchPage(driver);
    }

    public CompanyCardPage submitCompanySearchForCompanyCardPage() {
        companySearchSubmitButton.submit();
        return new CompanyCardPage(driver);
    }

    //############# REGISTRATION #############//

    public RegistrationPage gotoRegistrationPage() {
        registrationButtonInHeader.click();
        return new RegistrationPage(driver);
    }

    //############# API end

}









































