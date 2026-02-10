package Railway;

import org.openqa.selenium.By;
import Common.Utilities;
import Constant.Constant;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.openqa.selenium.WebElement;


public class TicketPricePage extends GeneralPage{

	private final By lblHeader = By.xpath("//div[@id='content']//h1[text()='Ticket Price']");
	private final By lblTableName = By.xpath("//tr[@class='TableSmallHeader']");
	
	
	public String getHeaderMsg() {
	    return Utilities.scrollToAndGetText(lblHeader);
	}
	
	public String getTableNameMsg() {
	    return Utilities.scrollToAndGetText(lblTableName);
	}
	
	public Map<String, String> getPriceTableData() {
	    Map<String, String> priceData = new HashMap<>();
	    
	    List<WebElement> headers = Constant.WEBDRIVER.findElements(By.xpath("//tr[th[contains(text(),'Seat type')]]//td"));
	    List<WebElement> values = Constant.WEBDRIVER.findElements(By.xpath("//tr[th[contains(text(),'Price (VND)')]]//td"));

	    for (int i = 0; i < headers.size(); i++) {
	    	priceData.put(headers.get(i).getText().trim(), values.get(i).getText().trim());
	    }
	    return priceData;
	}
	
}
