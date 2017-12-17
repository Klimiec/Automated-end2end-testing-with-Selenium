package framework.pageobject.ecommerce;


import framework.sharedkernel.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePageObject {

    @FindBy(css = "#userLoginErrorMsgs")
    private WebElement loginErrorLabel;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void verifyPage() {
        By userLoginForm = By.cssSelector("#userLoginWrpr");
        wait.forElementPresent(userLoginForm);
    }

    //############# API #############

    public String loginErrorMessage() {
        return loginErrorLabel.getText();
    }


}
