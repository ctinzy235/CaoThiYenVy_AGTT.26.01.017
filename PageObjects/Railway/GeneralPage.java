package Railway;

import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;

import Common.Tab;
import Common.Utilities;
import Constant.Constant;


public class GeneralPage {

	private String tabXpath = "//div[@id='menu']//span[text()='%s']";
    private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");
    
    protected By getTabLocator(Tab tab) {
        String xpath = String.format(tabXpath, tab.getDisplayName());
        return By.xpath(xpath);
    }
        
    public String getWelcomeMessage() {
        return Utilities.safeGetText(lblWelcomeMessage);
    }
    
    public boolean isTabLogoutDisplayed() {
        try {
            return Constant.WEBDRIVER.findElement(getTabLocator(Tab.LOGOUT)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void gotoPage(Tab tab) {
        Utilities.safeClick(getTabLocator(tab));
    }
    
//    public void gotoPage(By locator) {
//    	Utilities.safeClick(locator);
//    }
    
}
