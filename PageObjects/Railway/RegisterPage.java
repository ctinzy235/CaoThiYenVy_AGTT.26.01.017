package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class RegisterPage extends GeneralPage{

	// Locators
	private final By txtEmail = By.xpath("//input[@id='email']");
	private final By txtPassword = By.xpath("//input[@id='password']");
	private final By txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By txtPid = By.xpath("//input[@id='pid']");
	private final By btnRegister = By.xpath("//input[@value='Register']");
	private final By lblRegisterErrorMsg = By.xpath("//p[@class=\"message error\"]");
	private final By lblPasswordErrorMsg = By.xpath("//label[@class='validation-error'and@for='password']");
	private final By lblPidErrorMsg = By.xpath("//label[@class='validation-error'and@for='pid']");

	
	// Elements
	
	public WebElement getTxtEmail() {
        return Constant.WEBDRIVER.findElement(txtEmail);
    }
	
	public WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(txtPassword);
    }
	
	public WebElement getTxtConfirmPassword() {
        return Constant.WEBDRIVER.findElement(txtConfirmPassword);
    }
	
	public WebElement getTxtPid() {
        return Constant.WEBDRIVER.findElement(txtPid);
    }
	
	public WebElement getBtnRegister() {
        return Constant.WEBDRIVER.findElement(btnRegister);
    }
	
	public WebElement getLblRegisterErrorMsg() {
		return Constant.WEBDRIVER.findElement(lblRegisterErrorMsg);
	}
	
	public WebElement getLblPasswordErrorMsg() {
		return Constant.WEBDRIVER.findElement(lblPasswordErrorMsg);
	}
	
	public WebElement getLblPidErrorMsg() {
		return Constant.WEBDRIVER.findElement(lblPidErrorMsg);
	}
	
	public String getPasswordErrorMsg() {
		return  getLblPasswordErrorMsg().getText();
	}
	
	public String getPidErrorMsg() {
		return getLblPidErrorMsg().getText();
	}
	
	public String getRegisterErrorMsg() {
		return getLblRegisterErrorMsg().getText();
	}
	// Methods
	public void register(String email, String password, String confirmPassword, String pid) {
		this.getTxtEmail().sendKeys(email);
		this.getTxtPassword().sendKeys(password);
		this.getTxtConfirmPassword().sendKeys(confirmPassword);
		this.getTxtPid().sendKeys(pid);
		//this.getBtnRegister().click();
		Utilities.clickByJS(this.getBtnRegister());		
		
	}
}
