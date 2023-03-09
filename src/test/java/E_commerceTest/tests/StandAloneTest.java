package E_commerceTest.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException 
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		String emailid = "pv@gmail.com";
		String password = "Pradeep1";
		String item = "IPHONE 13 PRO";
		By succesLogin = By.id("toast-container");	
		
		driver.findElement(By.id("userEmail")).sendKeys(emailid);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(succesLogin));
		List<WebElement> allElements = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement itemGot = allElements.stream().filter(s->s.findElement(By.cssSelector("b"))
							.getText().equals(item)).findFirst().orElse(null);
		itemGot.findElement(By.cssSelector(".card-body button:last-of-type")).click();	//".card-body .btn.w-10.rounded"
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName("ngx-spinner")));
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();	//button[contains(@routerlink,'cart')]
		
		List<WebElement> products = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean prod = products.stream().anyMatch(p->p.getText().equalsIgnoreCase(item));
		Assert.assertTrue(prod);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		WebElement Country = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
		String selectCountry = "india";
		By searchResult = By.cssSelector(".ta-results");
		Actions a = new Actions(driver);
		
		a.sendKeys(Country, selectCountry).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchResult));
		
		driver.findElement(By.cssSelector(".ta-item:last-of-type")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmation = driver.findElement(By.cssSelector(".hero-primary")).getText();
		//Assert.assertEquals("THANKYOU FOR THE ORDER.", confirmation);
		Assert.assertTrue(confirmation.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.quit();
	
	}

}
