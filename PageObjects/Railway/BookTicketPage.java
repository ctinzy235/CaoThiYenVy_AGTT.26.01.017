package Railway;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import Common.TicketField;
import Common.TicketTable;

import Common.Utilities;
import Common.WaitUtils;

import Constant.Constant;
import DataObjects.BookTicket;



public class BookTicketPage extends GeneralPage{
	
	private String ticketXpath = "//select[@name='%s']";
	private String tableXpath = "//table[@class='MyTable WideTable']//tr[@class='OddRow'][1]/td[%s]";
	private final By btnBookTicket = By.xpath("//input[@value='Book ticket']");
	private final By lblBookTicketMsg = By.xpath("//div[@id='content']//h1");
	private final By lblSomee = By.xpath("//span[@id='ArriveStation']//center//a");
	
	protected By getTicketLocator(TicketField ticket) {
        String xpath = String.format(ticketXpath, ticket.getId());
        return By.xpath(xpath);
    }
	
	protected By getTableLocator(TicketTable ticketTable) {
		String xpath = String.format(tableXpath,ticketTable.getId());
		return By.xpath(xpath);
	}
	
	public String getTextTable(TicketTable ticketTable) {
		return Utilities.scrollToAndGetText(getTableLocator(ticketTable));
	}
	public void waitForSomeeFooterDisplayed() {
		WaitUtils.waitForVisible(lblSomee);
	}
	
	public String getBookTicketMsg() {
        return Utilities.scrollToAndGetText(lblBookTicketMsg);
    }
	
	public void selectOptionByText(TicketField ticket, String optionText) {
	    
	    Select select = new Select(Constant.WEBDRIVER.findElement(getTicketLocator(ticket)));
	    
	    select.selectByVisibleText(optionText);
	}
	
	public void bookTicket(BookTicket bookTicket) {
		selectOptionByText(TicketField.DEPARTDATE, bookTicket.getDepartDate());
		selectOptionByText(TicketField.DEPARTFROM, bookTicket.getDepartFrom());
		waitForSomeeFooterDisplayed();
		selectOptionByText(TicketField.ARRIVE,bookTicket.getArrive());
		selectOptionByText(TicketField.SEATTYPE, bookTicket.getSeatType());
		selectOptionByText(TicketField.TICKETAMOUNT, bookTicket.getTicketAmount());
		Utilities.srollToAndClick(btnBookTicket);
	}
	
	
	
}
