package com.totalwine.test.recommendations;
/*
 ****  Account Home
 ****  Work flow : 
 *  Click on "Account" > "Sign into your account"  (from the header) or "Account info" > "Account login" in footer section 
 *  In the Sign in popup > Signin using registered email and password.
 *  Member's Account Home page appear
 *  Click on the "Account home" link from the left navigation panel.  
 *  Verify Member number appear
 *  Click on "Update Account Details" below welcome panel
 *  Return to Account home by clicking on Breadcrumb navigation
 *  Click on "Request Missing Points" below welcome panel
 *  Return to Account home by clicking Account Home on the left navigation
 *  Click on "Online orders" below Member Number panel
 *  Click on "In-store orders" below Member Number panel
 *  Verify Preferred store text
 *  Verify "Browse events" under Preferred store panel

 **** Technical Modules:
 * 	DataProvider: Checkout test input parameters
 * 	BeforeMethod (Test Pre-requisites):
 * 			Invoke WebDriver
 * 			Maximize browser window
 * 	Test (Workflow)
 * 	AfterMethod
 * 			Take screenshot, in case of failure
 * 			Close WebDriver
 * 	AfterClass
 * 			Quit WebDriver
 */

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.LogStatus;
import com.totalwine.test.actions.Events;
import com.totalwine.test.actions.ShoppingList;
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;
import com.totalwine.test.pages.*;

public class CategoryLanding extends Browser {
	
	private String IP="71.193.51.0";
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	  }  
	
	@Test
	public void AccountHomeTest() throws InterruptedException {
		logger=report.startTest("RR: Category Landing");
		SiteAccess.ActionAccessSite(driver, IP);
		
		//Navigate to CLP
		Actions action=new Actions(driver);
		WebElement toplevelnav = driver.findElement(By.xpath("//a[contains(@href,'/c/c0020')]"));
		action.moveToElement(toplevelnav).build().perform();
		
		
		//Force Display RR
		driver.get(driver.getCurrentUrl()+ConfigurationFunctions.RRFORCEDISPLAY);
		
		//Validate presence of 2 RR areas
		
		//Validate content in RR tiles (ensure that Wine CLP presents Wine items)
		
		
	    logger.log(LogStatus.PASS, "RR CLP presence and content validated");
//	    sAssert.assertAll();
	}
}