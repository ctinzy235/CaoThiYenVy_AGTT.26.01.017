package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Constant.Constant;

public class TestBase {
	
	protected HomePage homePage;
    protected LoginPage loginPage;
    protected RegisterPage registerPage;
    protected BookTicketPage bookTicketPage;
    protected TimeTablePage timeTablePage;
    protected TicketPricePage ticketPricePage;

	@BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");

        Constant.WEBDRIVER = new ChromeDriver();

        Constant.WEBDRIVER.manage().window().maximize();
        
        homePage = new HomePage();
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        bookTicketPage = new BookTicketPage();
        timeTablePage = new TimeTablePage();
        ticketPricePage = new TicketPricePage();
    }

//	@AfterMethod
//	public void afterMethod() {
//		System.out.println("Post-condition");
//
//		Constant.WEBDRIVER.quit();
//	}
}
