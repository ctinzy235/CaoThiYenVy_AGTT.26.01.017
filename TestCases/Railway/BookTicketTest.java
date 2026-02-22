package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;
import DataObjects.User;
import EnumRailway.SeatType;
import EnumRailway.Station;
import EnumRailway.Tab;
import EnumRailway.TicketAction;
import EnumRailway.TicketField;
import DataObjects.BookTicket;
import DataObjects.SeatPrice;
import GuerrillaMail.GeneralPage;
import Common.Random;
import Common.DateUtils;


public class BookTicketTest extends TestBase{

	@Test
	public void TC12() {

		String userName = Random.getRandomString(8);
	    String fullMail = GeneralPage.creatMail(userName);
	    String departDate = "";
	    String amount = "1";
	    User user = new User(fullMail, Constant.PASSWORD,Constant.PASSWORD,  Constant.PID);

	    System.out.println("Pre-condition: an actived account is existing");
	    HomePage homePage = new HomePage();
	    homePage.open();
	    
	    RegisterPage registerPage = new RegisterPage();
	    registerPage = homePage.goToPage(Tab.REGISTER,RegisterPage.class);
	    registerPage.register(user);
	    GeneralPage.confirmAccountViaMail(userName);

		String expectedMsg = ("Ticket booked successfully!");

		BookTicket bookTicket = new BookTicket(departDate,Station.NHA_TRANG,Station.HUE,SeatType.SBC,amount);
		
		System.out.println("TC12 - User can book 1 ticket at a time");
		
		System.out.println("1. Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("2. Login with a valid account");
		LoginPage loginPage = new LoginPage();
		loginPage = homePage.goToPage(Tab.LOGIN, LoginPage.class);
		homePage = loginPage.login(user, HomePage.class);
		
		System.out.println("3. Click on \"Book ticket\" tab");
		BookTicketPage bookTicketPage = new BookTicketPage();
		bookTicketPage = homePage.goToPage(Tab.BOOKTICKET, BookTicketPage.class);

		System.out.println("4. Select the next 2 days from \"Depart date\"");
		System.out.println("5. Select Depart from \"Nha Trang\" and Arrive at \"Huế\"");
		System.out.println("6. Select \"Soft bed with air conditioner\" for \"Seat type\"");
		System.out.println("7. Select \"1\" for \"Ticket amount\"");
		System.out.println("8. Click on \"Book ticket\" button");
		bookTicketPage = bookTicketPage.bookTicket(bookTicket,2);

		String actualMsg = bookTicketPage.getBookTicketMsg();

		System.out.println("VP: Message \"Ticket booked successfully!\" displays.");
		Assert.assertEquals(expectedMsg, actualMsg, "The message does not match the expected message");
		
		System.out.println("VP:  Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		
		bookTicketPage.verifyTicketDetails(bookTicket);
		
	}
	
	@Test
	public void TC13() {

		String userName = Random.getRandomString(8);
	    String fullMail = GeneralPage.creatMail(userName);
	    String departDate = "";
	    String amount = "5";
	    String expectedMsg = ("Ticket booked successfully!");
	    User user = new User(fullMail, Constant.PASSWORD,Constant.PASSWORD,  Constant.PID);
	    BookTicket bookTicket = new BookTicket(departDate,Station.NHA_TRANG,Station.SAI_GON,SeatType.SBC,amount);

	    System.out.println("Pre-condition: an actived account is existing");
	    HomePage homePage = new HomePage();
	    homePage.open();
	    RegisterPage registerPage = new RegisterPage();
	    registerPage = homePage.goToPage(Tab.REGISTER, RegisterPage.class);
	    registerPage = registerPage.register(user);
	    GeneralPage.confirmAccountViaMail(userName);
		
	    System.out.println("TC13 - User can book many tickets at a time");
		System.out.println("1. Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("2. Login with a valid account");
		LoginPage loginPage = new LoginPage();
		loginPage = homePage.goToPage(Tab.LOGIN, LoginPage.class);
		homePage = loginPage.login(user, HomePage.class);
		
		System.out.println("3. Click on \"Book ticket\" tab");
		BookTicketPage bookTicketPage = new BookTicketPage();
		bookTicketPage = homePage.goToPage(Tab.BOOKTICKET, BookTicketPage.class);

		System.out.println("4. Select the next 25 days from \"Depart date\"");
		System.out.println("5. Select Depart from \"Nha Trang\" and Arrive at \"Huế\"");
		System.out.println("6. Select \"Soft bed with air conditioner\" for \"Seat type\"");
		System.out.println("7. Select \"5\" for \"Ticket amount\"");
		System.out.println("8. Click on \"Book ticket\" button");
		bookTicketPage = bookTicketPage.bookTicket(bookTicket,25);

		String actualMsg = bookTicketPage.getBookTicketMsg();

		System.out.println("VP: Message \"Ticket booked successfully!\" displays.");
		Assert.assertEquals(expectedMsg, actualMsg, "The message does not match the expected message");
		
		System.out.println("VP: Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		
		bookTicketPage.verifyTicketDetails(bookTicket);

	}
	
	@Test
	public void TC14() {
		
		String expectedHeaderMsg = "Ticket Price";
		String expectedTableNameMsg = "Ticket price from Đà Nẵng to Sài Gòn";
		
		SeatPrice hs = new SeatPrice(SeatType.HS, "310000");
	    SeatPrice ss = new SeatPrice(SeatType.SS, "335000");
	    SeatPrice ssc = new SeatPrice(SeatType.SSC, "360000");
	    SeatPrice hb = new SeatPrice(SeatType.HB, "410000");
	    SeatPrice sb = new SeatPrice(SeatType.SB, "460000");
	    SeatPrice sbc = new SeatPrice(SeatType.SBC, "510000");
		
		String userName = Random.getRandomString(8);
	    String fullMail = GeneralPage.creatMail(userName);
	    User user = new User(fullMail, Constant.PASSWORD,Constant.PASSWORD,  Constant.PID);
 
	    HomePage homePage = new HomePage();
	    homePage.open();
	    
	    RegisterPage registerPage = new RegisterPage();
	    registerPage = homePage.goToPage(Tab.REGISTER, RegisterPage.class);
	    registerPage = registerPage.register(user);
	    GeneralPage.confirmAccountViaMail(userName);
	    
	    System.out.println("TC14 - User can check price of ticket from Timetable");
		System.out.println("Pre-condition: an actived account is existing");
		System.out.println("1. Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("2. Login with a valid account");
		LoginPage loginPage = new LoginPage();
		loginPage = homePage.goToPage(Tab.LOGIN, LoginPage.class);
		homePage =loginPage.login(user,HomePage.class);
		
		System.out.println("3. Click on \"Timetable\" tab");
		TimeTablePage timeTablePage = new TimeTablePage();
		timeTablePage = homePage.goToPage(Tab.TIMETABLE, TimeTablePage.class);
		
		System.out.println("4. Click on \"check price\" link of the route from \"Đà Nẵng\" to \"Sài Gòn\"");
		TicketPricePage ticketPricePage  = new TicketPricePage();
		ticketPricePage = timeTablePage.clickLink(Station.DA_NANG, Station.SAI_GON, TicketAction.CHECK_PRICE, TicketPricePage.class);

		System.out.println("VP: \"Ticket Price\" page is loaded.");
		String actualHeaderMsg = ticketPricePage.getHeaderMsg();
		Assert.assertEquals(expectedHeaderMsg, actualHeaderMsg, "");
		
		System.out.println("VP: Ticket table shows \"Ticket price from Đà Nẵng to Sài Gòn\".");
		String actualexpectedTableNameMsg = ticketPricePage.getTableNameMsg();
		Assert.assertEquals(expectedTableNameMsg, actualexpectedTableNameMsg, "");

		
		System.out.println("VP: Price for each seat displays correctly\r\n"
				+ "HS = 310000, SS = 335000, SSC = 360000, HB = 410000, SB = 460000, SBC = 510000");
		
		ticketPricePage.verifySeatPrice(hs);
	    ticketPricePage.verifySeatPrice(ss);
	    ticketPricePage.verifySeatPrice(ssc);
	    ticketPricePage.verifySeatPrice(hb);
	    ticketPricePage.verifySeatPrice(sb);
	    ticketPricePage.verifySeatPrice(sbc);
	    
	}
	
	@Test
	public void TC15() {
		
		String tomorrow = DateUtils.getFutureDate(1, "M/d/yyyy");
		String userName = Random.getRandomString(8);
	    String fullMail = GeneralPage.creatMail(userName);
	    String expectedMsg = ("Ticket booked successfully!");
	    User user = new User(fullMail, Constant.PASSWORD,Constant.PASSWORD,  Constant.PID);
	    BookTicket bookTicket = new BookTicket(tomorrow, Station.QUANG_NGAI, Station.HUE, SeatType.SBC, "5");

	    System.out.println("Pre-condition: an actived account is existing");
	    HomePage homePage = new HomePage();
	    homePage.open();
	    
	    RegisterPage registerPage = new RegisterPage();
	    registerPage = homePage.goToPage(Tab.REGISTER, RegisterPage.class);
	    registerPage = registerPage.register(user);
	    GeneralPage.confirmAccountViaMail(userName);
		

	    System.out.println("TC15 - User can book ticket from Timetable");
		
		System.out.println("1. Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("2. Login with a valid account");
		LoginPage loginPage = new LoginPage();
		loginPage = homePage.goToPage(Tab.LOGIN, LoginPage.class);
		homePage = loginPage.login(user, HomePage.class);
		
		System.out.println("3. Click on \"Timetable\" tab");
		TimeTablePage timeTablePage = new TimeTablePage();
		timeTablePage = homePage.goToPage(Tab.TIMETABLE, TimeTablePage.class);
		
		System.out.println("4. Click on book ticket of route \"Quảng Ngãi\" to \"Huế\"");
		BookTicketPage bookTicketPage = new BookTicketPage();
		bookTicketPage = timeTablePage.clickLink(Station.QUANG_NGAI, Station.HUE, TicketAction.BOOK_TICKET, BookTicketPage.class);
		
		System.out.println("VP: Book ticket form is shown with the corrected \"depart from\" and \"Arrive at\"");
		String actualDepartFrom = bookTicketPage.getSelectedOption(TicketField.DEPARTFROM);
		String actualArriveAt = bookTicketPage.getSelectedOption(TicketField.ARRIVE);

		Assert.assertEquals(actualDepartFrom, "Quảng Ngãi", "The 'Depart Station' does not match the expected selection!");
		Assert.assertEquals(actualArriveAt, "Huế", "The 'Arrive Station' does not match the expected selection!");
		
		System.out.println("5. Select Depart date = tomorrow");
		System.out.println("6. Select Ticket amount = 5");
		System.out.println("7. Click on \"Book ticket\" button");
		
		bookTicketPage = bookTicketPage.bookTicket(bookTicket);
		
		System.out.println("VP: Message \"Ticket booked successfully!\" displays.");
		String actualMsg = bookTicketPage.getBookTicketMsg();
		Assert.assertEquals(expectedMsg, actualMsg, "The message does not match the expected message");
		
		System.out.println("VP: Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");

		bookTicketPage.verifyTicketDetails(bookTicket);
	}
	
	
}
