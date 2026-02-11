package Railway;

import org.testng.annotations.Test;

import Common.Random;
import Common.Tab;
import Constant.Constant;
import Constant.MailType;
import DataObjects.BookTicket;
import DataObjects.User;
import GuerrillaMail.GeneralPage;
import org.testng.Assert;

public class CancelBookingTest extends TestBase{

	@Test
	public void TC16() {
		
		String userName = Random.getRandomString(8);
	    String fullMail = GeneralPage.creatMail(userName);
	    User user = new User(fullMail, Constant.PASSWORD,Constant.PASSWORD,  Constant.PID);

	    homePage.open();
	    homePage.gotoPage(Tab.REGISTER);
	    registerPage.register(user);
	    GeneralPage.confirmMail(userName, MailType.CONFIRM);
		
		BookTicket bookTicket = new BookTicket("","Nha Trang","Huế","Soft bed with air conditioner","1");
		
		System.out.println("TC16 - User can cancel a ticket");
		System.out.println("Pre-condition: an actived account is existing");
		
		System.out.println("1. Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("2. Login with a valid account");
		homePage.gotoPage(Tab.LOGIN);
		loginPage.login(user);
		
		System.out.println("3. Book a ticket");
		homePage.gotoPage(Tab.BOOKTICKET);
		bookTicketPage.bookTicket(bookTicket,2);

		System.out.println("4. Click on \"My ticket\" tab");
		homePage.gotoPage(Tab.MYTICKET);
		
		System.out.println("5. Click on \"Cancel\" button of ticket which user want to cancel.");
		System.out.println("6. Click on \"OK\" button on Confirmation message \"Are you sure?\"");
		myTicketPage.clickCancelButton("1");
		
		System.out.println("VP: The canceled ticket is disappeared.");
		Assert.assertTrue(myTicketPage.isTicketCancelled(), "Error: The ticket is still displayed in the table after deletion!");
		
	}
}
