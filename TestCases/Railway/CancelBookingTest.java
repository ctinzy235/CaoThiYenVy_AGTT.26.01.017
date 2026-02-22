package Railway;

import org.testng.annotations.Test;

import Common.Random;
import Constant.Constant;
import DataObjects.BookTicket;
import DataObjects.User;
import EnumRailway.SeatType;
import EnumRailway.Station;
import EnumRailway.Tab;
import GuerrillaMail.GeneralPage;
import org.testng.Assert;

public class CancelBookingTest extends TestBase{

	@Test
	public void TC16() {
		
		String userName = Random.getRandomString(8);
	    String fullMail = GeneralPage.creatMail(userName);
	    String departDate = "";
	    String amount = "1";
	    User user = new User(fullMail, Constant.PASSWORD,Constant.PASSWORD,  Constant.PID);

	    HomePage homePage = new HomePage();
	    homePage.open();
	    
	    RegisterPage registerPage = new RegisterPage();
	    registerPage = homePage.goToPage(Tab.REGISTER, RegisterPage.class);
	    registerPage = registerPage.register(user);
	    GeneralPage.confirmAccountViaMail(userName);
		
		BookTicket bookTicket = new BookTicket(departDate,Station.NHA_TRANG,Station.HUE,SeatType.SSC,amount);
		
		System.out.println("TC16 - User can cancel a ticket");
		System.out.println("Pre-condition: an actived account is existing");
		
		System.out.println("1. Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("2. Login with a valid account");
		LoginPage loginPage = new LoginPage();
		loginPage = homePage.goToPage(Tab.LOGIN, LoginPage.class);
		homePage = loginPage.login(user, HomePage.class);
		
		System.out.println("3. Book a ticket");
		BookTicketPage bookTicketPage = new BookTicketPage();
		bookTicketPage = homePage.goToPage(Tab.BOOKTICKET, BookTicketPage.class);
		bookTicketPage = bookTicketPage.bookTicket(bookTicket,2);

		System.out.println("4. Click on \"My ticket\" tab");
		MyTicketPage myTicketPage = new MyTicketPage();
		myTicketPage = homePage.goToPage(Tab.MYTICKET, MyTicketPage.class);
		
		System.out.println("5. Click on \"Cancel\" button of ticket which user want to cancel.");
		System.out.println("6. Click on \"OK\" button on Confirmation message \"Are you sure?\"");
		myTicketPage = myTicketPage.clickCancelButton("1");
		
		System.out.println("VP: The canceled ticket is disappeared.");
		Assert.assertTrue(myTicketPage.isTicketCancelled(), "Error: The ticket is still displayed in the table after deletion!");
		
	}
}
