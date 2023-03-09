package E_commerceTest.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import E_commerceTest.centralizedPlace.centralizedPlace;

public class checkOutPage extends centralizedPlace
{
	WebDriver driver;
	public checkOutPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	String selectPlace = "india";
	By searchResult = By.cssSelector(".ta-results");
	
	@FindBy(css = "input[placeholder='Select Country']")
	WebElement Country;
	@FindBy(css = ".ta-item:last-of-type")
	WebElement selected;
	@FindBy(css = ".action__submit")
	WebElement ordered;
	
	public void selectCountry()
	{
		Actions a = new Actions(driver);
		a.sendKeys(Country, selectPlace).build().perform();
		elementVisible(searchResult);
		selected.click();
		
	}
	public confirmationPage confirmPage()
	{
		ordered.click();
		confirmationPage confirm = new confirmationPage(driver);
		return confirm;
	}
}