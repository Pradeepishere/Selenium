package E_commerceTest.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import E_commerceTest.pageobject.loginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public loginPage login;

	public WebDriver initializeDriver() throws IOException 
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\E_commerceTest\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");

		// if(browserName.equalsIgnoreCase("chrome"))
		// {
		WebDriverManager.chromedriver().setup();
		ChromeOptions opt = new ChromeOptions();
		//opt.addArguments("headless");				// for Browser to not pop up 
		driver = new ChromeDriver(opt);
		//driver.manage().window().setSize(new Dimension(1440, 900));		// max size in back end

		/*
		 * } 
		 * else if(browserName.equalsIgnoreCase("firefox")) {
		 * WebDriverManager.firefoxdriver().setup(); driver = new FirefoxDriver();
		 * driver.manage().window().maximize(); }
		 */
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public loginPage launchLogin() throws IOException {
		driver = initializeDriver();
		login = new loginPage(driver);
		login.goToWebsite();
		return login;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	
	public List<HashMap<String, String>> jsonDataToMap(String filePath) throws IOException
	{
		// read/convert json to String
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		// Convert String to HashMap	-  Using jackson databind jar from mvnRepository
		ObjectMapper mapper = new ObjectMapper();		// readValue - read json & convert to list of hashMaps
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, 
												new TypeReference<List<HashMap<String, String>>>() {});
		return data;		//  returns as Lists of HashMaps
		// {map , map1}  // like {0 ,1} indexes ,
	}
	
	public String screenShot(String tcName, WebDriver driver) throws IOException
	{					// driver has no life here, handle from Listeners result(have all info about testcases)
		
		TakesScreenshot ts = (TakesScreenshot)driver;			// cast driver to screenshot(SS) mode 
		File src = ts.getScreenshotAs(OutputType.FILE);			// taking SS in file as Output
		String path = System.getProperty("user.dir") + "//reports//" + tcName + ".png";	
															// creating ss filepath to be stored in it
		File fl = new File(path);								//  creating file in this path & storing 
		FileUtils.copyFile(src, fl);
		return path;											//	Taking SS ( in src ) & storing in ( fl )
	}
	
	

}
