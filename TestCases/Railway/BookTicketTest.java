package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Tab;
import Constant.Constant;
import DataObjects.User;
import DataObjects.BookTicket;
import Common.TicketTable;
//import Common.Utilities;
import Constant.MailType;
import GuerrillaMail.GeneralPage;
import Common.Random;
import Common.TicketAction;
import java.util.Map;
import java.util.HashMap;
import Common.TicketField;
import Common.DateUtils;


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
	    
		String expectedMsg = ("Ticket booked successfully!");

		BookTicket BookTicket = new BookTicket("","Nha Trang","Huế","Soft bed with air conditioner","1");
		
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
	    
		String expectedMsg = ("Ticket booked successfully!");

		BookTicket BookTicket = new BookTicket("","Nha Trang","Sài Gòn","Soft bed with air conditioner","5");
		
		System.out.println("TC13 - User can book many tickets at a time");
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
		
		System.out.println("VP: Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.DEPARTDATE),BookTicket.getDepartDate(),"Depart Date invalid");
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.DEPARTFROM),BookTicket.getDepartFrom(),"Depart From invalid");
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.ARRIVE),BookTicket.getArrive(),"Arrive invalid");
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.SEATTYPE),BookTicket.getSeatType(),"Seat Type invalid");
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.TICKETAMOUNT),BookTicket.getTicketAmount(),"Ticket Amount invalid");
		
	}
	
	@Test
	public void TC14() {
		
		String expectedHeaderMsg = "Ticket Price";
		String expectedTableNameMsg = "Ticket price from Đà Nẵng to Sài Gòn";
		Map<String, String> expectedPrices = new HashMap<String, String>() {{
		    put("HS", "310000");
		    put("SS", "335000");
		    put("SSC", "360000");
		    put("HB", "410000");
		    put("SB", "460000");
		    put("SBC", "510000");
		}};
		
		String userName = Random.getRandomString(8);
	    String fullMail = GeneralPage.creatMail(userName);
	    User User = new User(fullMail, Constant.PASSWORD,Constant.PASSWORD,  Constant.PID);

	    homePage.open();
	    homePage.gotoPage(Tab.REGISTER);
	    registerPage.register(User);
	    GeneralPage.confirmMail(userName, MailType.CONFIRM);
	    
	    System.out.println("TC14 - User can check price of ticket from Timetable");
		System.out.println("Pre-condition: an actived account is existing");
		System.out.println("1. Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("2. Login with a valid account");
		homePage.gotoPage(Tab.LOGIN);
		loginPage.login(User);
		
		System.out.println("3. Click on \"Timetable\" tab");
		homePage.gotoPage(Tab.TIMETABLE);
		
		System.out.println("4. Click on \"check price\" link of the route from \"Đà Nẵng\" to \"Sài Gòn\"");
		timeTablePage.clickLink("Đà Nẵng", "Sài Gòn", TicketAction.CHECK_PRICE);

		System.out.println("VP: \"Ticket Price\" page is loaded.");
		String actualHeaderMsg = ticketPricePage.getHeaderMsg();
		Assert.assertEquals(expectedHeaderMsg, actualHeaderMsg, "");
		
		System.out.println("VP: Ticket table shows \"Ticket price from Đà Nẵng to Sài Gòn\".");
		String actualexpectedTableNameMsg = ticketPricePage.getTableNameMsg();
		Assert.assertEquals(expectedTableNameMsg, actualexpectedTableNameMsg, "");

		
		System.out.println("VP: Price for each seat displays correctly\r\n"
				+ "HS = 310000, SS = 335000, SSC = 360000, HB = 410000, SB = 460000, SBC = 510000");
		
		Map<String, String> actualPrices = ticketPricePage.getPriceTableData();
		Assert.assertEquals(actualPrices, expectedPrices, "Ticket prices for seat types do not match the expected values!");
	    
	}
	
	@Test
	public void TC15() {
		
		String tomorrow = DateUtils.getFutureDate(1, "M/d/yyyy");
		String userName = Random.getRandomString(8);
	    String fullMail = GeneralPage.creatMail(userName);
	    String expectedMsg = ("Ticket booked successfully!");
	    User User = new User(fullMail, Constant.PASSWORD,Constant.PASSWORD,  Constant.PID);

	    homePage.open();
	    homePage.gotoPage(Tab.REGISTER);
	    registerPage.register(User);
	    GeneralPage.confirmMail(userName, MailType.CONFIRM);
		BookTicket BookTicket = new BookTicket(tomorrow, "Quảng Ngãi", "Huế", "Soft bed with air conditioner", "5");

	    System.out.println("TC15 - User can book ticket from Timetable");
		System.out.println("Pre-condition: an actived account is existing");
		System.out.println("1. Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("2. Login with a valid account");
		homePage.gotoPage(Tab.LOGIN);
		loginPage.login(User);
		
		System.out.println("3. Click on \"Timetable\" tab");
		homePage.gotoPage(Tab.TIMETABLE);
		
		System.out.println("4. Click on book ticket of route \"Quảng Ngãi\" to \"Huế\"");
		timeTablePage.clickLink("Quảng Ngãi", "Huế", TicketAction.BOOK_TICKET);
		
		System.out.println("VP: Book ticket form is shown with the corrected \"depart from\" and \"Arrive at\"");
		String actualDepartFrom = bookTicketPage.getSelectedOption(TicketField.DEPARTFROM);
		String actualArriveAt = bookTicketPage.getSelectedOption(TicketField.ARRIVE);

		Assert.assertEquals(actualDepartFrom, "Quảng Ngãi", "The 'Depart Station' does not match the expected selection!");
		Assert.assertEquals(actualArriveAt, "Huế", "The 'Arrive Station' does not match the expected selection!");
		
		System.out.println("5. Select Depart date = tomorrow");
		System.out.println("6. Select Ticket amount = 5");
		System.out.println("7. Click on \"Book ticket\" button");
		
		bookTicketPage.bookTicket(BookTicket);
		
		System.out.println("VP: Message \"Ticket booked successfully!\" displays.");
		String actualMsg = bookTicketPage.getBookTicketMsg();
		Assert.assertEquals(expectedMsg, actualMsg, "The message does not match the expected message");
		
		System.out.println("VP: Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.DEPARTDATE),BookTicket.getDepartDate(),"Depart Date invalid");
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.DEPARTFROM),BookTicket.getDepartFrom(),"Depart From invalid");
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.ARRIVE),BookTicket.getArrive(),"Arrive invalid");
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.SEATTYPE),BookTicket.getSeatType(),"Seat Type invalid");
		Assert.assertEquals(bookTicketPage.getTextTable(TicketTable.TICKETAMOUNT),BookTicket.getTicketAmount(),"Ticket Amount invalid");
	}
	
}
