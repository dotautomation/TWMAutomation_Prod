package com.totalwine.test.mobile;

/*
 * Browse Event Workflow
 * Workflow:
 * 	1. Access to PDP from the top navigation
 * 	2. Access Mobile PDP for first item on PLP
 * 	3. Validate contents of Mobile PLP
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

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.testng.*;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.trials.Browser;

public class MobilePDP extends Browser {
	
	private String IP="71.193.51.0";
	
	@Test 
	public void MobilePDPTest () throws InterruptedException, AWTException {
		logger=report.startTest("Mobile PDP Test");
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		SiteAccess.ActionAccessMobileAgeGate(driver);
		Thread.sleep(2000);
		
		// **  By passing location
		driver.findElement(By.cssSelector("div.ChooseStoreButtons > button#btnNo.btn.btn-gray")).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		Thread.sleep(5000);
		
		//Click on Wine
		driver.findElement(By.cssSelector("section.wrapper-data > section.hp-way-fndg > section.mb > div.hp-way-fndg-cat > a.btn.btn-red.analyticsLinkComp")).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		Thread.sleep(7000);
	    
	    //Access Mobile PDP for first item on PLP
		JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
		js1.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a.analyticsProductName")));        
		SiteAccess.ActionAccessMobileAgeGate(driver);
		Thread.sleep(5000);
	    
	    //Validate contents of Mobile PLP
	    Assert.assertEquals(driver.findElements(By.cssSelector("img.carouselImage.anProductImage")).isEmpty(),false); //PDP Image
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.pdp-carousel-text.analyticsProductName")).isEmpty(),false); //Item Name
	    Assert.assertEquals(driver.findElements(By.cssSelector("span.price")).isEmpty(),false); //Item Price
	    
	    //Assert.assertEquals(driver.findElements(By.cssSelector("div.pdp-rating")).isEmpty(),false); //Rating //Commenting since Ratings are n/a in Nevada
	    Assert.assertEquals(driver.findElements(By.cssSelector("a.analyticsProdCategs")).isEmpty(),false); //Category
	    Assert.assertEquals(driver.findElements(By.cssSelector("span.pdp-wine-spec-text-desc")).isEmpty(),false); //Review
	    Assert.assertEquals(driver.findElements(By.cssSelector("select.pdp-dropdown-amount")).isEmpty(),false); //Volume Dropdown
	    
	    SiteAccess.ActionAccessMobileAgeGate(driver);
	    Robot robot = new Robot(); //for scrolling
	    
	    robot.keyPress(KeyEvent.VK_PAGE_DOWN);
        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
	    //Assert.assertEquals(driver.findElements(By.cssSelector("div.BVRRRatingNormalImage")).isEmpty(),false); //BV Average Rating
	    //Assert.assertEquals(driver.findElements(By.cssSelector("div.BVRRHistogramBarRow.BVRRHistogramBarRow4")).isEmpty(),false); //BV 4 Star Rating Histogram
	    
        SiteAccess.ActionAccessMobileAgeGate(driver);
	    robot.keyPress(KeyEvent.VK_PAGE_DOWN);
        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
	    //Assert.assertEquals(driver.findElements(By.cssSelector("span.BVRRDetailedButton")).isEmpty(),false); //View detailed reviews button
	    Assert.assertEquals(driver.findElements(By.cssSelector("ul.pdp-prod-price-current")).isEmpty(),false); //Price
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.pdp-pickup")).isEmpty(),false); //ISP Option
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.pdp-pickup-ship-item")).isEmpty(),false); //Ship Option
	    
	    SiteAccess.ActionAccessMobileAgeGate(driver);
	    robot.keyPress(KeyEvent.VK_PAGE_DOWN);
        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
	    Assert.assertEquals(driver.findElements(By.cssSelector("select.pdp-dropdown-quantity")).isEmpty(),false); //Quantity Dropdown
	    Assert.assertEquals(driver.findElements(By.cssSelector("button.anPDPAddToCart.btnAddToCartPDP")).isEmpty(),false); //ATC Button
	    Assert.assertEquals(driver.findElements(By.cssSelector("button.btn-add-to-list.anAddToListInit")).isEmpty(),false); //Add to list Button
	    Assert.assertEquals(driver.findElements(By.cssSelector("button.anPDPAddToCart.btnAddToCartPDP")).isEmpty(),false); //ATC Button
	    Assert.assertEquals(driver.findElements(By.cssSelector("a.btn-share.anProdShare")).isEmpty(),false); //Email
	    //Assert.assertEquals(driver.findElements(By.cssSelector("div.rr-item")).isEmpty(),false); //Email
	}
}