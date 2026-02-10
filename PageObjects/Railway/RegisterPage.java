package Railway;

import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;

import Common.Utilities;
import DataObjects.User;
import Constant.RegisterField;


public class RegisterPage extends GeneralPage{

	// Locators
	
	private final By btnRegister = By.xpath("//input[@value='Register']");
	private final By lblRegisterErrorMsg = By.xpath("//p[@class=\"message error\"]");
	private final By lblPasswordErrorMsg = By.xpath("//label[@class='validation-error'and@for='password']");
	private final By lblPidErrorMsg = By.xpath("//label[@class='validation-error'and@for='pid']");
	private final By lblRegisterMsg = By.xpath("//div[@id='content']//p");

	private String registerXpath = "//input[@id='%s']";
	
	// Elements
	
	public String getPasswordErrorMsg() {
	    return Utilities.scrollToAndGetText(lblPasswordErrorMsg);
	}

	public String getPidErrorMsg() {
	    return Utilities.scrollToAndGetText(lblPidErrorMsg);
	}

	public String getRegisterErrorMsg() {
	    return Utilities.scrollToAndGetText(lblRegisterErrorMsg);
	}
	
	public String getRegisterMsg() {
	    return Utilities.scrollToAndGetText(lblRegisterMsg);
	}
	
	protected By getFieldLocator(RegisterField register) {
		String xpath = String.format(registerXpath, register.getId());
		return By.xpath(xpath);
	}
	
	// Methods
	public void register(User user) {

		Utilities.srollToAndSendKeys(getFieldLocator(RegisterField.EMAIL),user.getEmail());
		Utilities.srollToAndSendKeys(getFieldLocator(RegisterField.PASSWORD),user.getPassword());
		Utilities.srollToAndSendKeys(getFieldLocator(RegisterField.CONFIRM_PASSWORD),user.getConfirmPassword());
		Utilities.srollToAndSendKeys(getFieldLocator(RegisterField.PID),user.getPid());
		Utilities.scrollToAndClick(btnRegister);	
		
	}
}
