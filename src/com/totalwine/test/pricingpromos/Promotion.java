package com.totalwine.test.pricingpromos;

/*
 * Promotions Workflow
 * Workflow:
 * 	1. 
 *  2.
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class Promotion extends Browser {

	//private WebDriver driver;
	private String IP="98.169.134.0";
	//ProfilesIni profile = new ProfilesIni();
	//FirefoxProfile testProfile = profile.getProfile("WebDriver");

	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	}  

	//Promo Code in Bugfix and UAT 1221: $5 off $50 worth of WD Chardonnay, except for items ending in .97

	@Test 
	public void PricingTest () throws InterruptedException, BiffException, IOException, AWTException {
		
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	    driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
	    Thread.sleep(5000);
	    
	    //Eligible WD Item
	    	//Add eligible item to cart
	    
		    //Access cart and apply promotion
		    
		    //Validate successful application of promotion
		    
		    //Empty the cart
	    
	    //Ineligible WD Item
	    	//Add eligible item to cart
	    
		    //Access cart and apply promotion
		    
		    //Validate successful application of promotion
		    
		    //Empty the cart
	    
	    
	    //Ineligible .97 Item
	    	//Add ineligible item to cart (item ending in .97)
	    
		    //Access cart and apply promotion
		    
		    //Validate that promo code cannot be applied
		    
		    //Empty the cart
	    
	    //Ineligible Category
	    	//Add ineligible spirits item to cart
	    
		    //Access cart and apply promotion
		    
		    //Validate that promo code cannot be applied
		    
		    //Empty the cart
	    
	    //Price restriction
	    	//Add eligible item, but not meeting the total threshold (total price < $50)
	    
		    //Access cart and apply promotion
		    
		    //Validate that promo code cannot be applied
		    
		    //Empty the cart
	}
}
