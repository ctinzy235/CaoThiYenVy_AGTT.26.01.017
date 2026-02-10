package Railway;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import Common.TicketField;
import Common.Utilities;
import Common.DateUtils;

import Constant.Constant;
import DataObjects.BookTicket;
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
	    WebElement element = Constant.WEBDRIVER.findElement(getTicketLocator(field));
	    
	    Utilities.scrollToElement(element);
	    
	    Select select = new Select(element);
	    return select.getFirstSelectedOption().getText().trim();
	}
	
	private void fillTicketFormAndSubmit(BookTicket bookTicket) {
	    WebElement arriveStationDropdown = Constant.WEBDRIVER.findElement(getTicketLocator(TicketField.ARRIVE));
	    
	    selectOptionByText(TicketField.DEPARTDATE, bookTicket.getDepartDate());
	    selectOptionByText(TicketField.DEPARTFROM, bookTicket.getDepartFrom());
	    
	    Utilities.waitUntilStale(arriveStationDropdown);
	    
	    selectOptionByText(TicketField.ARRIVE, bookTicket.getArrive());
	    selectOptionByText(TicketField.SEATTYPE, bookTicket.getSeatType());
	    selectOptionByText(TicketField.TICKETAMOUNT, bookTicket.getTicketAmount());
	    
	    Utilities.scrollToAndClick(btnBookTicket); 
	}
	
	public void bookTicket(BookTicket bookTicket, int addDaysFromWeb) {
	    String currentWebDate = getFirstAvailableDepartDate();
	    String departDate = DateUtils.addDaysToDate(currentWebDate, addDaysFromWeb, "M/d/yyyy");
	    bookTicket.setDepartDate(departDate);
	    fillTicketFormAndSubmit(bookTicket);
	    
	}
	
	public void bookTicket(BookTicket bookTicket) {
		fillTicketFormAndSubmit(bookTicket);
	}
	
	
	
}
