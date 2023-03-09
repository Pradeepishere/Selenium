package E_commerceTest.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG 
{		
	public static ExtentReports getObject() 		//  static so that, call using class.method without object creating
	{
		String path = System.getProperty("user.dir") + "\\reports\\index.html";	// creating new file(index.html) in ExtentReports
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");	// new added & can be changed
		reporter.config().setDocumentTitle("Test Results");			// Title of url
		
		ExtentReports extent = new ExtentReports();				// this object knows all about reporter object
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Result");				// new added & can be changed
		return extent;
	}
	
}
