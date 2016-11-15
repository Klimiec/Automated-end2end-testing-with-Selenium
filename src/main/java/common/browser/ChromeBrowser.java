package common.browser;


import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class ChromeBrowser extends BrowserAbstract{

    private static final String CHROME_DRIVER_PATH_FORMAT = "src/main/resources/drivers/chrome/chromedriver_%s";
    private static final String CHROMEDRIVER_PATH_LINUX = String.format(CHROME_DRIVER_PATH_FORMAT, "linux64/chromedriver");
    private static final String CHROMEDRIVER_PATH_WINDOWS = String.format(CHROME_DRIVER_PATH_FORMAT, "win32/chromedriver.exe");
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

    public WebDriver getInstance() {

        if (StringUtils.isEmpty(System.getProperty(CHROME_DRIVER_PROPERTY))) {
            String chromeBinaryPath = chromeDriverPathForOS();
            System.setProperty(CHROME_DRIVER_PROPERTY, new File(chromeBinaryPath).getPath());
        }

        return new ChromeDriver();
    }

    private String chromeDriverPathForOS() {
        String operatingSystem = System.getProperty("os.name").toUpperCase();

        if(operatingSystem.contains("WINDOWS")) {
            return CHROMEDRIVER_PATH_WINDOWS;
        }else if(operatingSystem.contains("LINUX")) {
            return CHROMEDRIVER_PATH_LINUX;
        }

        return "";
    }
}
