package Railway;
import org.openqa.selenium.By;
import Common.WaitUtils;
import org.openqa.selenium.WebElement;
import Constant.Constant;
import Common.Utilities;
import java.util.List;



public class MyTicketPage extends GeneralPage{
	
	private String btnCancelXpath = "//tr[td[1][text()='%s']]//input";
	private String lastCancelledId;

	public MyTicketPage clickCancelButton(String rowNumber) {
	
		By cancelBtnLocator = By.xpath(String.format(btnCancelXpath, rowNumber));
		WebElement cancelBtn = Constant.WEBDRIVER.findElement(WaitUtils.waitForClickable(cancelBtnLocator));
		String onclickValue = cancelBtn.getAttribute("onclick");
	    this.lastCancelledId = onclickValue.replaceAll("[^0-9]", "");
	    Utilities.scrollToAndClick(cancelBtn);
	    Utilities.acceptAlert();
	    return this;
	}
	
	public String getLastCancelledId() {
	    return this.lastCancelledId;
	}
	
	
	public boolean isTicketCancelled() {
	    
		String xpath = String.format("//input[contains(@onclick, '%s')]", this.lastCancelledId);
	    List<WebElement> elements = Constant.WEBDRIVER.findElements(By.xpath(xpath));
	    
	    return elements.isEmpty(); 
	}
}
