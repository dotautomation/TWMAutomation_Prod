package com.totalwine.test.storelocator;

/*
 * Store Locator (Geo-locate)
 * Workflow:
 * 1. Access the site using the remoteTestIPAddress URL parameter for all test IPs
 * 2. Navigate to the Store Locator page
 * 3. Click the "Use your location" button
 * 4. Validate result
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

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class SLGeoLocate extends Browser {
	
	String[] IP = {"71.193.51.0","131.228.17.26","208.110.83.202","98.169.134.0","174.28.39.0","208.53.192.14"};
	String StoreLink = "ul.header-classes > li > a[href*=\\/store-finder]";
	
	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	 } 
	
	@Test //Stores in BDR
	public void SLGeolocateStoreinBDRTest () throws InterruptedException {
		logger=report.startTest("SL: Stores in BDR Test");
		//String[] IP = "71.193.51.0","131.228.17.26","208.110.83.202","98.169.134.0","174.28.39.0","208.53.192.14"};
		String IP = "71.193.51.0";
		//Access the site using the remoteTestIPAddress URL parameter for all test IPs
		SiteAccess.ActionAccessSite(driver, IP);
		    
	    //Navigate to the Store Locator page
	    driver.findElement(By.cssSelector(StoreLink)).click();
	    Thread.sleep(3000);
	    Assert.assertEquals(driver.findElements(By.id("storelocator-query")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.id("storeFinderBtn")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.linkText("See all stores and shipping locations")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("address > p")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div#map_canvas")).isEmpty(),false);
	}
		
	@Test //No stores in BDR - ship to state
	public void SLGeolocateNoStoreinBDRTest () throws InterruptedException {
		logger=report.startTest("SL: Not Stores in BDR Test");
		String IP = "208.53.192.14";
		//Access the site using the remoteTestIPAddress URL parameter for all test IPs
		SiteAccess.ActionAccessSite(driver, IP);
		
	    //Navigate to the Store Locator page
	    driver.findElement(By.cssSelector(StoreLink)).click();
	    Thread.sleep(3000);
	    Assert.assertEquals("We found no stores that match your search criteria.", 
	    			driver.findElement(By.cssSelector("div.strlctr-top-search-wrapper > #globalMessages > div.twm-error-server > p.error-msg")).getText(),
	    			"No stores in the BDR messaging isn't displayed");
	    Assert.assertEquals(driver.findElements(By.id("changeShippingState")).isEmpty(),false,"The \"Use this location\" link wasn't displayed");
	}
	
	@Test //International
	public void SLGeolocateInternationalTest () throws InterruptedException {
		logger=report.startTest("SL: Internationally Geolocated Test");
		//String IP = "131.228.17.26";
		String IP = "85.90.227.224";
		//Access the site using the remoteTestIPAddress URL parameter for all test IPs
		SiteAccess.ActionAccessSite(driver, IP);
		
		 //Navigate to the Store Locator page
	    driver.findElement(By.cssSelector(StoreLink)).click();
	    Thread.sleep(3000);
	    Assert.assertEquals(driver.findElements(By.id("storelocator-query")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.id("storeFinderBtn")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.linkText("See all stores and shipping locations")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("address > p")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div#map_canvas")).isEmpty(),false);
	}
	
}
