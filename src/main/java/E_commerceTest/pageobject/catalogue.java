package E_commerceTest.pageobject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import E_commerceTest.centralizedPlace.centralizedPlace;

public class catalogue extends centralizedPlace
{
	WebDriver driver;
	public catalogue(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
	@FindBy(css = ".mb-3")
	List<WebElement> allProducts;
	
	By succesLogin = By.id("toast-container");
	By itemintoCart =  By.cssSelector(".card-body button:last-of-type");		// Button of Add TO Cart
	By elementInvisible = By.tagName("ngx-zspinner");		// loading element
	//By produtsBy = By.cssSelector(".mb-3");
	
	@FindBy(css = "button[routerlink*='cart']")				// cart section Button
	WebElement cart;
	
	@FindBy(css = "button[routerlink*='myorders']")
	WebElement orders;

	public List<WebElement> elementLoadedList()
	{
		elementVisible(succesLogin);			// Explicit wait using to visible success login
		return allProducts;						// Simply Returning all the elements only
	}
	
	public WebElement itemName(String item)		// passing iphone item to get its text and return WebElement
	{
		WebElement itemGot = allProducts.stream().filter(s->s.findElement(By.cssSelector("b"))
				.getText().equals(item)).findFirst().orElse(null);
		return itemGot;
	}

	public void addtoCart(String item)			// calling method which returns WebElement 
	{											// and clicking on add to cart WebElement
		WebElement itemGot = itemName(item);
		itemGot.findElement(itemintoCart).click();
		//elementVisible(succesLogin);
		elementToInvisible(elementInvisible);			// waiting for loading to disappear

	}
	public cartPage gotoCartPage()
	{
		cart.click();									// click on cart element
		cartPage cartPg = new cartPage(driver);
		return cartPg;
	}
	
	public OrdersPage gotoOrdersPage()
	{
		orders.click();
		OrdersPage orderPg = new OrdersPage(driver);
		return orderPg;
	}

}