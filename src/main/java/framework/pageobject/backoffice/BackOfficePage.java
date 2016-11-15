package framework.pageobject.backoffice;



import framework.sharedkernel.annotations.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@PageObject
public class BackOfficePage extends BackOfficeBasePageObject {

    public BackOfficePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void verifyPage() {
        //Not required in backoffice
    }

    //############# API #############

}