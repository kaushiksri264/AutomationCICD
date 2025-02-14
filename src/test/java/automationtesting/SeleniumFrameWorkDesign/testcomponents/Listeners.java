package automationtesting.SeleniumFrameWorkDesign.testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import automationtesting.SeleniumFrameWorkDesign.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
	public ExtentTest test;
	
	ExtentReports extent = ExtentReporterNG.getReportObject();
	
	ThreadLocal<ExtentTest> thread = new ThreadLocal<>();
	
	@Override
	public void onTestStart(ITestResult result) {
		
		test = extent.createTest(result.getMethod().getMethodName());
		thread.set(test);//unique Thread id (ErrorValidation ) - > test
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		thread.get().log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		thread.get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		
		//screenShot
		String filePath = null;
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thread.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
	}
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
