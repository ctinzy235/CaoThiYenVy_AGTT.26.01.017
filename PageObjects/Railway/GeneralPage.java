package Railway;

import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import EnumRailway.Tab;


public class GeneralPage {

	private String tabXpath = "//div[@id='menu']//span[text()='%s']";
    private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");
    
    protected By getTabLocator(Tab tab) {
        String xpath = String.format(tabXpath, tab.getDisplayName());
        return By.xpath(xpath);
    }
        
    public String getWelcomeMessage() {
        return Utilities.scrollToAndGetText(lblWelcomeMessage);
    }
    
    public boolean isTabLogoutDisplayed() {
        try {
            return Constant.WEBDRIVER.findElement(getTabLocator(Tab.LOGOUT)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    
    public <T> T goToPage(Tab tab, Class<T> pageClass) {
        Utilities.scrollToAndClick(getTabLocator(tab));
        try {
            return pageClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Could not create page: " + pageClass.getName());
        }
    }
    
    public int getColumnIndex(String columnName) {
	    String xpath = String.format("//th[text()='%s']/preceding-sibling::th", columnName);
	    return Constant.WEBDRIVER.findElements(By.xpath(xpath)).size() + 1;
	}

    
}
