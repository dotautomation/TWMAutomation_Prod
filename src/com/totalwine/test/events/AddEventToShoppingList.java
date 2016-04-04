package com.totalwine.test.events;

/*
 *** Work flow:
 * 	1. Log into an account which has Pre-existing shopping list  
 * 	2. Click the "Classes & Events" link from the top level nav
 * 	3. Assert the presence of web elements on the Events landing page
 * 	4. Click on the "Save to list" link in a future event
 * 	5. Select a pre-existed shopping list or create one and click on "Save to list" button in the pop-up window
 * 	6. Assert the presence of msg "The event has been added to your shopping list" in the 2nd pop-up
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
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;
import com.totalwine.test.pages.*;
import com.totalwine.test.actions.*;

public class AddEventToShoppingList extends Browser {
	
	public String IP = "72.66.119.61";
	public String Event = "/events/jun-2016/virginia/mclean?storestatename=205,203,201,402,401";

	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	  } 
	
	@Test
	public void AddEventToShoppingListTest () throws InterruptedException, BiffException, IOException {
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
//		Assert.assertEquals(driver.findElements(By.cssSelector("div.event-title")).isEmpty(),false, "If Event title not appear then test will fail");	
				
	    //**Adding Event to Shopping List
	    JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js1.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".btn.btn-brown-pattern.mini-add-list.anAddToListInit")));
	    Thread.sleep (6000);   
	    driver.findElement(By.cssSelector("#dWishListName > div > span > i")).click();
	    Thread.sleep (6000);
	    driver.findElement(By.cssSelector("#dWishListName > div > div > div > div.jspPane > ul > li:nth-child(2)")).click();
	    Thread.sleep (6000);
	    driver.findElement(By.cssSelector("#addToList")).click();
	    Thread.sleep (6000);
	    sAssert.assertEquals(driver.findElements(By.cssSelector(".add-top-redmsg-caps.msg-success")).isEmpty(),false, "If Event title not appear then test will fail");
	    logger.log(LogStatus.PASS, "Validated event added into Shopping list");
	    Thread.sleep (3000);
//	    sAssert.assertAll();
	}
}