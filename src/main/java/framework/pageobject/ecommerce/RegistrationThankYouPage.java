package framework.pageobject.ecommerce;

import framework.sharedkernel.pageobject.BasePageObject;
import framework.featureobject.MenuHeaderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class RegistrationThankYouPage extends BasePageObject {

    @FindBy(css = "body > div.row.cortesia > div.large-8.medium-12.small-12.columns.text-center > p")
    private WebElement welcomeMessage;

    public MenuHeaderPage menuHeader;

    public RegistrationThankYouPage(WebDriver driver) {
        super(driver);
        menuHeader = new MenuHeaderPage(driver);
    }

    @Override
    protected void verifyPage() {
        By successfulSign = By.cssSelector(".titoloOk");
        wait.forElementPresent(successfulSign);
    }

    //############# API #############

    public String welcomeMessage() {
        return welcomeMessage.getText();
    }
}
