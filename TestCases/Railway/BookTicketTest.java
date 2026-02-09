package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Tab;
import Constant.Constant;
import DataObjects.User;
import DataObjects.BookTicket;
import Common.TicketTable;

public class BookTicketTest extends TestBase{

	@Test
	public void TC12() {
		User User = new User(Constant.USERNAME, Constant.PASSWORD);
		String departDate ="2/14/2026";
		String departFrom = "Nha Trang";
		String arrive = "Huế";
		String seatType = "Soft bed with air conditioner";
		String ticketAmount = "1";
		BookTicket BookTicket = new BookTicket(departDate,departFrom,arrive,seatType,ticketAmount);
		
		System.out.println("TC12 - User can book 1 ticket at a time");
		System.out.println("Pre-condition: an actived account is existing");
		
		System.out.println("1. Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("2. Login with a valid account");
		homePage.gotoPage(Tab.LOGIN);
		loginPage.login(User);
		
		System.out.println("3. Click on \"Book ticket\" tab");
		homePage.gotoPage(Tab.BOOKTICKET);

		System.out.println("4. Select the next 2 days from \"Depart date\"");
		System.out.println("5. Select Depart from \"Nha Trang\" and Arrive at \"Huế\"");
		System.out.println("6. Select \"Soft bed with air conditioner\" for \"Seat type\"");
		System.out.println("7. Select \"1\" for \"Ticket amount\"");
		System.out.println("8. Click on \"Book ticket\" button");
		bookTicketPage.bookTicket(BookTicket);

		String expectedMsg = ("Ticket booked successfully!");
		String actualMsg = bookTicketPage.getBookTicketMsg();

		System.out.println("VP: Message \"Ticket booked successfully!\" displays.");
		Assert.assertEquals(expectedMsg, actualMsg, "The message does not match the expected message");
		
		System.out.println("VP:  Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.DEPARTDATE),departDate,"Depart Date invalid");
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.DEPARTFROM),departFrom,"Depart From invalid");
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.ARRIVE),arrive,"Arrive invalid");
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.SEATTYPE),seatType,"Seat Type invalid");
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.TICKETAMOUNT),ticketAmount,"Ticket Amount invalid");

		
	}
}
