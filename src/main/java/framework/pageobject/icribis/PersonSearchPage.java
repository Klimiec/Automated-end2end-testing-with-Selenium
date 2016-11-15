package framework.pageobject.icribis;

import framework.sharedkernel.annotations.PageObject;
import framework.sharedkernel.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@PageObject
public class PersonSearchPage extends BasePageObject {

    //## page verification
    private By resultList = By.cssSelector(".risultati");

    public PersonSearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void verifyPage() {
        wait.forElementPresent(resultList);
    }

    //############# API #############
}
