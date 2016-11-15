package common.driverprovider;

import common.browser.Browser;
import common.configuration.Configuration;
import org.openqa.selenium.WebDriver;


public class DriverProvider {


    private static Browser browser;

    private DriverProvider() {}

    public static WebDriver driver() {

        if(browser == null) {
            browser = Configuration.browser();
        }

        return browser.getInstance();
    }
}
