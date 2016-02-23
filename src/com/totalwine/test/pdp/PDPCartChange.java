package com.totalwine.test.pdp;

/*
 * PDP Tabs Workflow
 * Workflow:
 * 	1. Browse to a PDP
 * 	  a. Validate the contents of each tab
 *  2. Iterate through the workflow for each item type (Wine, Beer, Spirits, Accessory, Cigar)
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
import com.totalwine.test.pages.*;
import com.totalwine.test.trials.Browser;

public class PDPCartChange extends Browser {
	
	private String IP="71.193.51.0";
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	  }  
	
	@Test 
	public void PDPISPToShipTest () throws InterruptedException {
		logger=report.startTest("PDP Cart Change Test");
		driver.get(ConfigurationFunctions.locationSet+IP); //Set site to ISP Sacramento
		Thread.sleep(5000);
		driver.findElement(PageGlobal.AgeGateYes).click();
		Thread.sleep(5000);
	    driver.findElement(PageGlobal.NewSiteIntroClose).click();
	    Thread.sleep(5000);
	    
	  //Access Spirits PDP and add to cart
	    driver.get(ConfigurationFunctions.accessURL+"/spirits/scotch/blended-scotch/johnnie-walker-black/p/636175");
	    Thread.sleep(3000);
	    driver.findElement(PagePDP.BuyBoxISPRadio).click();
	    Thread.sleep(3000);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.left-ship > span.disabled")).isEmpty(),false); //Ensure that shipping radio is disabled
	    driver.findElement(PagePDP.ATCButton).click();
	    Thread.sleep(3000);
	    
	    //Access Wine PDP and validate ISP selection
	    driver.get(ConfigurationFunctions.accessURL+"/wine/white-wine/chardonnay/kendall-jackson-chardonnay/p/2403750");
	    Thread.sleep(5000);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.left > span.active")).isEmpty(), false);
	    
	    //Select shipping option and validate messaging
	    driver.findElement(PagePDP.BuyBoxShipRadio).click();
	    Thread.sleep(3000);
	    Assert.assertEquals(driver.findElement(By.cssSelector("section.pdp-tab-overview-type > div > p.error-limited-qty.ship")).getText(), 
	    		"If you add this item to the cart, we'll set your order for shipping. See details");
	    
	    //Add to cart and validate site session is changed to shipping
	    driver.findElement(PagePDP.ATCButton).click();
	    Thread.sleep(3000);
	    
	    //Confirm on Discard Modal
	    driver.findElement(PagePDP.DiscardConfirmation).click();
	    Thread.sleep(3000);
	    
	    driver.navigate().refresh();
	    Thread.sleep(3000);
	    Assert.assertEquals(driver.findElement(PageGlobal.StoreSelection).getText(), "California");
	    
	    //Access cart and validate the Spirits item is removed
	    driver.findElement(PageGlobal.MiniCart).click();
	    Thread.sleep(3000);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.prodTitle > a[href*=\"/scotch/\"]")).isEmpty(),true);
	    
	}
	/*
	@Test
	public void PDPChangeStoreTest() throws InterruptedException {
		driver.get(ConfigurationFunctions.locationSet+IP); //Set site to ISP Sacramento
		Thread.sleep(5000);
		driver.findElement(PageGlobal.AgeGateYes).click();
		Thread.sleep(5000);
	    driver.findElement(PageGlobal.NewSiteIntroClose).click();
	    Thread.sleep(5000);
	    
	    driver.get(ConfigurationFunctions.accessURL+"");//Access Wine PDP and validate ISP selection
	    //Add item to cart and validate site session
	    //Navigate to another Wine PDP and click the More Stores... modal
	    //Validate and click "Make My Store" button for another store
	    //Validate change store messaging
	    //Add item to cart and validate site session is changed to ISP for the other store
	    
	}*/
}
