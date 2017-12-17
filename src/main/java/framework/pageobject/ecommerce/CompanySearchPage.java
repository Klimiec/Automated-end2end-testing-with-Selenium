package framework.pageobject.ecommerce;

import framework.sharedkernel.annotations.PageObject;
import framework.sharedkernel.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@PageObject
public class CompanySearchPage extends BasePageObject {


    @FindBy(css = "span.azienda > span:nth-child(2)")
    private WebElement searchTitle;
    @FindBy(css = "div.large-3:nth-child(1)")
    private WebElement resultSetSize;

    public CompanySearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void verifyPage() {
        By resultList = By.cssSelector(".risultati");
        By googleMap = By.cssSelector("#icrif-companies-map");
        wait.forElementPresent(resultList);
        wait.forElementPresent(googleMap);
    }

    //############# API #############

    public String searchTitle() {
        return searchTitle.getText();
    }

    public int resultSetSize() {
        final String searchSize = resultSetSize.getText().split("/")[1].replace(")","");
        return Integer.parseInt(searchSize);
    }

    //############# API end

}
