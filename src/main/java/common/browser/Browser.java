package common.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public enum Browser {

    CHROME(ChromeBrowser.class, "CHROME"),
    FIREFOX(FirefoxBrowser.class, "FIREFOX");

    private Class<? extends BrowserAbstract> browserClass;
    private String name;

    Browser(Class browserClass, String name) {
        this.browserClass = browserClass;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Browser lookUp(String browserNam) {
        for (Browser name : Browser.values()) {
            if (name.getName().equalsIgnoreCase(browserNam)) {
                return name;
            }
        }
        return null;
    }

    public WebDriver getInstance() {

        try {
            return browserClass.newInstance().getInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Couldn't create webdriver for a given type of browser", e);
        }
    }
}
