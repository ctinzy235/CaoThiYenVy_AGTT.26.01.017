package GuerrillaMail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Common.WaitUtils;
import Constant.Constant; 
import Constant.MailType;

public class GeneralPage {

	private static By btnInboxID = By.xpath("//span[@id='inbox-id']");
	private static By txtInboxID = By.xpath("//span[@id='inbox-id']//input");
	private static By btnSet = By.xpath("//button[@class='save button small']");
	private static By chkScrambleAddress = By.xpath("//input[@id='use-alias']");
	private static By lblEmailAddress = By.xpath("//span[@id='email-widget']");
	private static By btnConfirmAcc = By.xpath("//td[contains(text(), 'Please confirm your account')]");
	private static By btnResetPassword = By.xpath("//td[contains(text(), 'Please reset your password')]");
	private static By linkConfirm = By.xpath("//div[@class=\"email_body\"]//a");

	public static void navigateToWebMail() {
        System.out.println("NAVIGATING TO WEBMAIL: " + Constant.GUERRILLAMAIL_URL);
        Constant.WEBDRIVER.get(Constant.GUERRILLAMAIL_URL);
    }
	
	public static String creatMail(String randomName) {

        navigateToWebMail();

        Utilities.safeClick(btnInboxID);

        Utilities.safeSendKeys(txtInboxID, randomName);

        Utilities.safeClick(btnSet);

        WaitUtils.waitForVisible(chkScrambleAddress);
        WebElement checkbox = Constant.WEBDRIVER.findElement(chkScrambleAddress);
        
        if (checkbox.isSelected()) {
            Utilities.safeClick(chkScrambleAddress);
            System.out.println("STATUS: CHECKBOX UNTICKED");
        } else {
            System.out.println("STATUS: CHECKBOX ALREADY UNTICKED");
        }

        Utilities.safeClick(lblEmailAddress); 
        String finalMail = Constant.WEBDRIVER.findElement(lblEmailAddress).getText();
        
        return finalMail;
    }
	
	public static void confirmMail(String username, MailType type) {
		navigateToWebMail();
		
		Utilities.safeClick(btnInboxID);
		
		Utilities.safeSendKeys(txtInboxID, username);
		
        Utilities.safeClick(btnSet);
        
        switch (type) {
        case RESETPASSWORD:
            Utilities.safeClick(btnResetPassword);
            break;
        case CONFIRM:
            Utilities.safeClick(btnConfirmAcc);
            break;
        }
        
        Utilities.safeClick(linkConfirm);
        
        Utilities.closeCurrentAndSwitchToLatestTab();
	}
	
	
}
