package Grid_GitHub;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Grid2_Test {

	@Test
	public void academyCheck() throws MalformedURLException
	{
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setBrowserName("MicrosoftEdge");
		//caps.setPlatform(Platform.WIN10);
		
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.6:4444"), caps);
		
		driver.get("http://rahulshettyacademy.com");
		System.out.println(driver.getTitle());
		driver.close();
		
		

	}

}
