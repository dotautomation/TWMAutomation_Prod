package com.totalwine.test.brandpages;

/*
 * Brand Story Telling Page Workflow
 * Workflow:
 * 	1. 
 *  
 * Technical Modules:
 * 	1. BeforeMethod (Test Pre-requisites):
 * 			Invoke webdriver
 * 			Maximize browser window
 * 	2. Test (Workflow)
 * 	3. AfterMethod
 * 			Take screenshot, in case of failure
 * 			Close webdriver
 * 	4. AfterClass
 * 			Quit webdriver
 */

import java.awt.AWTException;
import java.io.IOException;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.trials.Browser;

public class BrandStoryTelling extends Browser {

	//private WebDriver driver;
	private String IP="71.193.51.0";
	//ProfilesIni profile = new ProfilesIni();
	//FirefoxProfile testProfile = profile.getProfile("WebDriver");

	@BeforeMethod
	  public void setUp() throws Exception {
		//this.driver = ConfigurationFunctions.driver;
		//driver = new FirefoxDriver(testProfile);
	    driver.manage().window().maximize();
	}  
	
	@Test 
	public void BrandStoryTellingTest () throws InterruptedException, BiffException, IOException, AWTException {
		logger=report.startTest("Brand StoryTelling Page Test");
		SiteAccess.ActionAccessSite(driver, IP);
	    
	    //Access Brand Story Telling page via PDP's View All link
	    driver.get(ConfigurationFunctions.accessURL+"/wine/champagne-sparkling-wine/champagne/champagne-mailly-grand-cru-o-de-mailly/p/148096750");
	    Thread.sleep(3000);
	    driver.findElement(By.linkText("View all Mailly products >")).click();
	    Thread.sleep(3000);
	    String BrandSPURL = driver.getCurrentUrl();
	    Assert.assertEquals(BrandSPURL.toLowerCase().contains("/wine/brand/".toLowerCase()),true);
	    
	    //Validate elements of the Brand Story Telling page are present
	    Assert.assertEquals(driver.findElements(By.cssSelector("section.wrapper-bleed > img")).isEmpty(),false);
	    Assert.assertEquals("MAILLY", driver.findElement(By.cssSelector("div.wrapper-bleed-t > h1")).getText());
	    Assert.assertEquals(driver.findElements(By.id("btnShopStoryTelling")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("section.formbg.education-container")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("ul.education-tabs-wrapper > li.active")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("a.analyticsProductName")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.id("148096750-1")).isEmpty(),false);
	    
	    //Validate elements of Brand List page are absent
	    Assert.assertEquals(driver.findElements(By.id("plp-aty-tab")).isEmpty(),true);
	    
	    //Click the first link and validate that the PDP appears
	    driver.findElement(By.cssSelector("a.analyticsProductName")).sendKeys(Keys.ARROW_DOWN);
	    String BrandSPName = driver.findElement(By.cssSelector("a.analyticsProductName")).getText();
	    driver.findElement(By.cssSelector("a.analyticsProductName")).click();
	    Thread.sleep(3000);
	    String PDPName = driver.findElement(By.cssSelector("h1.product-name")).getText();
	    Assert.assertEquals(BrandSPName,PDPName);
	}
}
