package Railway;

import org.openqa.selenium.By;
import Common.Utilities;
import DataObjects.User;


public class LoginPage extends GeneralPage {
    // Locators
    private final By txtUsername = By.xpath("//input[@id='username']");
    private final By txtPassword = By.xpath("//input[@id='password']");
    private final By btnLogin = By.xpath("//input[@value='login']");
    private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
    private final By linkForgotPassword = By.xpath("//a[text()='Forgot Password page']");
    private final By txtEmailAddress = By.xpath("//input[@id='email']");
    private final By btnSendInstruction = By.xpath("//input[@value='Send Instructions']");
    private final By txtNewPassword = By.xpath("//input[@id='newPassword']");
    private final By txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
    private final By btnResetPassword = By.xpath("//input[@value='Reset Password']");
    private final By lblPasswordChangeForm = By.xpath("//legend[text()='Password Change Form']");
    private final By lblPasswordChangeSuccessMsg = By.xpath("//p[@class='message success']");
    private final By lblPasswordChangeErrorMsg = By.xpath("//p[@class='message error']");
    private final By lblErrConfirmPasswordMsg = By.xpath("//li[@class='confirm-password']//label[@class='validation-error']");
    // Elements

    public String getLoginErrorMsg() {
        return Utilities.scrollToAndGetText(lblLoginErrorMsg);
    }

    public String getPasswordChangeForm() {
    	return Utilities.scrollToAndGetText(lblPasswordChangeForm);
    }
    
    public String getPasswordChangeSuccessMsg() {
    	return Utilities.scrollToAndGetText(lblPasswordChangeSuccessMsg);
    }
    
    public String getPasswordChangeErrorMsg() {
    	return Utilities.scrollToAndGetText(lblPasswordChangeErrorMsg);
    }
    
    public String getErrConfirmPasswordMsg() {
    	return Utilities.scrollToAndGetText(lblErrConfirmPasswordMsg);
    }
    
    
    // Methods
    public HomePage login(User user) {
        Utilities.srollToAndSendKeys(txtUsername, user.getEmail());
        Utilities.srollToAndSendKeys(txtPassword, user.getPassword());
        Utilities.srollToAndClick(btnLogin);
        return new HomePage();
    }
    
    public void gotoForgotPasswordPage() {
    	Utilities.srollToAndClick(linkForgotPassword);
    }
    
    public void enterMailAddressResetPassword(String email) {
    	Utilities.srollToAndSendKeys(txtEmailAddress, email);
    	Utilities.srollToAndClick(btnSendInstruction);
    }
    
    public void resetPassword(String newPassword, String confirmPassword) {
    	Utilities.srollToAndSendKeys(txtNewPassword, newPassword);
    	Utilities.srollToAndSendKeys(txtConfirmPassword,confirmPassword);
    	Utilities.srollToAndClick(btnResetPassword);
    	
    }
}
