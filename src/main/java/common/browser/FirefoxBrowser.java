package common.browser;


import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class FirefoxBrowser extends BrowserAbstract{

    private static final String CHROME_DRIVER_PATH_FORMAT = "src/main/resources/drivers/firefox/firefoxdriver_%s";
    private static final String FIREFOXDRIVER_PATH_LINUX = String.format(CHROME_DRIVER_PATH_FORMAT, "linux64/geckodriver");
    private static final String FIREFOXDRIVER_PATH_WINDOWS = String.format(CHROME_DRIVER_PATH_FORMAT, "win32/geckodriver.exe");
    private static final String FIREFOXDRIVER_PROPERTY = "webdriver.gecko.driver";


    public WebDriver getInstance() {

        if (StringUtils.isEmpty(System.getProperty(FIREFOXDRIVER_PROPERTY))) {
            String chromeBinaryPath = chromeDriverPathForOS();
            System.setProperty(FIREFOXDRIVER_PROPERTY, new File(chromeBinaryPath).getPath());
        }

        return new FirefoxDriver();
    }

    private String chromeDriverPathForOS() {
        String operatingSystem = System.getProperty("os.name").toUpperCase();

        if(operatingSystem.contains("WINDOWS")) {
            return FIREFOXDRIVER_PATH_WINDOWS;
        }else if(operatingSystem.contains("LINUX")) {
            return FIREFOXDRIVER_PATH_LINUX;
        }

        return "";
    }


}
