package com.totalwine.test.events;

/*
 *** Workflow:
 * 	1. Log into an account which has pre-existing shopping list  
 * 	2. Click the "Classes & Events" link from the top level nav
 * 	3. Assert the presence of web elements on the Events landing page
 * 	4. Select only one store from the left navigation
 * 	5. Browse into available event and add the even into the shopping cart. 
 * 	6. Add multiple events in the shopping cart
 * 	6. Validated multiple events added into Shopping cart msg
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
import com.totalwine.test.actions.*;

public class AddMultiEventsSameStoreToCart extends Browser {
	
	public String IP = "72.66.119.61";
	public String Event = "/events/nov-2016/virginia/mclean?storestatename=401";

	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	  } 
	
	@Test
	public void AddMultiEventsSameStoreToCartTest () throws InterruptedException, BiffException, IOException {
		logger=report.startTest("Add multiple events from same store to cart");

		//** By Passing Age Gate and Welcome Modal
		SiteAccess.ActionAccessSite(driver, IP);
		
	    //**Sign in modal with credential which has pre-existing order history, shopping list etc. 
	    Events.CustomLogin(driver);
	    
	    //**Checking for presence of merge cart modal
	    ShoppingList.MergeCartModal(driver);
		
	    // **  Selecting events from PDP
		driver.get(ConfigurationFunctions.accessURL+Event);
		Thread.sleep(3000);
		Assert.assertEquals(driver.findElements(By.cssSelector("div.event-title")).isEmpty(),false, "If Event title not appear then test will fail");	
				
	    //**Adding multiple events from same store to cart
	    JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js1.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("li:nth-child(1) > div > div.search-main-cont > div:nth-child(1) > div.search-desc > div.search-title > h2 > a")));
	    Thread.sleep (3000);
	    
	    JavascriptExecutor js2 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js2.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".btn.btn-red.add-to-cart-event.anAddToCart")));
	    Thread.sleep (3000);
	    
	    //**Checking for presence of merge cart modal
	    ShoppingList.MergeCartModal(driver);
	    
		driver.get(ConfigurationFunctions.accessURL+Event);
		Thread.sleep(3000);
		
	    JavascriptExecutor js3 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js3.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("li:nth-child(2) > div > div.search-main-cont > div:nth-child(1) > div.search-desc > div.search-title > h2 > a")));
	    Thread.sleep (3000);
	    
	    JavascriptExecutor js4 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js4.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".btn.btn-red.add-to-cart-event.anAddToCart")));
	    Thread.sleep (3000);
	    
	    //**Checking for presence of merge cart modal
	    ShoppingList.MergeCartModal(driver);

	    sAssert.assertEquals(driver.findElements(By.cssSelector(".itemsInCart.textAlignCenter")).isEmpty(),false, "If item added in your cart msg doesn't appear then test will fail");
	    logger.log(LogStatus.PASS, "Validated multiple events added into Shopping cart");
	    Thread.sleep (3000);
	}
}