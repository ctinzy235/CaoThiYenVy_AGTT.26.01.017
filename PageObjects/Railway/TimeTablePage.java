package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Constant.Constant;
import Common.Utilities;
import Common.TicketAction;
import Common.TicketColumn;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class TimeTablePage extends GeneralPage {

	public int getColumnIndex(String columnName) {
	    String xpath = String.format("//th[text()='%s']/preceding-sibling::th", columnName);
	    return Constant.WEBDRIVER.findElements(By.xpath(xpath)).size() + 1;
	}
	
	public void clickLink(String depart, String arrive, TicketAction action) {
	    int departIdx = getColumnIndex(TicketColumn.DEPART_STATION.getValue());
	    int arriveIdx = getColumnIndex(TicketColumn.ARRIVE_STATION.getValue());
	    
	    String xpath = String.format("//tr[td[%d][text()='%s'] and td[%d][text()='%s']]//a[text()='%s']", departIdx, depart, arriveIdx, arrive, action.getValue());
	    
	    WebElement btnLink = Constant.WEBDRIVER.findElement(By.xpath(xpath));
	    Utilities.scrollToAndClick(btnLink);
	}
	
	
	public Map<String, String> getActualTicketData(List<String> columnsToGet) {
	    Map<String, String> actualData = new HashMap<>();

	    for (String colName : columnsToGet) {
	        int colIdx = getColumnIndex(colName);
	        
	        String xpath = String.format("//table[@class='MyTable WideTable']//tr[@class='OddRow'][1]/td[%d]", colIdx);
	        String text = Utilities.scrollToAndGetText(By.xpath(xpath));
	        
	        actualData.put(colName, text);
	    }
	    return actualData;
	}
}
