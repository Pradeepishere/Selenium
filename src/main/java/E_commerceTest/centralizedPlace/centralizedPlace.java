package E_commerceTest.centralizedPlace;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import E_commerceTest.pageobject.cartPage;

public class centralizedPlace 
{
	WebDriver driver;
	
	
	public centralizedPlace(WebDriver driver) 
	{
		this.driver = driver;
		//PageFactory.initElements(driver, this);
	}

		public void elementVisible(By findBy)
		{
			WebDriverWait wait = new  WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		}
		
		public void WebElementVisible(WebElement elem)
		{
			WebDriverWait wait = new  WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(elem));
		}
		
		public void elementToInvisible(By findBy)
		{
			WebDriverWait wait = new  WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));	
		}
		
}

