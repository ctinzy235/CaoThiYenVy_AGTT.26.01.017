package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;
import DataObjects.User;
import EnumRailway.Tab;

public class LogoutTest extends TestBase{
	
	@Test
	public void TC06() {
		
		User user = new User(Constant.USERNAME, Constant.PASSWORD);

		System.out.println("TC06 - User is redirected to Home page after logging out");
		
		System.out.println("Step 1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();
		
		System.out.println("Step 2. Login with valid Email and Password");
		
		LoginPage loginPage = new LoginPage();
		loginPage = homePage.goToPage(Tab.LOGIN, LoginPage.class); 
		homePage = loginPage.login(user,HomePage.class);
		
		System.out.println("Step 3. Click on \"FAQ\" tab");
		FAQPage faqPage = new FAQPage();
		faqPage = homePage.goToPage(Tab.FAQ, FAQPage.class);
		
		System.out.println("Step 4. Click on \"Log out\" tab");
		homePage = faqPage.goToPage(Tab.LOGOUT,HomePage.class);
	
		System.out.println("VP: Home page displays.");
		Assert.assertEquals(Constant.WEBDRIVER.getCurrentUrl(),Constant.RAILWAY_URL, "User was not redirected to Home page after logging out!");
		
		System.out.println("VP: \"Log out\" tab is disappeared.");
		Assert.assertFalse(homePage.isTabLogoutDisplayed(), "Log out tab is still visible!");
		
		}

}
