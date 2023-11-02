package Academy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Academy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener	{
	ExtentTest test;
	
	ExtentReports extent = ExtentReporterNG.getReportObject();// classname.methodname
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // Thread safe means even if parallel execution it will run correct
	
	@Override
	public void onTestStart(ITestResult result) {
	    // Every test before starting 
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);  //unique thread id 
	  }
	
	@Override
	public  void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test has passed");
	  }

	@Override
	public  void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test has failed");
		extentTest.get().fail(result.getThrowable()); // prints error message 
		
		// Screenshot and attach it	
		
		 try {
		   driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		 } catch (Exception e1)
		 {
			 e1.printStackTrace();
		 }
		
			String filePath = null;
			try {
				filePath = getScreenshot(result.getMethod().getMethodName(), driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		 
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	  }
	
	@Override
	public  void onTestSkipped(ITestResult result) {
		 
	  }
	
	@Override
	public  void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	  }
	
	@Override
	public  void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }
	
	@Override
	public  void onStart(ITestContext context) {
	    // not implemented
	  }
	
	@Override
	public  void onFinish(ITestContext context) {
	    // flush is mandatory at the end
		extent.flush();
	  }

}
