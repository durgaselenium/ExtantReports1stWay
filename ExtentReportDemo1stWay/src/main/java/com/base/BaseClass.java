package com.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
  public static WebDriver driver;
  
  public ExtentHtmlReporter htmlReporter;
  public ExtentReports extent;
  public ExtentTest test;
  
  @BeforeSuite
  public void setExtent() {
 htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/ExtentReport/MyReport.html");
 
 htmlReporter.config().setDocumentTitle("Automation Test Report");
 htmlReporter.config().setReportName("OrangeHRM Test Automation Report");
 htmlReporter.config().setTheme(Theme.DARK); //standed
 

 extent = new ExtentReports();
 extent.attachReporter(htmlReporter);
 
 extent.setSystemInfo("HostName", "MyHost");
 extent.setSystemInfo("ProjeceName", "OrangeHRM");
 extent.setSystemInfo("Tester", "Devi Veeravalli");
 extent.setSystemInfo("Browser", "Chrome");
 }
  
  @AfterSuite
  public void endReport() {
  extent.flush(); 
  }
  
 @BeforeMethod	
 public void setUp() {
 WebDriverManager.chromedriver().setup();
 driver = new ChromeDriver();

 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
 driver.get("https://opensource-demo.orangehrmlive.com/");
	}
 
 @AfterMethod
 public void tearDown(ITestResult result) throws IOException {
	if (result.getStatus()==ITestResult.FAILURE) {
 test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+ " - TestCase Failed", ExtentColor.RED));
 test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable()+ " - TestCase Failed", ExtentColor.RED));
 
  String pathString = BaseClass.screenShot(driver, result.getName());
  test.addScreenCaptureFromPath(pathString);
 
	}else if (result.getStatus()==ITestResult.SKIP) {
	test.log(Status.SKIP, "Skipping the TestCase is :" + result.getName());	
	}else if (result.getStatus()==ITestResult.SUCCESS) {
	test.log(Status.PASS, "Pass the TestCase is :"+ result.getName());	
	}
	
	driver.close(); 
 }

 public static String screenShot(WebDriver driver,String filename) {
 String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());	 
	 
 File sreFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
 String destination = System.getProperty("user.dir")+ "//Screenshots//"+filename+"_"+dateName+".png";
 File finaldestion = new File(destination);
 
 try {
	FileUtils.copyFile(sreFile, finaldestion);
} catch (Exception e) {
	e.getMessage();
}
return destination;
 
 }
	
}
