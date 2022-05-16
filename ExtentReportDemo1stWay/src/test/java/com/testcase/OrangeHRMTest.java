package com.testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;


public class OrangeHRMTest extends BaseClass{

 @Test	
 public void orangeHRMLogoTest() {
 test = extent.createTest("orangeHRMLogoTest");
 WebElement imageElement = driver.findElement(By.xpath("//div[@id='divLogo']//img44444"));
 Assert.assertTrue(imageElement.isDisplayed());
 }	
	
 @Test
 public void orangeHRMLoginTest() throws InterruptedException {
  test = extent.createTest("orangeHRMLoginTest");	 
  driver.findElement(By.name("txtUsername")).sendKeys("Admin");
  driver.findElement(By.name("txtPassword")).sendKeys("admin123");
  driver.findElement(By.name("Submit")).click();
  
  Thread.sleep(2000);
  String actualTitle = driver.getTitle();
  String expecteTitle = "OrangeHRM";
  Thread.sleep(1000);
  Assert.assertEquals(actualTitle, expecteTitle);
 }
	

 @Test
 public void sampleCase() {
      test.createNode("Validation1");
	  Assert.assertTrue(true);
	  test.createNode("Validation2");
	  Assert.assertTrue(true);
	  test.createNode("Validation3");
	  Assert.assertTrue(true);
	  test.createNode("Validation4");
	  Assert.assertTrue(true);
	 

 }	
}
