package E_commerceTest.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import E_commerceTest.pageobject.catalogue;
import E_commerceTest.pageobject.checkOutPage;
import E_commerceTest.pageobject.confirmationPage;
import E_commerceTest.pageobject.OrdersPage;
import E_commerceTest.pageobject.cartPage;
import E_commerceTest.pageobject.loginPage;
import E_commerceTest.testComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Submit_Test extends BaseTest
{
	String emailid = "pv@gmail.com";
	String password = "Pradeep1";
	String item = "IPHONE 13 PRO";
	
	@Test(dataProvider = "getData", groups = "purchase")	
	public void submitorder(HashMap<String, String> input) throws IOException		
							// input = all values of data in data Provider will fall in input
							// HashMap is returnType of dataProvider Object
	//public void submitorder(String emailid, String password, String item) throws IOException	
	{			
						// login object is returned here when @BeforeMethod is executed
						// login object has also driver object 
		catalogue catlog = login.goToHome(input.get("emailid"), input.get("password"));
		//catalogue catlog = login.goToHome(emailid, password);	
		
		catlog.elementLoadedList();
		catlog.addtoCart(input.get("item"));		//catlog.addtoCart(item);
		cartPage cartPg = catlog.gotoCartPage();
		
		Boolean match = cartPg.verifyProduct(input.get("item"));		// item = input.get("item")
		Assert.assertTrue(match);
		
		checkOutPage checkPg = cartPg.goTochechOutPage();
		checkPg.selectCountry();
		confirmationPage confirm = checkPg.confirmPage();
		
		String confirmMsg = confirm.confirmation();
		//Assert.assertEquals("THANKYOU FOR THE ORDER.", confirmMsg);
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	
		
		//tearDown(); in @BeforeMethod
	}
	
	@Test(dependsOnMethods = {"submitorder"})
	public void OrdersHistory()
	{
		catalogue catlog = login.goToHome(emailid, password);
		OrdersPage orderPg = catlog.gotoOrdersPage();
		Assert.assertTrue(orderPg.verifyProduct(item));	
	}

	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = jsonDataToMap(System.getProperty("user.dir")+
		"\\src\\test\\java\\E_commerceTest\\data\\PurchaseOrder.json");		// 3rd option using .json extension file
		
		return new Object[][] { {data.get(0)}, {data.get(1)} };		// 1st data set  ,  2nd data set  retrieved from
																	// purchaseOrder.json file				
		
/* 2 	HashMap<String, String> hm = new HashMap<String,String>();	// 1st option for data Provider
		hm.put("emailid", "pv@gmail.com");		
		hm.put("password", "Pradeep1");			// 1st data Set 
		hm.put("item", "IPHONE 13 PRO");
		
		HashMap<String, String> hm1 = new HashMap<String, String>();
		hm1.put("emailid", "pdv@yahoo.com");
		hm1.put("password", "Pradeep12");		// 2nd data set
		hm1.put("item", "ADIDAS ORIGINAL");
				
		return new Object[][] { {hm}, {hm1} };
	*/	
										// 2nd option for data Provider
/* 1	Object[][] ob = new Object[2][3];		// object is Super parent of all data types
		
		ob[0][0] = "pv@gmail.com";				// 1st data Set 
		ob[0][1] = "Pradeep1";
		ob[0][2] = "IPHONE 13 PRO";
		
		ob[1][0] =  "pdv@yahoo.com";			// 2nd data set
		ob[1][1] =	"Pradeep12";
		ob[1][2] = 	"ADIDAS ORIGINAL";
		return ob;		
	*/
// 4		return new Object[][] { { "pv@gmail.com", "Pradeep1", "IPHONE 13 PRO" } , 
		//						{ "pdv@yahoo.com", "Pradeep12", "ADIDAS ORIGINAL" } }
		
	}
	
	
	
	
}
