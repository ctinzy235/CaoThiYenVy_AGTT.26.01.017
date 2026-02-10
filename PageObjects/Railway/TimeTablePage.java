package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Constant.Constant;
import Common.Utilities;
import Common.TicketAction;



public class TimeTablePage extends GeneralPage {

	public int getColumnIndex(String columnName) {
	    String xpath = String.format("//th[text()='%s']/preceding-sibling::th", columnName);
	    return Constant.WEBDRIVER.findElements(By.xpath(xpath)).size() + 1;
	}
	
	public void clickLink(String depart, String arrive, TicketAction action) {
	    int departIdx = getColumnIndex("Depart Station");
	    int arriveIdx = getColumnIndex("Arrive Station");
	    
	    String xpath = String.format("//tr[td[%d][text()='%s'] and td[%d][text()='%s']]//a[text()='%s']", departIdx, depart, arriveIdx, arrive, action.getValue());
	    
	    WebElement btnLink = Constant.WEBDRIVER.findElement(By.xpath(xpath));
	    Utilities.scrollToAndClick(btnLink);
	}
	
	
}
