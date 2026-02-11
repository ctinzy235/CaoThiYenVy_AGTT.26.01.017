package Common;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class WaitUtils {
	
    public static By waitForClickable(By locator) {
        return waitForClickable(locator, Constant.TIMEOUT);
    }

    public static By waitForClickable(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return locator;
    }

    public static By waitForVisible(By locator) {
        return waitForVisible(locator, Constant.TIMEOUT);
    }
    
    public static WebElement waitForClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static By waitForVisible(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return locator;
    }
    
    public static void waitUntilStale(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT));
			wait.until(ExpectedConditions.stalenessOf(element));
		} catch (Exception e) {

		}
	}

}
