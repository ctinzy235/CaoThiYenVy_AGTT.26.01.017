package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Tab;
import Constant.Constant;
import DataObjects.User;

public class LogoutTest extends TestBase{
	
	@Test
	public void TC06() {
		
		User user = new User(Constant.USERNAME, Constant.PASSWORD);

		System.out.println("TC06 - User is redirected to Home page after logging out");
		
		System.out.println("Step 1. Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2. Login with valid Email and Password");
		
		homePage.gotoPage(Tab.LOGIN); 
		loginPage.login(user);
		
		homePage.gotoPage(Tab.FAQ);
		
		homePage.gotoPage(Tab.LOGOUT);
	
		System.out.println("VP: Home page displays.");
		Assert.assertEquals(Constant.WEBDRIVER.getCurrentUrl(),Constant.RAILWAY_URL, "User was not redirected to Home page after logging out!");
		
		System.out.println("VP: \"Log out\" tab is disappeared.");
		Assert.assertFalse(homePage.isTabLogoutDisplayed(), "Log out tab is still visible!");
		
		}

}
