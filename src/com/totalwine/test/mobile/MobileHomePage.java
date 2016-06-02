package com.totalwine.test.mobile;

/*
 * Mobile Home Page Workflow
 * Workflow:
 * 	1. Access the site
 * 	2. On the Age Gate, click No
 * 	3. Validate the presence of the splash screen containing the 21+ notification
 *  4. Validate that http://responsibility.org/ loads
 *  5. Re-access the site
 *  6. On the Age Gate, click Yes
 *  7. Validate that the following home page loads
 *    a. Header
 *    b. Nav Icon (Hamburger)
 *    c. Location
 *    d. Quick Links
 *    e. What's New Carousel
 *    f. Social Icons
 *    g. Terms
 *    h. Copyright
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

import org.testng.*;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.trials.Browser;

public class MobileHomePage extends Browser {
	
		private String IP="71.193.51.0";
	
	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	}  
	
	@Test
	public void MobileHomePageTest () throws InterruptedException {
		logger=report.startTest("Mobile Homepage Test");
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(PageGlobal.AgeGateNo).click();
		Thread.sleep(1000);
		//Splash screen validation
		Assert.assertEquals(driver.findElements(By.cssSelector(".info-red-box")).isEmpty(),false);

		//Validate URL for responsibility.org
		Thread.sleep(13000);
		String url = driver.getCurrentUrl();
		System.out.println(url);
		Assert.assertEquals(url, "http://responsibility.org/");
		
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(PageGlobal.AgeGateYes).click();
		Thread.sleep(5000);
		
		//HomePage validation
		Assert.assertEquals(driver.findElements(By.cssSelector("section.mobile-logo-container")).isEmpty(),false); //Header
		Assert.assertEquals(driver.findElements(By.cssSelector("div.mobile-navigation")).isEmpty(),false);  // Navigation Icon
		Assert.assertEquals(driver.findElements(By.cssSelector("p.location-text")).isEmpty(),false); //Location
	    Assert.assertEquals(driver.findElements(By.cssSelector("section.hp-quick-links")).isEmpty(),false); //Quick Links
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.partial-carousel-wrap")).isEmpty(),false); //What's New Carousel
	    Assert.assertEquals(driver.findElements(By.cssSelector("ul.mob-footer-follow-us-icons")).isEmpty(),false); //Social Icons
	    Assert.assertEquals(driver.findElements(By.cssSelector("section.mob-footer-terms")).isEmpty(),false); //Terms
	    Assert.assertEquals(driver.findElements(By.cssSelector("section.mob-footer-copyright")).isEmpty(),false); //Copyright
	}
}