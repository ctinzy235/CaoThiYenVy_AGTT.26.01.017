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
        return Utilities.safeGetText(lblLoginErrorMsg);
    }

    public String getPasswordChangeForm() {
    	return Utilities.safeGetText(lblPasswordChangeForm);
    }
    
    public String getPasswordChangeSuccessMsg() {
    	return Utilities.safeGetText(lblPasswordChangeSuccessMsg);
    }
    
    public String getPasswordChangeErrorMsg() {
    	return Utilities.safeGetText(lblPasswordChangeErrorMsg);
    }
    
    public String getErrConfirmPasswordMsg() {
    	return Utilities.safeGetText(lblErrConfirmPasswordMsg);
    }
    
    
    // Methods
    public HomePage login(User user) {
        Utilities.safeSendKeys(txtUsername, user.getEmail());
        Utilities.safeSendKeys(txtPassword, user.getPassword());
        Utilities.safeClick(btnLogin);
        return new HomePage();
    }
    
    public void gotoForgotPasswordPage() {
    	Utilities.safeClick(linkForgotPassword);
    }
    
    public void enterMailAddressResetPassword(String email) {
    	Utilities.safeSendKeys(txtEmailAddress, email);
    	Utilities.safeClick(btnSendInstruction);
    }
    
    public void resetPassword(String newPassword, String confirmPassword) {
    	Utilities.safeSendKeys(txtNewPassword, newPassword);
    	Utilities.safeSendKeys(txtConfirmPassword,confirmPassword);
    	Utilities.safeClick(btnResetPassword);
    	
    }
}
