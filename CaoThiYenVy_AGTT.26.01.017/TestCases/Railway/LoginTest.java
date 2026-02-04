package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;

public class LoginTest extends TestBase{

	@Test
	public void TC01() {
		System.out.println("TC01 - User can log into Railway with valid username and password");

		//1. Navigate to QA Railway Website
		HomePage homePage = new HomePage();
		homePage.open();

		//2. Click on "Login" tab
		LoginPage loginPage = homePage.gotoLoginPage();

		//3. Enter valid Email and Password
		//4. Click on "Login" button
		String actualMsg = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getWelcomeMessage();

		String expectedMsg = "Welcome " + Constant.USERNAME;
		
		// User is logged into Railway. Welcome user message is displayed.
		Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
	}
	
	@Test
	public void TC02() {
		System.out.println("TC02 - User cannot login with blank \"Username\" textbox");
		
		//1. Navigate to QA Railway Website
		HomePage homePage = new HomePage();
		homePage.open();
		
		//2. Click on "Login" tab
		LoginPage loginPage = homePage.gotoLoginPage();
		
		//3. User doesn't type any words into "Username" textbox but enter valid information into "Password" textbox 
		//4. Click on "Login" button
		loginPage.login("",Constant.PASSWORD);
		
		String actualErrorMsg = loginPage.getLoginErrorMsg();
		
		String expectedErrorMsg = "There was a problem with your login and/or errors exist in your form.";
		
		Assert.assertEquals(actualErrorMsg,expectedErrorMsg,"Error message for blank username is not displayed correctly!");
		
	}
	
	@Test
	public void TC03() {
		System.out.println("TC03 - User cannot log into Railway with invalid password");
		
		//1. Navigate to QA Railway Website
		HomePage homePage = new HomePage();
		homePage.open();
				
		//2. Click on "Login" tab
		LoginPage loginPage = homePage.gotoLoginPage();
		
		//3. Enter valid Email and invalid Password
		//4. Click on "Login" button
		String invalidPassword = Utilities.getRandomPassword();
		
		loginPage.login(Constant.USERNAME, invalidPassword);
		
		String actualErrorMsg = loginPage.getLoginErrorMsg();	
		String expectedErrorMsg = "There was a problem with your login and/or errors exist in your form.";

		Assert.assertEquals(actualErrorMsg,expectedErrorMsg,"Error message for invalid password is not displayed correctly!");

	}
	
	@Test
	public void TC04() {
		System.out.println("TC04 - System shows message when user enters wrong password many times");

		//1. Navigate to QA Railway Website
		HomePage homePage = new HomePage();
		homePage.open();
				
		//2. Click on "Login" tab
		LoginPage loginPage = homePage.gotoLoginPage();
		
		//3. Enter valid information into "Username" textbox except "Password" textbox.
		//4. 4. Click on "Login" button
		String invalidPassword = Utilities.getRandomPassword();
		
		loginPage.login(Constant.USERNAME, invalidPassword);
		
		String actualErrorMsg1 = loginPage.getLoginErrorMsg();	
		String expectedErrorMsg1 = "Invalid username or password. Please try again.";

		Assert.assertEquals(actualErrorMsg1,expectedErrorMsg1,"Error message for invalid password is not displayed correctly!");

		//5. Repeat step 3 and 4 three more times.
		for(int i=1;i<=3;i++) {
			
			loginPage.login(Constant.USERNAME, Utilities.getRandomPassword());
		}
		
		String actualErrorMsg4 = loginPage.getLoginErrorMsg();	
		String expectedErrorMsg4 = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
		
		Assert.assertEquals(actualErrorMsg4,expectedErrorMsg4,"Error message for invalid password is not displayed correctly!");

	}
	
	@Test
	public void TC05() {
		System.out.println("TC05 - User can't login with an account hasn't been activated");
		
		//1. Navigate to QA Railway Website
		HomePage homePage = new HomePage();
		homePage.open();
		
		//Pre-condition: a not-active account is existing
		RegisterPage registerPage = homePage.gotoRegisterPage();
		
		String email = Utilities.getRandomEmail();
		String password = Utilities.getRandomPassword();
		
		registerPage.register(email, password, password, "12345678");
		
		//2. Click on "Login" tab
		LoginPage loginPage = homePage.gotoLoginPage();
		
		loginPage.login(email, password);
		
		String actualErrorMsg = loginPage.getLoginErrorMsg();	
		String expectedErrorMsg = "Invalid username or password. Please try again.";

		Assert.assertEquals(actualErrorMsg,expectedErrorMsg,"Error message for invalid password is not displayed correctly!");


	}
	
	
	
}
