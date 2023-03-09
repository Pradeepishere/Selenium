package E_commerceTest.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import E_commerceTest.centralizedPlace.centralizedPlace;

public class confirmationPage extends centralizedPlace
{
	WebDriver driver;
	public confirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".hero-primary")
	WebElement confirmMsg;
	
	public String confirmation()
	{
		String confirmation = confirmMsg.getText();
		return confirmation;
	}
}
	
