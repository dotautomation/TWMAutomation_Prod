package com.totalwine.test.events;

/*
 *** Workflow:
 * 	1. Log into an account which has pre-existing shopping list  
 * 	2. Click the "Classes & Events" link from the top level nav
 * 	3. Assert the presence of web elements on the Events landing page
 * 	4. Browse for an event that has sold out
 * 	6. Verify msg "sold out" exist.  
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;
import com.totalwine.test.pages.*;
import com.totalwine.test.actions.*;

public class BrowseSoldOutEvent extends Browser {
	
	public String IP = "72.66.119.61";
	public String Event = "/events/jul-2016/virginia/mclean?storestatename=214,203,202,201,205";

	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	  }
	
	@Test
	public void BrowseSoldOutEventTest () throws InterruptedException, BiffException, IOException {
		logger=report.startTest("Add Event To Shopping List");

		//** By Passing Age Gate and Welcome Modal
		SiteAccess.ActionAccessSite(driver, IP);
		
	    //**Sign in modal with credential which has pre-existing order history, shopping list etc. 
	    Events.CustomLogin(driver);
	    
	    //**Checking for presence of merge cart modal
	    ShoppingList.MergeCartModal(driver);
		
	    // **  Selecting a Event from PDP
		driver.get(ConfigurationFunctions.accessURL+Event);
		Thread.sleep(3000);
		sAssert.assertEquals(driver.findElements(By.cssSelector("div.event-title")).isEmpty(),false, "If Event title not appear then test will fail");	
				
	    //**Sold out event verification
		WebElement element = driver.findElement(By.cssSelector(".search-sold-out-msg"));  
		new Actions(driver).moveToElement(element).perform();
		Thread.sleep(8000);
//	    JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
//	    js1.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".search-sold-out-msg")));
		sAssert.assertEquals(driver.findElements(By.cssSelector(".search-sold-out-msg")).isEmpty(),false, "If Event's Soldout message doesn't not appear then test will fail");	
		logger.log(LogStatus.PASS, "Event sold out message verification");
		Thread.sleep(4000);
		sAssert.assertAll();
	}
}