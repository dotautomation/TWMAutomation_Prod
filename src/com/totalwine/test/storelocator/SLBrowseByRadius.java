package com.totalwine.test.storelocator;

/*
 * Store Locator (Geo-locate)
 * Workflow:
 * 1. Navigate to Store Locator page
 * 2. Click the Browse by state dropdown
 * 3. Validate the presence of all states
 * 4. Hover over state and validate stores' names display
 * 5. Click on store and validate appearance of store detail page
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;
import com.totalwine.test.pages.*;

public class SLBrowseByRadius extends Browser {
	
	String StoreLink = "ul.header-classes > li > a[href*=\\/store-finder]";
	
	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	 } 
	
	@Test //Stores by State dropdown
	public void SLBrowseByRadiusTest () throws InterruptedException {
		logger=report.startTest("SL: Browse stores by Radius Test");
		String IP = "98.169.134.0"; //McLean
		SiteAccess.ActionAccessSite(driver, IP);
		    
	    //Navigate to the Store Locator page
	    driver.findElement(PageGlobal.TopNavFindStore).click();
	    Thread.sleep(3000);
	    
	    //Click the radius dropdown and validate the default value (200 miles) and contents (200/150/100/50 miles)
	    Assert.assertEquals(driver.findElement(PageStoreLocator.DefaultRadiusDropdown).getText(),"200 miles"); //Default Value
	    driver.findElement(PageStoreLocator.DefaultRadiusDropdown).click();
	    int availableRadii = driver.findElements(PageStoreLocator.RadiusDropdownValues).size();
	    String[] expectedRadii = {"200 miles","150 miles","100 miles","50 miles"};
	    int [] expectedStoreCount = {20,16,12,9}; //expected store counts with center set to 98.169.134.0
 	    for (int i=0;i<availableRadii;i++) {
	    	Thread.sleep(2000);
	    	Assert.assertEquals(driver.findElement(By.cssSelector("div.dist-dropdown > span:nth-child("+(i+1)+")")).getText(), expectedRadii[i]);
	    	driver.findElement(By.cssSelector("div.dist-dropdown > span:nth-child("+(i+1)+")")).click(); //Select each radii
	    	System.out.println(expectedRadii[i]+" : "+driver.findElements(By.cssSelector("button#shopThisStore")).size()+" stores");//Count the number of stores reported
//	    	Assert.assertTrue(driver.findElements(By.cssSelector("button#shopThisStore")).size()>=expectedStoreCount[i],"Store count in store locator page is less than expected");
	    	driver.findElement(PageStoreLocator.DefaultRadiusDropdown).click();
	    }
	}
}
