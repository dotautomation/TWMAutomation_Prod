package com.totalwine.test.mobile;

/*
 * Browse Event Workflow
 * Workflow:
 * 	1. Click the "Classes & Events" link from the top level nav
 * 	2. Assert the presence of web elements on the Events landing page
 * 	3. Click on the first event displaying on the Events landing page, navigating to the Events detail page.
 * 	4. Assert the presence of web elements on on the Events detail page.
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
import org.openqa.selenium.JavascriptExecutor;

import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.trials.Browser;

public class MobileBrowseEvent extends Browser {
	
	private String IP="72.66.119.61";
	
	@Test 
	public void MobileBrowseEventTest () throws InterruptedException {
		logger=report.startTest("Mobile Browse Event Test");
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(PageGlobal.AgeGateYes).click();
		Thread.sleep(5000);

		// **  By passing location
		driver.findElement(By.cssSelector("div.ChooseStoreButtons > button#btnNo.btn.btn-gray")).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		
		// **  Clicking on Hamburger menu
		driver.findElement(By.cssSelector("header > section > section > section > section.header-banner-wrapper > section > div > div.mobile-logo-left > div")).click();
		Thread.sleep(3000);
		JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
		js1.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#pdp-left-nav > li:nth-child(11) > a > span")));        
	    Thread.sleep(3000);
	    SiteAccess.ActionAccessMobileAgeGate(driver);

	    //Validate Mobile ELP
	    Assert.assertEquals(driver.findElements(By.cssSelector("section.elp-pagetitle")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("button.btn-brown.anFilter")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.elp-event-img")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("button.eventCalender.anAddToCalendar")).isEmpty(),false);
	    driver.findElement(By.xpath("//a[contains(@href,'/e/ec')]")).click();
	    Thread.sleep(3000);
	    SiteAccess.ActionAccessMobileAgeGate(driver);
	    
	    //Validate Mobile EDP (same as Desktop EDP)
	    Assert.assertEquals(driver.findElements(By.cssSelector("section.store-right-hours-tasting > div.search-result-list-buy-ctrls")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("section.event-testing-profile")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("ul.right-rail-typo > li")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("li.print-container.anPrintEventDetails")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.linkText("Events")).isEmpty(),false);
	}
}