package Railway;

import org.openqa.selenium.By;
import Common.Utilities;
import Constant.Constant;
import DataObjects.SeatPrice;
import org.testng.Assert;


public class TicketPricePage extends GeneralPage{

	private final By lblHeader = By.xpath("//div[@id='content']//h1[text()='Ticket Price']");
	private final By lblTableName = By.xpath("//tr[@class='TableSmallHeader']");
	
	
	public String getHeaderMsg() {
	    return Utilities.scrollToAndGetText(lblHeader);
	}
	
	public String getTableNameMsg() {
	    return Utilities.scrollToAndGetText(lblTableName);
	}
	
	public void verifySeatPrice(SeatPrice expected) {
	    
	    String xpathSeat = String.format("//tr[th[contains(text(),'Seat type')]]/td[text()='%s']", expected.getSeatType());
	    
	    int colIdx = Constant.WEBDRIVER.findElements(By.xpath(xpathSeat + "/preceding-sibling::td")).size() + 1;
	    
	    String xpathPrice = String.format("//tr[th[contains(text(),'Price (VND)')]]/td[%d]", colIdx);
	    String actualPrice = Constant.WEBDRIVER.findElement(By.xpath(xpathPrice)).getText().trim();

	    Assert.assertEquals(actualPrice, expected.getPrice(), "Price for " + expected.getSeatType() + " does not match!");
	}
	
}
