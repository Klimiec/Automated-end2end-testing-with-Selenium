package framework.pageobject.ecommerce;

import common.users.User;
import framework.sharedkernel.annotations.PageObject;
import framework.sharedkernel.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PageObject
public class RegistrationPage extends BasePageObject {


    //#### registration ###
    @FindBy(css = "label.last:nth-child(1)")
    private WebElement registerAsCompanyCheckbutton;
    @FindBy(css = ".regType > label:nth-child(2)")
    private WebElement registerAsPersonCheckbutton;

    @FindBy(css = "#user_first_name")
    private WebElement firstName;
    @FindBy(css = "#user_family_name")
    private WebElement lastName;
    @FindBy(css = "#user_phone")
    private WebElement phone;
    @FindBy(css = "#user_tax_code")
    private WebElement taxCode;
    @FindBy(css = "#user_company")
    private WebElement companyName;
    @FindBy(css = "#user_vat_code")
    private WebElement vatNumber;

    @FindBy(css = "#user_email")
    private WebElement email;
    @FindBy(css = "#user_password")
    private WebElement password;
    @FindBy(css = "#user_confirm_password")
    private WebElement passwordConfirmation;

    @FindBy(css = "div.large-12:nth-child(7) > div:nth-child(2) > label:nth-child(2)")
    private WebElement termsOfUseAcceptButton;
    @FindBy(css = "div.large-12:nth-child(7) > div:nth-child(2) > label:nth-child(3)")
    private WebElement termsOfUseRejectButton;
    @FindBy(css = "div.medium-12:nth-child(3) > label:nth-child(2)")
    private WebElement privacyPolicyAcceptButton;
    @FindBy(css = "div.medium-12:nth-child(3) > label:nth-child(3)")
    private WebElement privacyPolicyRejectButton;
    @FindBy(css = "div.large-12:nth-child(7) > div:nth-child(4) > label:nth-child(2)")
    private WebElement privacyPolic2yAcceptButton;
    @FindBy(css = "div.large-12:nth-child(7) > div:nth-child(4) > label:nth-child(3)")
    private WebElement privacyPolicy2RejectButton;

    @FindBy(css = "#submitButton")
    private WebElement submitFormButton;

    //### error labels
    @FindBy(id = "email.errors")
    private WebElement emailErrorLabel;
    @FindBy(css = "label[for='user_confirm_password']")
    private WebElement passwordConfirmationErrorLabel;

    //### selectors
    private By submitFormButtonSelector = By.cssSelector("#submitButton");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void verifyPage() {
        By registrationForm = By.cssSelector("#regForm");
        wait.forElementPresent(registrationForm);
    }

    //############# API #############
    //############# REGISTRATION Person/Company

    public RegistrationPage selectRegisterAsPerson() {
        registerAsPersonCheckbutton.click();
        return this;
    }

    public RegistrationPage selectRegisterAsCompany() {
        registerAsCompanyCheckbutton.click();
        return this;
    }


    public RegistrationPage fillFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
        return this;
    }

    public RegistrationPage fillLastName(String lastName) {
        this.lastName.sendKeys(lastName);
        return this;
    }

    public RegistrationPage fillPhone(String phone) {
        this.phone.sendKeys(phone);
        return this;
    }

    public RegistrationPage fillTaxCode(String taxCode) {
        this.taxCode.sendKeys(taxCode);
        return this;
    }

    public RegistrationPage fillCompanyName(String companyName) {
        this.companyName.sendKeys(companyName);
        return this;
    }

    public RegistrationPage fillVatNumber(String vatNumber) {
        this.vatNumber.sendKeys(vatNumber);
        return this;
    }

    public RegistrationPage fillEmail(String email) {
        this.email.sendKeys(email);
        return this;
    }

    public RegistrationPage fillPassword(String password) {
        this.password.sendKeys(password);
        return this;
    }

    public RegistrationPage fillPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation.sendKeys(passwordConfirmation);
        return this;
    }

    public RegistrationPage acceptTermsOfUse() {
        termsOfUseAcceptButton.click();
        return this;
    }

    public RegistrationPage rejectTermsOfUse() {
        termsOfUseAcceptButton.click();
        return this;
    }

    public RegistrationPage acceptPrivacyPolicy() {
        privacyPolicyAcceptButton.click();
        return this;
    }

    public RegistrationPage rejectPrivacyPolicy() {
        privacyPolicyRejectButton.click();
        return this;
    }

    public RegistrationPage acceptPrivacyPolicy2() {
        privacyPolic2yAcceptButton.click();
        return this;
    }

    public RegistrationPage rejectPrivacyPolicy2() {
        privacyPolicy2RejectButton.click();
        return this;
    }

    public RegistrationThankYouPage submitRegisterOnSuccess() {
        wait.forElementClickable(submitFormButtonSelector);
        submitFormButton.click();
        return new RegistrationThankYouPage(driver);
    }

    public RegistrationPage submitRegisterOnFailure() {
        wait.forElementClickable(submitFormButtonSelector);
        submitFormButton.click();
        return new RegistrationPage(driver);
    }

    public RegistrationPage fillRegistrationForm(RegistrationType registrationType, User userData) {

        switch (registrationType) {
            case PERSON:
                         selectRegisterAsPerson()
                        .fillFirstName(userData.firstName())
                        .fillLastName(userData.lastName())
                        .fillPhone(userData.phone())
                        .fillTaxCode(userData.taxCode())
                        .fillEmail(userData.email())
                        .fillPassword(userData.password())
                        .fillPasswordConfirmation(userData.passwordConfirmation())
                        .acceptTermsOfUse()
                        .acceptPrivacyPolicy()
                        .acceptPrivacyPolicy2();
                break;
            case COMPANY:
                        selectRegisterAsCompany()
                        .fillFirstName(userData.firstName())
                        .fillLastName(userData.lastName())
                        .fillPhone(userData.phone())
                        .fillTaxCode(userData.taxCode())
                        .fillCompanyName(userData.companyName())
                        .fillVatNumber(userData.vatNumber())
                        .fillEmail(userData.email())
                        .fillPassword(userData.password())
                        .fillPasswordConfirmation(userData.passwordConfirmation())
                        .acceptTermsOfUse()
                        .acceptPrivacyPolicy()
                        .acceptPrivacyPolicy2();
                break;
        }
        return this;
    }

    public String emailErrorLabel() {
        return emailErrorLabel.getText();
    }

    public String passwordConfirmationErrorLabel() {
        return passwordConfirmationErrorLabel.getText();
    }



    //############# API end

    public enum RegistrationType {
        PERSON,
        COMPANY
    }
}
