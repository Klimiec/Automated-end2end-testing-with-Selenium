package framework.sharedkernel.pageobject;

import common.driverprovider.wait.Wait;
import common.url.UrlFactory;
import framework.sharedkernel.actionobject.BaseAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePageObject {

    protected WebDriver driver;
    protected Wait wait;
    protected UrlFactory urlBuilder;

    public BasePageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new Wait(driver);
        this.urlBuilder = new UrlFactory();
        PageFactory.initElements(driver, this);
        verifyPage();
    }

    public <EntryPage extends BasePageObject, OutputPage extends BasePageObject> OutputPage execute(BaseAction<EntryPage, OutputPage> action) {
        return action.execute((EntryPage)this);
    }

    protected abstract void verifyPage();
}
