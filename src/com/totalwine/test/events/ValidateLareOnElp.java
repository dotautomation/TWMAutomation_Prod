package com.totalwine.test.events;

/*
 *** Workflow:
 * 	1. Log into an account which has pre-existing shopping list  
 * 	2. Click the "Classes & Events" link from the top level nav
 * 	3. Assert the presence of web elements on the Events landing page
 * 	4. Click on change location and select a new shipping destination
 * 	5. Assert the presence of web elements on the Events landing page after changing location
 * 
 *** Technical Modules:
 * BeforeMethod (Test Pre-requisites):
 *  	Invoke webdriver
 * 		Maximize browser window
 * Test (Workflow)
 * AfterMethod
 * 		Take screenshot, in case of failure
 * 		Close webdriver
 * AfterClass
 * 		Quit webdriver
 */

import java.io.IOException;
import jxl.read.biff.BiffException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import com.totalwine.test.pages.PageChangingStore;
import com.totalwine.test.trials.Browser;
import com.totalwine.test.actions.*;

public class ValidateLareOnElp extends Browser {
	
	public String IP = "72.66.119.61";
//	public String Event = "/events/mar-2016/virginia/mclean?storestatename=214,203,202,201,205";

	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	  } 
	
	@Test
	public void ValidateLareOnElpTest() throws InterruptedException, BiffException, IOException {
		logger=report.startTest("Validate LARE on ELP (ISP/DWS/Ship)");

		//** By Passing Age Gate and Welcome Modal
		SiteAccess.ActionAccessSite(driver, IP);
	
		//** Clicking on the Classes & Events
	    driver.findElement(By.cssSelector("div.parent-header-wrapper > div > nav > ul > li:nth-child(6) > a")).click();
	    Thread.sleep(3000);
	    sAssert.assertEquals(driver.findElements(By.cssSelector("div.event-title")).isEmpty(),false, "If Event title not appear then test will fail");

	    // **  Changing Store location
	 	driver.findElement(PageChangingStore.YourStore).click();
	    Thread.sleep(5000);
	    driver.findElement(PageChangingStore.ChangeLocation).click();
	    Thread.sleep(5000);
	 	driver.findElement(PageChangingStore.ChooseShipingDistinationTab).click();
	    Thread.sleep(3000);
	 	driver.findElement(PageChangingStore.ClickingDropDown).click();
	    Thread.sleep(3000);
	    
	    JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of site
	    js1.executeScript("arguments[0].click();", driver.findElement(PageChangingStore.ShippingState));     
	    Thread.sleep(3000);
	 	driver.findElement(PageChangingStore.MakeThisMyShippingStateButton).click();
	    Thread.sleep(7000);

		//** Clicking on the Classes & Events
	    driver.findElement(By.cssSelector("div.parent-header-wrapper > div > nav > ul > li:nth-child(6) > a")).click();
	    Thread.sleep(3000);

	    driver.findElement(By.cssSelector("#Email-Modal > div > div > div > div > div > div > ul > li:nth-child(2) > a")).click();
	    Thread.sleep(3000);
	    
	    driver.findElement(By.cssSelector("#Email-Modal > div > div > div > div > div > div > ul > li:nth-child(2) > ul > div > div > li:nth-child(1) > a")).click();
	    sAssert.assertEquals(driver.findElements(By.cssSelector("div.event-title")).isEmpty(),false, "If Event title not appear then test will fail");
	    logger.log(LogStatus.PASS, "Validated LARE on ELP");
	    Thread.sleep(3000);
//	    sAssert.assertAll();
	}
}