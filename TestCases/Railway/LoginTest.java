package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Random;
import Constant.Constant;
import DataObjects.User;
import EnumRailway.Tab;

public class LoginTest extends TestBase{

	@Test
	public void TC01() {
		
		User user = new User(Constant.USERNAME, Constant.PASSWORD);
		
		String expectedMsg = "Welcome " + Constant.USERNAME;
		
		System.out.println("TC01 - User can log into Railway with valid username and password");
		System.out.println("Step 1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("Step 2. Click on \"Login\" tab");
		LoginPage loginPage = new LoginPage();
		loginPage = homePage.goToPage(Tab.LOGIN,LoginPage.class);

		System.out.println("Step 3. Enter valid Email and Password.");
		System.out.println("Step 4. Click on \"Login\" button.");
		String actualMsg = loginPage.login(user,HomePage.class).getWelcomeMessage();
		
		System.out.println("VP: User is logged into Railway. Welcome user message is displayed.");
		Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
	}
	
	@Test
	public void TC02() {
		
		User user = new User("", Constant.PASSWORD);
		
		String expectedErrorMsg = "There was a problem with your login and/or errors exist in your form.";

		System.out.println("TC02 - User cannot login with blank \"Username\" textbox");
		
		System.out.println("Step 1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();
		
		System.out.println("Step 2. Click on \"Login\" tab");
		LoginPage loginPage = new LoginPage();
		loginPage = homePage.goToPage(Tab.LOGIN, LoginPage.class);
		
		System.out.println("Step 3: Leave 'Username' textbox blank and enter a valid password.");
		System.out.println("Step 4: Click on 'Login' button.");
		homePage = loginPage.login(user,HomePage.class);
		
		String actualErrorMsg = loginPage.getLoginErrorMsg();
		
		System.out.println("VP: User can't login and message \"There was a problem with your login and/or errors exist in your form. \" appears.");
		Assert.assertEquals(actualErrorMsg,expectedErrorMsg,"Error message for blank username is not displayed correctly!");
		
	}
	
	@Test
	public void TC03() {
		
		User user = new User(Constant.USERNAME, Random.getRandomPassword());
		HomePage homePage = new HomePage();
		LoginPage loginPage = new LoginPage();
		String expectedErrorMsg = "There was a problem with your login and/or errors exist in your form.";
		
		System.out.println("TC03 - User cannot log into Railway with invalid password");
		
		System.out.println("Step 1. Navigate to QA Railway Website");
		homePage.open();
		
		System.out.println("Step 2. Click on \"Login\" tab");
		loginPage = homePage.goToPage(Tab.LOGIN, LoginPage.class);
		
		System.out.println("Step 3. Enter valid Email and invalid Password");
		System.out.println("Step 4. Click on \"Login\" button");
		
		loginPage = loginPage.login(user,LoginPage.class);
		
		String actualErrorMsg = loginPage.getLoginErrorMsg();	

		System.out.println("VP: Error message \"There was a problem with your login and/or errors exist in your form.\" is displayed");
		Assert.assertEquals(actualErrorMsg,expectedErrorMsg,"Error message for invalid password is not displayed correctly!");

	}
	
	@Test
	public void TC04() {
		
		User user = new User(Constant.USERNAME, Random.getRandomPassword());
		
		String expectedErrorMsg1 = "Invalid username or password. Please try again.";
		String expectedErrorMsg4 = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
		
		System.out.println("TC04 - System shows message when user enters wrong password many times");

		System.out.println("Step 1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();
		
		System.out.println("Step 2. Click on \"Login\" tab");
		LoginPage loginPage = new LoginPage();
		loginPage = homePage.goToPage(Tab.LOGIN,LoginPage.class);
		
		System.out.println("Step 3. Enter valid information into \"Username\" textbox except \"Password\" textbox.");
		System.out.println("Step 4. Click on \"Login\" button");
		
		
		loginPage = loginPage.login(user,LoginPage.class);
		
		String actualErrorMsg1 = loginPage.getLoginErrorMsg();	
		
		System.out.println("VP: \"Invalid username or password. Please try again\" is shown");
		Assert.assertEquals(actualErrorMsg1,expectedErrorMsg1,"Error message for invalid password is not displayed correctly!");

		System.out.println("Step 5. Repeat step 3 and 4 three more times.");
		
		for(int i=1;i<=3;i++) {
			
			loginPage = loginPage.login(user,LoginPage.class);
		}
		
		String actualErrorMsg4 = loginPage.getLoginErrorMsg();	
		
		System.out.println("VP: User can't login and message \"You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.\" appears.");
		Assert.assertEquals(actualErrorMsg4,expectedErrorMsg4,"Error message for invalid password is not displayed correctly!");

	}
	
	@Test
	public void TC05() {
		
		String password = Random.getRandomPassword();
		String email = Random.getRandomEmail();
		String expectedErrorMsg = "Invalid username or password. Please try again.";

		User registerUser = new User(email, password, password,"12345678");
		User loginUser = new User(email, password);

		System.out.println("TC05 - User can't login with an account hasn't been activated");
		
		System.out.println("Step 1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();
		
		System.out.println("Pre-condition: a not-active account is existing");
		RegisterPage registerPage = new RegisterPage();
		registerPage = homePage.goToPage(Tab.REGISTER,RegisterPage.class);
		registerPage = registerPage.register(registerUser);
		
		System.out.println("Step 2. Click on \"Login\" tab");
		LoginPage loginPage = new LoginPage();
		loginPage = homePage.goToPage(Tab.LOGIN, LoginPage.class);
		
		System.out.println("3. Enter username and password of account hasn't been activated.");
		loginPage = loginPage.login(loginUser, LoginPage.class);
		
		String actualErrorMsg = loginPage.getLoginErrorMsg();	

		System.out.println("VP: User can't login and message \"Invalid username or password. Please try again.\" appears.");
		Assert.assertEquals(actualErrorMsg,expectedErrorMsg,"Error message for invalid password is not displayed correctly!");


	}
	
	
}
