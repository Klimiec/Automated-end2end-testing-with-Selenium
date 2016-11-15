package common.driverprovider.wait;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Wait {

    /**
     * Checks if the element is present in browser DOM
     */

    private static final int DEFAULT_TIMEOUT = 15;

    private WebDriverWait wait;
    private WebDriver driver;

    public Wait(WebDriver driver) {
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        this.driver = driver;
    }


    /**
     * Checks if the element is present in browser DOM
     */
    public void forElementPresent(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Checks if the element is visible in the browser
     */
    public void forElementVisible(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Checks if the element is clickable on the browser
     */
    public void forElementClickable(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));

    }

    public boolean isAlertPopUpPresent() {
        try {
            new WebDriverWait(driver, 1).until(ExpectedConditions.alertIsPresent());
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }

}
