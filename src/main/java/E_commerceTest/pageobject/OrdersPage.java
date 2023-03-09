package E_commerceTest.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import E_commerceTest.centralizedPlace.centralizedPlace;

public class OrdersPage extends centralizedPlace
{
	WebDriver driver;
	public OrdersPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//tr/td[2]")			// css =" tr td:nth-child(3)"  hidden element in 1st index in css
	List<WebElement> ordersList;
	
	public Boolean verifyProduct(String item)
	{
		Boolean itemMatch = ordersList.stream().anyMatch(p->p.getText().equalsIgnoreCase(item));
		return itemMatch;
	}
	
}
