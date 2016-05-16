package com.totalwine.test.storelocator;

/*
 * Store Detail Page
 * Workflow:
 * 1.  Navigate to McLean Store Detail Page
 * 2. Validate Elements:
 * 		State-wide notification
 * 		Store Image
 * 		Map View
 * 		Print link
 * 		Address
 * 		Hours
 * 		Features
 * 		Nearby stores
 * 		Store Details
 * 		Store Highlights
 * 		Store Staff
 * 		Charity & Donations
 * 		Careers
 * 		Upcoming Store Events
 * 3. Click Make this my store button
 * 		Validate store setting
 *  
 * Technical Modules:
 * 	1. BeforeMethod (Test Pre-requisites):
 * 			Maximize browser window
 * 	2. Test (Workflow)
 * 	3. AfterMethod
 * 			Take screenshot, in case of failure
 * 			Close webdriver
 * 	4. AfterClass
 * 			Quit webdriver
 */

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class StoreDetail extends Browser {
	
	String StoreLink = "ul.header-classes > li > a[href*=\\/store-finder]";
	
	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	 } 
	
	@Test //Stores by State dropdown
	public void StoreDetailTest () throws InterruptedException, AWTException {
		logger=report.startTest("SL: Stores by State Dropdown Test");
		String IP = "71.193.51.0";
		SiteAccess.ActionAccessSite(driver, IP);
		    
	    //Navigate to the Store Locator page
	    driver.findElement(By.cssSelector(StoreLink)).click();
	    Thread.sleep(3000);
	    
	    //Navigate to the McLean Store Detail page
	    driver.findElement(By.id("storelocator-query")).click();
	    driver.findElement(By.id("storelocator-query")).clear();
	    driver.findElement(By.id("storelocator-query")).sendKeys("20817");
	    driver.findElement(By.id("storeFinderBtn")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.cssSelector("a.analyticsStoreLink > p")).click(); //First result - McLean
	    Thread.sleep(3000);
	    
	    //Validate the Get Directions link
	    driver.findElement(By.cssSelector("a.getdir.analyticsGetDir")).click();
	    Thread.sleep(2000);
	    Assert.assertTrue(driver.findElement(By.cssSelector("input#SUBMIT")).isDisplayed(),"The map view didn't load upon clicking the Get Directions link");
	    
	    //Validate the presence of all elements
	    Assert.assertEquals(driver.findElements(By.cssSelector("div#notificationDiv")).isEmpty(), false); //State-wide notification
	    Assert.assertEquals(driver.findElements(By.cssSelector("img.jumbo-image")).isEmpty(), false); //Store image
	    driver.findElement(By.cssSelector("a.map-view")).click();
	    Thread.sleep(2000);
//	    Assert.assertEquals(driver.findElements(By.cssSelector("div#map_canvas")).isEmpty(), false); //Map View
	    Assert.assertEquals(driver.findElements(By.cssSelector("a.analyticsPrintStoreDetails")).isEmpty(), false);//Print link
	    Assert.assertEquals(driver.findElements(By.cssSelector("address.right-rail-typo")).isEmpty(), false);//Address
	    Assert.assertEquals(driver.findElements(By.cssSelector("a.analyticsWeeklyAd[href*=\"totalwine.inserts2online.com/\"]")).isEmpty(), false);//Weekly ad
	    Assert.assertEquals(driver.findElements(By.cssSelector("section.store-details-hours")).isEmpty(), false);//Hours
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("a.analyticsTastingHours")));
	    Thread.sleep(2000);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.modal-body > div.hours-body")).isEmpty(), false);//Sampling hours
	    driver.findElement(By.cssSelector("div.modal-body > div.hours-header > a.btn-close.analyticsCloseBtn")).click();
	    Thread.sleep(2000);
	    driver.switchTo().defaultContent();
	    Assert.assertEquals(driver.findElements(By.cssSelector("li[title=\"Growler station\"]")).isEmpty(), false);//Features
	    Assert.assertEquals(driver.findElements(By.cssSelector("section.store-details-features > a[href*=\"eventready.com/eventrequest\"]")).isEmpty(), false);//Book our room
	    Assert.assertEquals(driver.findElements(By.cssSelector("section.other-stores")).isEmpty(), false);//Nearby stores
	    Assert.assertEquals(driver.findElements(By.cssSelector("section.store-details-wrapper > div.secondary-heading")).isEmpty(), false);//Store Details
	    Assert.assertEquals(driver.findElements(By.cssSelector("div#store-highlights")).isEmpty(), false);//Store Highlights
	    
	    Assert.assertEquals(driver.findElement(By.cssSelector("section.store-info.product-tabs > ul.product-tabs-wrapper > li:nth-child(1) > a.analyticsProdTabs")).getText(), "Store Staff");//Store Staff
	    Assert.assertEquals(driver.findElement(By.cssSelector("section.store-info.product-tabs > ul.product-tabs-wrapper > li:nth-child(2) > a.analyticsProdTabs")).getText(), "Charity & Donations");//Charity & Donations
	    //driver.findElement(By.cssSelector("section.store-info.product-tabs > ul.product-tabs-wrapper > li:nth-child(2) > a.analyticsProdTabs")).click();
	    //Thread.sleep(2000);
	    //Assert.assertEquals(driver.findElements(By.cssSelector("a[href=\"/about-us/corporate-philanthropy\"]")).isEmpty(), false); //Charity & Donations > Read more
	    Assert.assertEquals(driver.findElement(By.cssSelector("section.store-info.product-tabs > ul.product-tabs-wrapper > li:nth-child(3) > a.analyticsProdTabs")).getText(), "Careers");//Careers
	    //driver.findElement(By.cssSelector("section.store-info.product-tabs > ul.product-tabs-wrapper > li:nth-child(3) > a.analyticsProdTabs")).click();
	    //Thread.sleep(2000);
	    //Assert.assertEquals(driver.findElements(By.cssSelector("a[href=\"/about-us/careers\"]")).isEmpty(), false); //Careers > Learn more
	    
	    //Assert.assertEquals(driver.findElements(By.cssSelector("div.events-classes-items-content")).isEmpty(), false);//Upcoming Store Events
	    //Assert.assertEquals(driver.findElements(By.cssSelector("a#upcomingEventLink")).isEmpty(), false);//See all events
	    
		//Click Make this my store button and validate store session change
	    driver.findElement(By.cssSelector("button#startInStoreBtn")).sendKeys(Keys.ARROW_DOWN);
	    driver.findElement(By.cssSelector("button#startInStoreBtn")).click();
	    PageLoad(driver);
	    Thread.sleep(2000);
//	    Assert.assertEquals(driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src")).getText(), "McLean, VA");
	}
}
