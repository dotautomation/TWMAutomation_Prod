package com.totalwine.test.events;

/*
 * Browse Event Workflow
 * Workflow:
 * 	1. Click the "Classes & Events" link from the top level nav
 * 	2. Assert the presence of web elements on the Events landing page
 * 	3. Click on the past event from the left navigation
 * 	4. Select "Show complete month" from the drop down
 * 	5. Assert the presence of msg "This event has passed"
 *  
 * Technical Modules:
 * 	1. BeforeMethod (Test Pre-requisites):
 * 			Invoke webdriver
 * 			Maximize browser window
 * 	2. Test (Workflow)
 * 	3. AfterMethod
 * 			Take screenshot
 * 			Close webdriver
 * 	4. AfterClass
 * 			Quit webdriver
 */

import org.testng.*;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;

import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.trials.Browser;

public class PastEvents extends Browser {
	
	private String IP="72.66.119.61";
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	  }  
	
	@Test 
	public void PastEventsTest () throws InterruptedException {
		logger=report.startTest("Past Events Test");
		SiteAccess.ActionAccessSite(driver, IP);
		
		//** Clicking on the Classes & Events
	    driver.findElement(By.cssSelector("div.parent-header-wrapper > div > nav > ul > li:nth-child(6) > a")).click();
	    Thread.sleep(3000);
	    sAssert.assertEquals(driver.findElements(By.cssSelector("div.event-title")).isEmpty(),false, "If Event title not appear then test will fail");
	    
	    //** Clicking on Past Event Link
//	    driver.findElement(By.cssSelector("section.span-4 > aside > section > ul > li:nth-child(4) > a")).click();
	    driver.findElement(By.linkText("Past Events")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("li:nth-child(4) > div > ul > li > a > span > label")).click();
	    Thread.sleep(5000);
	    sAssert.assertEquals(driver.findElements(By.cssSelector(".search-qty-left")).isEmpty(),false, "Test will fail if past event msg doesn't appear" );
//	    sAssert.assertAll();
	}
}