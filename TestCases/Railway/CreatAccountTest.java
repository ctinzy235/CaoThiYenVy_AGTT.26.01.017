package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;
import Constant.Constant;
import DataObjects.User;
import EnumRailway.Tab;
import GuerrillaMail.GeneralPage;
import Common.Random;


public class CreatAccountTest extends TestBase{

	@Test
	public void TC07() {
		
		String expectedErrorMsg = "This email address is already in use.";
		
		System.out.println("Pre-condition: an actived account is existing");
		User user = new User(Constant.USERNAME, Constant.PASSWORD, Constant.PASSWORD, Constant.PID);
		
		System.out.println("TC07 - User can't create account with an already in-use email");
		System.out.println("Step 1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();
		
		System.out.println("Step 2. Click on \"Register\" tab");
		RegisterPage registerPage = new RegisterPage();
		registerPage =homePage.goToPage(Tab.REGISTER, RegisterPage.class);
		
		System.out.println("Step 3. Enter information of the created account in Pre-condition");
		System.out.println("Step 4. Click on \"Register\" button");
		registerPage = registerPage.register(user);
		
		String actualErrorMsg = registerPage.getRegisterErrorMsg();
		
		System.out.println("VP: Error message \"This email address is already in use.\" displays above the form.");
		Assert.assertEquals(actualErrorMsg,expectedErrorMsg,"Error message for 'Email address already in use' is not displayed correctly!");
	
	}
	
	@Test
	public void TC08() {
		
		String expectedErrorMsg1 = "There're errors in the form. Please correct the errors and try again.";
		String expectedErrorMsg2 = "Invalid password length";
		String expectedErrorMsg3 = "Invalid ID length";
		User user = new User(Constant.USERNAME,"", "", "");

		System.out.println("TC08 - User can't create account while password and PID fields are empty");
		System.out.println("Step 1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();
		
		System.out.println("Step 2. Click on Register tab");
		RegisterPage registerPage = new RegisterPage();
		registerPage = homePage.goToPage(Tab.REGISTER,RegisterPage.class);
		
		System.out.println("Step 3. Enter valid email address and leave other fields empty");
		System.out.println("Step 4. Click on \"Register\" button");
		registerPage = registerPage.register(user);
		
		String actualErrorMsg1 = registerPage.getRegisterErrorMsg();
		String actualErrorMsg2 = registerPage.getPasswordErrorMsg();
		String actualErrorMsg3 = registerPage.getPidErrorMsg();
		
		System.out.println("VP: Message \"There're errors in the form. Please correct the errors and try again.\" appears above the form.");
		Assert.assertEquals(actualErrorMsg1, expectedErrorMsg1, "General registration error message is not displayed correctly!");
		
		System.out.println("VP: Next to password fields, error message \"Invalid password length.\" displays");
		Assert.assertEquals(actualErrorMsg2, expectedErrorMsg2, "Password validation error message is not displayed correctly!");
		
		System.out.println("VP: Next to PID field, error message \"Invalid ID length.\" displays");
		Assert.assertEquals(actualErrorMsg3, expectedErrorMsg3, "PID validation error message is not displayed correctly!");
		
	}
	
	@Test
	public void TC09() {
		
		String expectedRegisterMsg = ("Registration Confirmed! You can now log in to the site.");
		String userName = Random.getRandomString(8);
		String fullMail = GeneralPage.creatMail(userName);
		User userRegister = new User(fullMail, Constant.PASSWORD,Constant.PASSWORD, Constant.PID);
		
		System.out.println("TC09 - User create and activate account");
		System.out.println("Step 1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();
		
		System.out.println("Step 2. Click on \"Create an account\"");
		RegisterPage registerPage = new RegisterPage();
		registerPage = homePage.goToPage(Tab.REGISTER, RegisterPage.class);
		
		System.out.println("Step 3. Enter valid information into all fields");
		System.out.println("Step 4. Click on \"Register\" button");
		registerPage = registerPage.register(userRegister);
		
		System.out.println("Step 5. Get email information (webmail address, mailbox and password) and navigate to that webmail ");
		System.out.println("Step 6. Login to the mailbox");
		System.out.println("Step 7. Open email with subject containing \"Please confirm your account\"  and the email of the new account at step 3");
		System.out.println("Step 8. Click on the activate link");

		GeneralPage.confirmAccountViaMail(userName);
				
		String actualRegisterMsg = registerPage.getRegisterMsg();
		
		System.out.println("VP: Redirect to Railways page and message \"Registration Confirmed! You can now log in to the site\" is shown");
		Assert.assertEquals(actualRegisterMsg, expectedRegisterMsg, "Register message is not displayed correctly!");

	}
	
	
	
}
