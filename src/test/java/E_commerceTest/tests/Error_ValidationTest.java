package E_commerceTest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import E_commerceTest.pageobject.cartPage;
import E_commerceTest.pageobject.catalogue;
import E_commerceTest.testComponents.BaseTest;


public class Error_ValidationTest extends BaseTest
{
	String emailid = "pv@gmail.com";
	String password = "Pradeep1";
	String item = "IPHONE 13 PRO";
	
	@Test(groups = {"errorhandling"})
	public void errorCheck()
	{
		login.goToHome(emailid, "wegeahgathb");			// wrong pwd 
		String errorMSG = login.getErrorMSG();
		System.out.println(errorMSG);
		Assert.assertEquals("Incorrect email o password.", errorMSG);
	}
	
	@Test
	public void product_Check()
	{
		catalogue catlog = login.goToHome(emailid, password);
		
		catlog.elementLoadedList();
		catlog.addtoCart(item);
		cartPage cartPg = catlog.gotoCartPage();
		
		Boolean match = cartPg.verifyProduct(item);
		Assert.assertTrue(match);
	}
}


