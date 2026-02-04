package Common;

import java.util.UUID;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import Constant.Constant;

public class Utilities {

	public static String getRandomPassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
	
	public static String getRandomEmail() {
		return "test" + UUID.randomUUID().toString().substring(0, 4) + "@gmail.com";
	}
	
	public static void clickByJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].click();", element);
    }
	
}
