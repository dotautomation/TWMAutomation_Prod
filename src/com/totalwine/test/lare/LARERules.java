package com.totalwine.test.lare;

/*
 * LARE Rules
 * Workflow:
 * 	1. User entered location/selected store or ship-to-state
 *  2. Deep Link
 *  3. Profile Store set to Always Use
 *  4. Default Web Store
 * Technical Modules:
 * 	Inherited Class from Browser
 *  1. BeforeMethod (Test Pre-requisites):
 * 			Maximize browser window
 * 	2. Test (Workflow)
 * 	3. AfterMethod (from Browser)
 * 			Take screenshot, in case of failure
 * 			Close webdriver
 */

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.trials.Browser;
import com.totalwine.test.actions.*;

public class LARERules extends Browser {
	
	private String IP="71.193.51.0"; //Sacramento
	private String UnknownIP="10.125.18.63"; //Unknown IP
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	  }  
	
	@Test 
	public void LAREUserEnteredLocationTest () throws InterruptedException {
		logger=report.startTest("LARE User Entered Location Test");
		//Rule: User entered location/selected store or ship-to-state
		//Action: User accesses site and changes store via global store selector
		//Validation: Customer is shopping only in the globally selected store (store is 0.0 miles away in the Store Selector)
		connect(IP);
		driver.findElement(By.cssSelector(".fluid-store-finder-logo.fluid-icons")).click();
		Thread.sleep(2000);
//	    driver.findElement(By.cssSelector("div.header-location-nearby-stores.flyover > div.location-near-by-store-locator > table > tbody > tr > td > a.header-change-location")).click();
//	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("#storelocator-query")).clear();
	    driver.findElement(By.cssSelector("#storelocator-query")).sendKeys("21224");
	    driver.findElement(By.cssSelector("#storeFinderBtn")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("#shopThisStore")).click();
	    Thread.sleep(5000);
	    sAssert.assertEquals(driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src")).getText(),"Towson (Beltway), MD","The site session wasn't correctly displayed");
	    Actions action=new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
	    WebElement wineNav = driver.findElement(By.xpath("//a[contains(@href,'c0020')]")); 
		action.moveToElement(wineNav).build().perform(); //Top Level Menu Hover
		WebElement winePLPNav=driver.findElement(By.xpath("//a[contains(@href,'000009')]"));
		js.executeScript("arguments[0].click();", winePLPNav);
		Thread.sleep(5000);
		WebElement wineMove = driver.findElement(By.cssSelector("ul.header-classes")); //Moving the mouse away from the top level menu 
		action.moveToElement(wineMove).build().perform(); 
//		sAssert.assertEquals(driver.findElement(By.cssSelector("div.inner-items-wrapper > ul > li.act > a > span.checkStyle > label")).getText(),"Towson (Beltway), MD (0.0 miles)","The site session wasn't correctly displayed");
//		sAssert.assertAll();
	}
	
	@Test
	public void LAREDeepLinkTest () throws InterruptedException {
		//Rule: Deep Link
		//Action: User accesses site via a deep link containing the store information
		//Validation: Global store header should have the deep link's store
		logger=report.startTest("LARE Deep Link Test");
		connect(IP);
		driver.get(ConfigurationFunctions.accessURL+"/wine/white-wine/chardonnay/null/j-lohr-arroyo-vista-chardonnay/p/91517750?s=205"); //McLean, VA
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(2000);
//		sAssert.assertEquals(driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src")).getText(),"McLean, VA","The site session wasn't correctly displayed");
//		sAssert.assertAll();
	}
		
	@Test
	public void LAREAlwaysUseProfileTest () throws InterruptedException {
		//Rule: Profile Store set to Always Use
		//Action: User accesses site and then logs in
		//Validation: Global store header changes to the profile store marked as always use (mhossain@totalwine.com/grapes123)
		logger=report.startTest("LARE Profile Set to Always Use Test");
		connect(IP);
		
		//** By Passing Age Gate and Welcome Modal
		Events.CustomLogin(driver);
		PageLoad(driver); // Will not trigger the next control until loading the page
	    Thread.sleep(5000);
	    driver.switchTo().activeElement();
//	    sAssert.assertAll();
	}
	
	@Test
	public void LAREDWSTest () throws InterruptedException {
		//Rule: Default Web Store
		//Action: User accesses the site from outside the US or user's location cannot be determined
		//Validation: Global store header is DWS (1108)
		logger=report.startTest("LARE DWS Test");
		driver.get(ConfigurationFunctions.locationSet+UnknownIP);
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
		
		//Location Intercept
		Assert.assertEquals(driver.findElements(PageGlobal.LocationInterceptNo).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.id("btnSelectLocation")).isEmpty(),false);
	    driver.findElement(PageGlobal.LocationInterceptNo).click();
//	    sAssert.assertEquals(driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src")).getText(),"Sacramento (Arden), CA","The site session wasn't correctly displayed");
//	    sAssert.assertAll();
	}

	public void connect(String Address) throws InterruptedException {
		SiteAccess.ActionAccessSite(driver, Address);
	}
}