package common.browser;


import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class FirefoxBrowser extends BrowserAbstract{

    private static final String FIREFOXDRIVER_PATH = "src/main/resources/drivers/firefox/geckodriver.exe";
    private static final String FIREFOXDRIVER_PROPERTY = "webdriver.gecko.driver";


    public WebDriver getInstance() {

        if (StringUtils.isEmpty(System.getProperty(FIREFOXDRIVER_PROPERTY))) {
            System.setProperty(FIREFOXDRIVER_PROPERTY, new File(FIREFOXDRIVER_PATH).getPath());
        }

        return new FirefoxDriver();
    }
}
