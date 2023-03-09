package E_commerceTest.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import E_commerceTest.centralizedPlace.centralizedPlace;

public class cartPage extends centralizedPlace
{
	WebDriver driver;
	public cartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css= ".cartSection h3")
	List<WebElement> products;
	
	@FindBy(css = ".totalRow button")
	WebElement checkout;
	
	public Boolean verifyProduct(String item)
	{
		Boolean productMatch = products.stream().anyMatch(p->p.getText().equalsIgnoreCase(item));
		return productMatch;
	}
	public checkOutPage goTochechOutPage()
	{
		checkout.click();
		checkOutPage checkPg = new checkOutPage(driver);
		return checkPg;
	}
	
}