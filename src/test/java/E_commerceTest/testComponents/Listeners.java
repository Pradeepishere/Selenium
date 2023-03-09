package E_commerceTest.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import E_commerceTest.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener
{
	ExtentTest test;										// Holds an entry into our Report
	ExtentReports extent = ExtentReporterNG.getObject();	// object is returning & storing
														// calling static method using classname.method from another class
	ThreadLocal<ExtentTest> extest = new ThreadLocal<ExtentTest>();		
											// for Parallel execution is used to get Unique id of each test Object
											// so that no override happens b/w test Object
				// threadLocal used for Synchronise the test object with Method
	
	@Override												// 1st = Extent report will execute here
	public void onTestStart(ITestResult result) {				// 2nd = then @Test will execute
		test = extent.createTest(result.getMethod().getMethodName());	// Result stores all info about Test which 	
																		// is going to be executed
		extest.set(test);			// unique id is assigned to test object as map in backend screen
	}								// id( of Methodname from result )-->test object
	
			// replace ,,   test = extest.get()			only for parallel execution
	
	@Override 
	public void onTestSuccess(ITestResult result) {
		// test.log(Status.PASS, "TestPass");				// Not Mandatory	
		extest.get().log(Status.PASS, "TestPass");			// for Parallel Execution
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//test.log(Status.FAIL, "Test Fail");				
		// test.fail(result.getThrowable());				// will give fail Reasons 
		extest.get().fail(result.getThrowable());
		
		try 
		{
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} 	// getting driver from (pom.xml) test / classname / getting driver from here & getting object driver
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
		
		String methName = result.getMethod().getMethodName();		// get Name of currently executing @test  
		String filepath = null;
		try 
		{
			filepath = screenShot(methName, driver);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		// test.addScreenCaptureFromPath(filepath, methName);			// storing MethName in filepath
		extest.get().addScreenCaptureFromPath(filepath, methName);		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub	
	}
	
	@Override
	public void onFinish(ITestContext context) {		// Very Important, give All Reporting at the end
		extent.flush();									// or else No reporting will be seen without flush()
		
	}

}
