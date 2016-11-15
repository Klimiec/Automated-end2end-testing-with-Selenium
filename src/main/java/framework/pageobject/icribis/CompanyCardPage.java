package framework.pageobject.icribis;

import framework.sharedkernel.annotations.PageObject;
import framework.sharedkernel.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@PageObject
public class CompanyCardPage extends BasePageObject {

    //#### free report ###
    @FindBy(css = "#fiscalCode-value")
    private WebElement taxCode;
    @FindBy(css = "#localBusinessId-value")
    private WebElement reaCode;
    @FindBy(css = "#province-value")
    private WebElement province;

    public CompanyCardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void verifyPage() {
        By companyCardContent = By.cssSelector("section.risultati.azienda");
        wait.forElementPresent(companyCardContent);
    }

    //############# API #############

    public String taxCode() {
        return taxCode.getText();
    }

    public String reaCode() {
        String cciaaRea = reaCode.getText();
        return cciaaRea.split("-")[1].trim();
    }

    public String province() {
        return province.getText();
    }

    //############# API end

}
