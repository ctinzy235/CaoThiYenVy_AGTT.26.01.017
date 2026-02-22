package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Constant.Constant;
import EnumRailway.Station;
import EnumRailway.TicketAction;
import EnumRailway.TicketColumn;
import Common.Utilities;


public class TimeTablePage extends GeneralPage {
	
	public <T> T clickLink(Station depart, Station arrive, TicketAction action, Class<T> pageClass) {
	    int departIdx = getColumnIndex(TicketColumn.DEPART_STATION.getValue());
	    int arriveIdx = getColumnIndex(TicketColumn.ARRIVE_STATION.getValue());
	    
	    String xpath = String.format("//tr[td[%d][text()='%s'] and td[%d][text()='%s']]//a[text()='%s']", 
	                                 departIdx, depart.getValue(), arriveIdx, arrive.getValue(), action.getValue());
	    
	    WebElement btnLink = Constant.WEBDRIVER.findElement(By.xpath(xpath));
	    Utilities.scrollToAndClick(btnLink);
	    
	    try {
	        return pageClass.getDeclaredConstructor().newInstance();
	    } catch (Exception e) {
	        throw new RuntimeException("Could not create page: " + pageClass.getName());
	    }
	}
	
}
