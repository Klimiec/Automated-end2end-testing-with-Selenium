package framework.featureobject;

import framework.sharedkernel.annotations.PageObject;
import framework.sharedkernel.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@PageObject
public class MenuHeaderPage extends BasePageObject {

    @FindBy(css = ".menuTop li.open:nth-child(1) > a:nth-child(1)")
    private WebElement loggedUserName;

    public MenuHeaderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void verifyPage() {
        //brak weryfikacji ponieważ jest to sprawdzane w homepage jak jeszcze nie ma strony
    }

    //############# API #############

    public String loggedInUserName() {
        return loggedUserName.getText();
    }
}