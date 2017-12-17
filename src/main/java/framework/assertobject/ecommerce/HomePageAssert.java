package framework.assertobject.ecommerce;

import common.users.UserCredentials;
import framework.assertobject.ecommerce.featureobject.MenuHeaderPageAssert;
import framework.sharedkernel.annotations.AssertObject;
import framework.pageobject.ecommerce.HomePage;

@AssertObject
public class HomePageAssert {

    private HomePage homePage;
    private MenuHeaderPageAssert menuHeaderPageAssert;

    public HomePageAssert(HomePage homePage) {
        this.homePage = homePage;
        this.menuHeaderPageAssert = new MenuHeaderPageAssert(homePage.menuHeader);
    }

    public HomePageAssert hasLoggedInUserAs(UserCredentials user) {
        menuHeaderPageAssert.isLoggedInAs(user);
        return this;
    }
}
