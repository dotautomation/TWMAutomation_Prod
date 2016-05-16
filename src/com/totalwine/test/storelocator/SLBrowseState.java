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

public class SLBrowseState extends Browser {
	
	String StoreLink = "ul.header-classes > li > a[href*=\\/store-finder]";
	
	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	 } 
	
	@Test //Stores by State dropdown
	public void SLBrowseStateTest () throws InterruptedException {
		logger=report.startTest("SL: Stores by State dropdown Test");
		String IP = "71.193.51.0";
		SiteAccess.ActionAccessSite(driver, IP);
		    
	    //Navigate to the Store Locator page
	    driver.findElement(By.cssSelector(StoreLink)).click();
	    Thread.sleep(3000);
	    
	    //Click the Browse by state dropdown
	    driver.findElement(By.cssSelector("p.findByState")).click();
	    Thread.sleep(2000);
	    
	    //Validate the presence of all states
	    Actions action = new Actions(driver);
	    String[] States = {"Arizona","California","Connecticut","Delaware","Florida","Georgia","Maryland","Massachusetts","Minnesota",
	    		"Missouri","Nevada","New Jersey","New Mexico","North Carolina","South Carolina","Texas","Virginia","Washington"};
	    //Iterate through list validating state names
	    for (int i=0;i<States.length;i++) 
	    	Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'strlctr-top-search-lft-Findstorenav')]/ul/li["+(i+3)+"]/span")).getText(), States[i]);
	    
	    //Hover over state and validate stores' names display 
	    String[] ArizonaStores = {"Gilbert","Glendale","Goodyear","Phoenix (Camelback)","Phoenix (Desert Ridge)","Scottsdale","Tempe (Emerald Center)",
	    		"Tempe (Tempe Marketplace)","Tucson (Park Place Mall)","Tucson (The Corner)"};
	    WebElement stateName = driver.findElement(By.xpath("//div[contains(@class,'strlctr-top-search-lft-Findstorenav')]/ul/li[3]/span")); //Arizona
	    action.moveToElement(stateName).build().perform();

	    for (int i=0;i<ArizonaStores.length;i++) 
    		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'strlctr-top-search-lft-Findstorenav')]/ul/li[3]/ul/li["+(i+1)+"]/a")).getText(), ArizonaStores[i]);
	    
		//Click on store and validate appearance of store detail page
		driver.findElement(By.xpath("//div[contains(@class,'strlctr-top-search-lft-Findstorenav')]/ul/li[3]/ul/li[1]/a")).click();
		Thread.sleep(3000);
		Assert.assertEquals("Total Wine & More Gilbert", driver.findElement(By.cssSelector("div.primary-heading.span-12")).getText());
	}
}
