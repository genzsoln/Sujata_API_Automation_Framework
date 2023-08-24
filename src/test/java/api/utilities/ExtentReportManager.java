package api.utilities;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;  // common data
    public ExtentTest test;  // for writing test
    
    String repName;

    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp
        repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); // specify location of the report

        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); // Title of the report
        sparkReporter.config().setReportName("Pet Store Users API"); // Name of the project
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Pet Store Users API");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("user", "Sujata");
    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable().getMessage());
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext testContext) {
        extent.flush();
        // once you created everything then we call flush method to make sure everything is ready in the report
        // if you do not call this method the report will not generate
    }
}

//package api.utilities;
//
//import java.sql.Date;
//import java.text.SimpleDateFormat;
//
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//
//public class ExtentReportManager implements ITestListener
//{
//	//Predefined classes
//	
//	public ExtentSparkReporter sparkReporter;
//	public ExtentReports extent;  //common data
//	public ExtentTest test;  //for writting test
//	
//	String repName;
//	
//	public void onStart(ITestContext testContext)
//	{
//		String timeStamp=new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date(0)); //time stamp
//		repName="Test-Report-"+timeStamp+".html";
//		
//		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName); //specify location of the report
//		
//		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); //Title of the report
//		sparkReporter.config().setReportName("Pet Store Users APT"); //Name of the project
//		sparkReporter.config().setTheme(Theme.DARK);
//		
//		ExtentReports extent=new ExtentReports();
//		extent.attachReporter(sparkReporter);
//		extent.setSystemInfo("Application","Pet Store Users APT");
//		extent.setSystemInfo("Operating System",System.getProperty("os.name"));
//		extent.setSystemInfo("User Name",System.getProperty("user.name"));
//		extent.setSystemInfo("Environment","QA");
//		extent.setSystemInfo("user","Sujata");
//	}
//	
//	public void onTestSucess(ITestResult result)
//	{
//		test=extent.createTest(result.getName());
//		test.assignCategory(result.getMethod().getGroups());
//		test.createNode(result.getName());
//		test.log(Status.PASS,"Test Passed");
//	}
//	
//	public void onTestFailure(ITestResult result)
//	{
//		test=extent.createTest(result.getName());
//		test.createNode(result.getName());
//		test.assignCategory(result.getMethod().getGroups());
//		test.log(Status.FAIL, "Test Failed");
//		test.log(Status.FAIL,result.getThrowable().getMessage());
//	}
//	
//	public void onTestSkipped(ITestResult result)
//	{
//		test.extent.create(result.getName());
//		test.createNode(result.getName());
//		test.assignCategory(result.getMethod().getGroups());
//		test.log(Status.SKIP, "Test Skipped");
//		test.log(Status.SKIP,result.getThrowable().getMessage());
//	}
//	
//	public void onFinish(ITestContext testContext)
//	{
//		extent.flush();
//		//once you creatyed everything then we call flush method to make everything is ready in the report
//		// if you not call this method report will not generate
//	}
//}

