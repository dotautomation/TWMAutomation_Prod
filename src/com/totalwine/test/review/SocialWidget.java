package com.totalwine.test.review;

/*
 ****  Validating Social Widget
 ****  Work flow : 
* 	1.	Access the main page
* 	2.	Mouseover on the “Customer Service” which is situated in the footer section. 
* 	3.	Verify that all the social widget icons are present and links are working properly
 **** Technical Modules:
 * 	1. DataProvider: Checkout test input parameters
 * 	2. BeforeMethod (Test Pre-requisites):
 * 			Invoke WebDriver
 * 			Maximize browser window
 * 	3. Test (Workflow)
 * 	4. AfterMethod
 * 			Take screenshot, in case of failure
 * 			Close WebDriver
 * 	5. AfterClass
 * 			Quit WebDriver
 */

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;
import jxl.read.biff.BiffException;
import com.relevantcodes.extentreports.LogStatus;
import com.totalwine.test.actions.*;
import com.totalwine.test.pages.*;

public class SocialWidget extends Browser {

	public String IP = "71.193.51.0";

	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	  } 
	
	@Test
	public void SocialWidgetTest () throws InterruptedException, BiffException, IOException {
		logger=report.startTest("Validating Social Widget");
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		
		//** By Passing Age Gate and Welcome Modal
		Checkout.AgeGateWelcome(driver);
		PageLoad(driver); // Will not trigger the next control until loading the page
		Thread.sleep(7000);
	    
		driver.findElement(PageGlobal.CustomerService).click();
		PageLoad(driver); // Will not trigger the next control until loading the page

//		driver.findElement(PageGlobal.FB).click();
		Assert.assertEquals(driver.findElements(PageGlobal.FB).isEmpty(),false, "If social widget link doesn't exist then test will fail");
		logger.log(LogStatus.PASS, "Validating Social Widget");
		
//		driver.findElement(PageGlobal.Twitter).click();
		Assert.assertEquals(driver.findElements(PageGlobal.Twitter).isEmpty(),false, "If social widget link doesn't exist then test will fail");
		logger.log(LogStatus.PASS, "Validating Social Widget");

//		driver.findElement(PageGlobal.GooglePlus).click();
		Assert.assertEquals(driver.findElements(PageGlobal.GooglePlus).isEmpty(),false, "If social widget link doesn't exist then test will fail");
		logger.log(LogStatus.PASS, "Validating Social Widget");

//		driver.findElement(PageGlobal.YouTube).click();
		Assert.assertEquals(driver.findElements(PageGlobal.YouTube).isEmpty(),false, "If social widget link doesn't exist then test will fail");
		logger.log(LogStatus.PASS, "Validating Social Widget");

//		driver.findElement(PageGlobal.Pinterest).click();
		Assert.assertEquals(driver.findElements(PageGlobal.Pinterest).isEmpty(),false, "If social widget link doesn't exist then test will fail");
		logger.log(LogStatus.PASS, "Validating Social Widget");

//		driver.findElement(PageGlobal.Instagram).click();
		Assert.assertEquals(driver.findElements(PageGlobal.Instagram).isEmpty(),false, "If social widget link doesn't exist then test will fail");
		logger.log(LogStatus.PASS, "Validating Social Widget");

//		driver.findElement(PageGlobal.Blog).click();
		Assert.assertEquals(driver.findElements(PageGlobal.Blog).isEmpty(),false, "If social widget link doesn't exist then test will fail");
		logger.log(LogStatus.PASS, "Validating Social Widget");
	 }
}