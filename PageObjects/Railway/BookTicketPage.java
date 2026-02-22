package Railway;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Common.Utilities;
import Common.DateUtils;
import Common.WaitUtils;

import Constant.Constant;
import DataObjects.BookTicket;
import EnumRailway.TicketColumn;
import EnumRailway.TicketField;

import org.openqa.selenium.WebElement;



public class BookTicketPage extends GeneralPage{
	
	private String ticketXpath = "//select[@name='%s']";
	private final By btnBookTicket = By.xpath("//input[@value='Book ticket']");
	private final By lblBookTicketMsg = By.xpath("//div[@id='content']//h1");
	
	protected By getTicketLocator(TicketField ticket) {
        String xpath = String.format(ticketXpath, ticket.getId());
        return By.xpath(xpath);
    }
	
	public String getBookTicketMsg() {
        return Utilities.scrollToAndGetText(lblBookTicketMsg);
    }
	
	public void selectOptionByText(TicketField ticket, String optionText) {
	    
	    Select select = new Select(Constant.WEBDRIVER.findElement(getTicketLocator(ticket)));
	    
	    select.selectByVisibleText(optionText);
	}
	
	public String getFirstAvailableDepartDate() {
	    WebElement dropdown = Constant.WEBDRIVER.findElement(getTicketLocator(TicketField.DEPARTDATE));
	    Select select = new Select(dropdown);
	    return select.getOptions().get(0).getText();
	}
	
	public String getSelectedOption(TicketField field) {
		
		By locator = getTicketLocator(field);
		
		WaitUtils.waitForVisible(locator);
		
	    WebElement element = Constant.WEBDRIVER.findElement(locator);
	    
	    Utilities.scrollToElement(element);
	    
	    Select select = new Select(element);
	    return select.getFirstSelectedOption().getText().trim();
	}
	
	private void fillTicketFormAndSubmit(BookTicket bookTicket) {
	    WebElement arriveStationDropdown = Constant.WEBDRIVER.findElement(getTicketLocator(TicketField.ARRIVE));
	    
	    selectOptionByText(TicketField.DEPARTDATE, bookTicket.getDepartDate());
	    selectOptionByText(TicketField.DEPARTFROM, bookTicket.getDepartFrom().getValue());
	    
	    WaitUtils.waitUntilStale(arriveStationDropdown);
	    
	    selectOptionByText(TicketField.ARRIVE, bookTicket.getArrive().getValue());
	    selectOptionByText(TicketField.SEATTYPE, bookTicket.getSeatType().getValue());
	    selectOptionByText(TicketField.TICKETAMOUNT, bookTicket.getTicketAmount());
	    
	    Utilities.scrollToAndClick(btnBookTicket); 
	}
	
	public BookTicketPage bookTicket(BookTicket bookTicket, int addDaysFromWeb) {
	    String currentWebDate = getFirstAvailableDepartDate();
	    String departDate = DateUtils.addDaysToDate(currentWebDate, addDaysFromWeb, "M/d/yyyy");
	    bookTicket.setDepartDate(departDate);
	    fillTicketFormAndSubmit(bookTicket);
	    return this;
	}
	
	public BookTicketPage bookTicket(BookTicket bookTicket) {
		fillTicketFormAndSubmit(bookTicket);
		return this;
	}
	
	public String getCellValueByColumnName(String colName) {
	    int colIdx = getColumnIndex(colName);
	    
	    String xpath = String.format("//table[@class='MyTable WideTable']//tr[@class='OddRow'][1]/td[%d]", colIdx);
	    
	    return Constant.WEBDRIVER.findElement(By.xpath(xpath)).getText();
	}
	
	public void verifyTicketDetails(BookTicket expectedTicket) {
	    Assert.assertEquals(getCellValueByColumnName(TicketColumn.DEPART_DATE.getValue()), expectedTicket.getDepartDate(), "Depart Date does not match!");
	    Assert.assertEquals(getCellValueByColumnName(TicketColumn.DEPART_STATION.getValue()), expectedTicket.getDepartFrom().getValue(), "Depart Station does not match!");
	    Assert.assertEquals(getCellValueByColumnName(TicketColumn.ARRIVE_STATION.getValue()), expectedTicket.getArrive().getValue(), "Arrive Station does not match!");
	    Assert.assertEquals(getCellValueByColumnName(TicketColumn.SEAT_TYPE.getValue()), expectedTicket.getSeatType().getValue(), "Seat Type does not match!");
	    Assert.assertEquals(getCellValueByColumnName(TicketColumn.AMOUNT.getValue()), expectedTicket.getTicketAmount(), "Amount does not match!");
	}
	
}
