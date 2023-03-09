package E_commerceTest.pageobject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import E_commerceTest.centralizedPlace.centralizedPlace;

public class loginPage extends centralizedPlace
{				// in @BeforeMethod, login object created and driver is passed as parameter to be uesd here
	WebDriver driver;						// Instance/GLobal Variable
	public loginPage(WebDriver driver) 		// Passing Main Driver here in Constructor
	{										// Always Constructor is executed before any execution happens
		super(driver);						// super(Driver) is giving driver to parent(centralized_Place) to use
		this.driver = driver;				// passing main driver in Global variable
		PageFactory.initElements(driver, this);   
	}

	@FindBy(id = "userEmail")
	WebElement email;
	
	@FindBy(id = "userPassword")
	WebElement passwd;
	
	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css = "div[class*='flyInOut']")
	WebElement incorrect;
	
	public void goToWebsite() 
	{
		driver.get("https://rahulshettyacademy.com/client");	
	}
	
	public catalogue goToHome(String emailid, String password)
	{
		email.sendKeys(emailid);
		passwd.sendKeys(password);
		submit.click();
		catalogue catlog = new catalogue(driver);		// driver from here is passed to catalogue page
		return catlog;
	}
	
	public String getErrorMSG()
	{
		WebElementVisible(incorrect);
		return incorrect.getText();
	}
	
}