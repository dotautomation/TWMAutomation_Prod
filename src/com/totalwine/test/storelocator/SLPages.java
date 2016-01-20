package com.totalwine.test.storelocator;

/*
 * Store Locator (Misc. Pages)
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

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class SLPages extends Browser {
	
	String StoreLink = "ul.header-classes > li > a[href*=\\/store-finder]";
	
	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	 } 
	/*
	@Test //Weekly Ad Page
	public void SLWeeklyAdPageTest () throws InterruptedException {
		AccessStoreLocator();
	}*/
	
	@Test //Charity/Donations Page
	public void SLCharityPageTest () throws InterruptedException {
		AccessStoreLocator();
		//Access footer
		driver.findElement(By.cssSelector("ul.footer-tabs > li:nth-child(1)")).click(); //About Us
		Thread.sleep(2000);
		
		//Access Corporate Philantropy page
		driver.findElement(By.cssSelector("a[href*=\"/about-us/corporate-philanthropy\"]")).click(); //Corporate Philantropy
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("a[href*=\"/about-us/donation-requests\"]")).click(); //Donation Requests
		Thread.sleep(3000);
		Assert.assertEquals(driver.findElements(By.cssSelector("a[href*=\"totalwine.requestitem.com\"]")).isEmpty(),false); //Submit a request button
	}
	
	@Test //Careers Page
	public void CareersPageTest () throws InterruptedException {
		AccessStoreLocator();
		//Access footer
		driver.findElement(By.cssSelector("ul.footer-tabs > li:nth-child(1)")).click(); //About Us
		Thread.sleep(2000);
		
		//Access Careers page
		driver.findElement(By.cssSelector("a[href*=\"/about-us/careers\"]")).click(); //Careers
		Thread.sleep(3000);
		int tileCount = driver.findElements(By.cssSelector("section.yCmsComponent > section.yCmsComponent.lister-component")).size();
		
		for (int i=1;i<=tileCount;i++) {
			Actions action=new Actions(driver);
		    WebElement toplevelnav = driver.findElement(By.cssSelector("section.yCmsComponent > section.yCmsComponent.lister-component:nth-child("+(i)+")")); //Wine
		  //Hover over each category and validate "See jobs" link
		    action.moveToElement(toplevelnav).build().perform(); 
			Assert.assertEquals(driver.findElements(By.cssSelector("a[href*=\"totalwine.csod.com/ats/careersite/search.aspx\"]")).isEmpty(),false);
		}
	}
	
	@Test //All Stores Page
	public void SLAllStoresPageTest () throws InterruptedException {
		AccessStoreLocator();
		driver.findElement(By.cssSelector("a.analyticsFindAllStores")).click();
		Thread.sleep(3000);
		//Validate state list
		Assert.assertEquals(driver.findElements(By.cssSelector("div.our-store-map")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div#our-stores-address > ul > li[data-state=\"#az\"]")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div#our-stores-address > ul > li[data-state=\"#ca\"]")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div#our-stores-address > ul > li[data-state=\"#ct\"]")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div#our-stores-address > ul > li[data-state=\"#de\"]")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div#our-stores-address > ul > li[data-state=\"#fl\"]")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div#our-stores-address > ul > li[data-state=\"#ga\"]")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div#our-stores-address > ul > li[data-state=\"#md\"]")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div#our-stores-address > ul > li[data-state=\"#ma\"]")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div#our-stores-address > ul > li[data-state=\"#mn\"]")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div#our-stores-address > ul > li[data-state=\"#mo\"]")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div#our-stores-address > ul > li[data-state=\"#nv\"]")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div#our-stores-address > ul > li[data-state=\"#nj\"]")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div#our-stores-address > ul > li[data-state=\"#nm\"]")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div#our-stores-address > ul > li[data-state=\"#nc\"]")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div#our-stores-address > ul > li[data-state=\"#sc\"]")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div#our-stores-address > ul > li[data-state=\"#tx\"]")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div#our-stores-address > ul > li[data-state=\"#va\"]")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div#our-stores-address > ul > li[data-state=\"#wa\"]")).isEmpty(),false);

		//Validate default expanded tab (Arizona)
		Assert.assertEquals(driver.findElement(By.xpath("//address/p[1]/a")).isDisplayed(), true);
	}
	
	private void AccessStoreLocator () throws InterruptedException {
		String IP = "71.193.51.0";
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	    driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
	    Thread.sleep(5000);
		    
	    //Navigate to the Store Locator page
	    driver.findElement(By.cssSelector(StoreLink)).click();
	    Thread.sleep(3000);
	}
}
