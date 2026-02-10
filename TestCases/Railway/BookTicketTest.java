package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Tab;
import Constant.Constant;
import DataObjects.User;
import DataObjects.BookTicket;
import Common.TicketTable;
import Constant.MailType;
import GuerrillaMail.GeneralPage;
import Common.Random;


public class BookTicketTest extends TestBase{

	@Test
	public void TC12() {

		String userName = Random.getRandomString(8);
	    String fullMail = GeneralPage.creatMail(userName);
	    User User = new User(fullMail, Constant.PASSWORD,Constant.PASSWORD,  Constant.PID);

	    homePage.open();
	    homePage.gotoPage(Tab.REGISTER);
	    registerPage.register(User);
	    GeneralPage.confirmMail(userName, MailType.CONFIRM);
	    
		String departDate = "";
		String departFrom = "Nha Trang";
		String arriveAt = "Huế";
		String seatType = "Soft bed with air conditioner";
		String ticketAmount = "1";
		String expectedMsg = ("Ticket booked successfully!");

		BookTicket BookTicket = new BookTicket(departDate,departFrom,arriveAt,seatType,ticketAmount);
		
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
		bookTicketPage.bookTicket(BookTicket,2);

		String actualMsg = bookTicketPage.getBookTicketMsg();

		System.out.println("VP: Message \"Ticket booked successfully!\" displays.");
		Assert.assertEquals(expectedMsg, actualMsg, "The message does not match the expected message");
		
		System.out.println("VP:  Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.DEPARTDATE),BookTicket.getDepartDate(),"Depart Date invalid");
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.DEPARTFROM),BookTicket.getDepartFrom(),"Depart From invalid");
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.ARRIVE),BookTicket.getArrive(),"Arrive invalid");
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.SEATTYPE),BookTicket.getSeatType(),"Seat Type invalid");
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.TICKETAMOUNT),BookTicket.getTicketAmount(),"Ticket Amount invalid");
		
	}
	
	@Test
	public void TC13() {

		String userName = Random.getRandomString(8);
	    String fullMail = GeneralPage.creatMail(userName);
	    User User = new User(fullMail, Constant.PASSWORD,Constant.PASSWORD,  Constant.PID);

	    homePage.open();
	    homePage.gotoPage(Tab.REGISTER);
	    registerPage.register(User);
	    GeneralPage.confirmMail(userName, MailType.CONFIRM);
	    
		String departDate = "";
		String departFrom = "Nha Trang";
		String arriveAt = "Sài Gòn";
		String seatType = "Soft bed with air conditioner";
		String ticketAmount = "5";
		String expectedMsg = ("Ticket booked successfully!");

		BookTicket BookTicket = new BookTicket(departDate,departFrom,arriveAt,seatType,ticketAmount);
		
		System.out.println("TC12 - User can book 1 ticket at a time");
		System.out.println("Pre-condition: an actived account is existing");
		
		System.out.println("1. Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("2. Login with a valid account");
		homePage.gotoPage(Tab.LOGIN);
		loginPage.login(User);
		
		System.out.println("3. Click on \"Book ticket\" tab");
		homePage.gotoPage(Tab.BOOKTICKET);

		System.out.println("4. Select the next 25 days from \"Depart date\"");
		System.out.println("5. Select Depart from \"Nha Trang\" and Arrive at \"Huế\"");
		System.out.println("6. Select \"Soft bed with air conditioner\" for \"Seat type\"");
		System.out.println("7. Select \"5\" for \"Ticket amount\"");
		System.out.println("8. Click on \"Book ticket\" button");
		bookTicketPage.bookTicket(BookTicket,25);

		String actualMsg = bookTicketPage.getBookTicketMsg();

		System.out.println("VP: Message \"Ticket booked successfully!\" displays.");
		Assert.assertEquals(expectedMsg, actualMsg, "The message does not match the expected message");
		
		System.out.println("VP:  Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.DEPARTDATE),BookTicket.getDepartDate(),"Depart Date invalid");
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.DEPARTFROM),BookTicket.getDepartFrom(),"Depart From invalid");
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.ARRIVE),BookTicket.getArrive(),"Arrive invalid");
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.SEATTYPE),BookTicket.getSeatType(),"Seat Type invalid");
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.TICKETAMOUNT),BookTicket.getTicketAmount(),"Ticket Amount invalid");
		
	}
	
	@Test
	public void TC14() {
		
	}
}
