package Academy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	 
	public static ExtentReports getReportObject() // declaring static so that it can accessed with creating an object
	{
		String path = System.getProperty("user.dir")+"\\reports\\index.html" ; // this will give project path till "ExtentReports1"
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);  //ExtentSparkReporter expects a path where report has to be created
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		
		//ExtentReports --> Main class, drives all execution
		ExtentReports extent = new ExtentReports();  /// creating object of main class 
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Anurodh More");
		return extent;
		
	}

}
