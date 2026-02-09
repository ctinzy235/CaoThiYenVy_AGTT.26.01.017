package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;
import Constant.Constant;
import DataObjects.User;
import GuerrillaMail.GeneralPage;
import Common.Random;
import Common.Tab;
import Constant.MailType;




public class ResetPasswordTest extends TestBase{

	@Test
	public void TC10() {
		System.out.println("TC10 - Reset password shows error if the new password is same as current");
		System.out.println("Pre-condition: an actived account is existing");
		
		String userName = Random.getRandomString(8);
		String fullMail = GeneralPage.creatMail(userName);
		String pid = "12345678";
		User userRegister = new User(fullMail, Constant.PASSWORD,Constant.PASSWORD, pid);
		
		homePage.open();
		homePage.gotoPage(Tab.REGISTER);
		registerPage.register(userRegister);
		GeneralPage.confirmMail(userName,MailType.CONFIRM);
		
		System.out.println("1. Navigate to QA Railway Login page");
		homePage.gotoPage(Tab.LOGIN);

		System.out.println("2. Click on \"Forgot Password page\" link");
		loginPage.gotoForgotPasswordPage();
		
		System.out.println("3. Enter the email address of the activated account");
		System.out.println("4. Click on \"Send Instructions\" button");
		loginPage.enterMailAddressResetPassword(fullMail);
		
		System.out.println("5. Login to the mailbox (the same mailbox when creating account) ");
		System.out.println("6. Open email with subject contaning \"Please reset your password\" and the email of the account at step 3");
		System.out.println("7. Click on reset link");
		
		GeneralPage.confirmMail(userName,MailType.RESETPASSWORD);
		
		String expectedMsg = ("Password Change Form");
		String actualMsg = loginPage.getPasswordChangeForm();

		System.out.println("VP: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token");
		Assert.assertEquals(expectedMsg, actualMsg, "The 'Password Change Form' header is not displayed correctly");

		System.out.println("8. Input same password into 2 fields  \"new password\" and \"confirm password\"");
		System.out.println("9. Click Reset Password");
		
		loginPage.resetPassword(Constant.PASSWORD, Constant.PASSWORD);
		
		String expectedPasswordChangeMsg = ("The new password cannot be the same with the current password");
		String actualPasswordChangeMsg = loginPage.getPasswordChangeSuccessMsg();

		System.out.println("VP: Message \"The new password cannot be the same with the current password\" is shown");
		Assert.assertEquals(expectedPasswordChangeMsg, actualPasswordChangeMsg, "The validation message does not match the expected message");
		
	}
	
	@Test
	public void TC11() {
		System.out.println("TC11 - Reset password shows error if the new password and confirm password doesn't match");
		System.out.println("Pre-condition: an actived account is existing");
		
		String userName = Random.getRandomString(8);
		String fullMail = GeneralPage.creatMail(userName);
		String pid = "12345678";
		User userRegister = new User(fullMail, Constant.PASSWORD,Constant.PASSWORD, pid);
		
		homePage.open();
		homePage.gotoPage(Tab.REGISTER);
		registerPage.register(userRegister);
		GeneralPage.confirmMail(userName,MailType.CONFIRM);
		
		System.out.println("1. Navigate to QA Railway Login page");
		homePage.gotoPage(Tab.LOGIN);

		System.out.println("2. Click on \"Forgot Password page\" link");
		loginPage.gotoForgotPasswordPage();
		
		System.out.println("3. Enter the email address of the activated account");
		System.out.println("4. Click on \"Send Instructions\" button");
		loginPage.enterMailAddressResetPassword(fullMail);
		
		System.out.println("5. Login to the mailbox (the same mailbox when creating account) ");
		System.out.println("6. Open email with subject contaning \"Please reset your password\" and the email of the account at step 3");
		System.out.println("7. Click on reset link");
		
		GeneralPage.confirmMail(userName,MailType.RESETPASSWORD);
		
		String expectedMsg = ("Password Change Form");
		String actualMsg = loginPage.getPasswordChangeForm();

		System.out.println("VP: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token");
		Assert.assertEquals(expectedMsg, actualMsg, "The 'Password Change Form' header is not displayed correctly");

		System.out.println("8. Input different input into 2 fields  \"new password\" and \"confirm password\"");
		System.out.println("9. Click Reset Password");
		
		loginPage.resetPassword(Constant.PASSWORD,Constant.CONFIRMPASSWORD);
		
		String expectedPasswordChangeMsg = ("Could not reset password. Please correct the errors and try again.");
		String actualPasswordChangeMsg = loginPage.getPasswordChangeErrorMsg();
		
		System.out.println("VP: Error message \"Could not reset password. Please correct the errors and try again.\" displays above the form.");
		Assert.assertEquals(expectedPasswordChangeMsg, actualPasswordChangeMsg, "The validation message does not match the expected message");
		
		System.out.println("VP: Error message \"The password confirmation did not match the new password.\" displays next to the confirm password field.");
		
		String expectedErrMsg = "The password confirmation did not match the new password.";
		String actualErrMsg = loginPage.getErrConfirmPasswordMsg();
		Assert.assertEquals(expectedErrMsg, actualErrMsg, "The message does not match the expected message");
		
	}
	
}
