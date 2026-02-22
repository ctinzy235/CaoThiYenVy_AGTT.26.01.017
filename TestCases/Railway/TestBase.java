package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;

import Constant.Constant;

public class TestBase {


    @Parameters("browser")
	@BeforeMethod
    public void beforeMethod(@Optional("chrome") String browser) {
        System.out.println("Pre-condition - Browser:" + browser);

        if ("chrome".equalsIgnoreCase(browser)) {
        	Constant.WEBDRIVER = new ChromeDriver();
       }
        else if ("firefox".equalsIgnoreCase(browser)) {
        	Constant.WEBDRIVER = new FirefoxDriver();
       }
        else {
        throw new RuntimeException("Unsupported browser: " + browser);
       }

        Constant.WEBDRIVER.manage().window().maximize();
    }

	@AfterMethod
	public void afterMethod() {
		System.out.println("Post-condition");

		Constant.WEBDRIVER.quit();
	}
}
