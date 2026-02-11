package Common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import Constant.Constant;
import org.openqa.selenium.By;
import java.util.Set;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class Utilities {

	
	public static void clickByJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].click();", element);
    }
	
	public static void scrollToElement(By locator) {
	    scrollToElement(Constant.WEBDRIVER.findElement(locator));
	}

	public static void scrollToElement(WebElement element) {
	    JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
	    js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
	    try { Thread.sleep(300); } catch (InterruptedException ignored) {}
	}
	
	public static void scrollToAndClick(By locator) {

		WaitUtils.waitForClickable(locator);
	    
	    WebElement element = Constant.WEBDRIVER.findElement(locator);
	    
	    scrollToElement(element);
	    
	    try {
	        element.click();
	    } catch (Exception e) {
	    	clickByJS(element);
	    }
	}
	
	public static void scrollToAndClick(WebElement element) {
	    WaitUtils.waitForVisible(element); 
	    
	    scrollToElement(element);
	    
	    try {
	        WaitUtils.waitForClickable(element);
	        element.click();
	    } catch (Exception e) {
	        clickByJS(element);
	    }
	}
	
	public static void srollToAndSendKeys(By locator, String text) {
	    WaitUtils.waitForVisible(locator);
	    
	    WebElement element = Constant.WEBDRIVER.findElement(locator);
	    
	    scrollToElement(element);
	    
	    element.clear();
	    
	    element.sendKeys(text);
	}
	
	public static String scrollToAndGetText(By locator) {
	    WaitUtils.waitForVisible(locator);
	    
	    WebElement element = Constant.WEBDRIVER.findElement(locator);
	    
	    scrollToElement(element);
	    
	    return element.getText();
	   
	}
	

	public static void closeCurrentAndSwitchToLatestTab() {
	    String currentWindow = Constant.WEBDRIVER.getWindowHandle();
	    
	    Set<String> allWindows = Constant.WEBDRIVER.getWindowHandles();
	    
	    Constant.WEBDRIVER.close();
	    
	    for (String window : allWindows) {
	        if (!window.equals(currentWindow)) {
	            Constant.WEBDRIVER.switchTo().window(window);
	        }
	    }
	    
	    System.out.println("Closed old tab and switched to: " + Constant.WEBDRIVER.getTitle());
	}
	
	
	
	public static void acceptAlert() {
	    try {
	    	WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT));
	    	wait.until(ExpectedConditions.alertIsPresent());
	        Constant.WEBDRIVER.switchTo().alert().accept();
	    } catch (Exception e) {
	        System.out.println("No alert present to accept.");
	    }
	}
	
}
