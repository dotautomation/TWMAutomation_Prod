package com.totalwine.test.aml;
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
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeMethod;
import com.relevantcodes.extentreports.LogStatus;
import com.totalwine.test.actions.Events;
import com.totalwine.test.actions.ShoppingList;
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.trials.Browser;
import com.totalwine.test.pages.*;

public class AccountHome extends Browser {
	
	private String IP="71.193.51.0";
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	  }  
	
	@Test
	public void AccountHomeTest() throws InterruptedException {
		logger=report.startTest("AML - Registered users Account Home ( Dashboard) verification. ");
		//** By Passing Age Gate and Welcome Modal
		SiteAccess.ActionAccessSite(driver, IP);
		
	    //**Sign in modal with credential which has pre-existing order history, shopping list etc. 
	    Events.CustomLogin(driver);
	    
	    //**Checking for presence of merge cart modal
	    ShoppingList.MergeCartModal(driver);
	    Thread.sleep(3000);
	    
	    //**Navigate to the Account home link
	    driver.findElement(PageAccountHome.AccountHome).click();  
	    Thread.sleep(3000);
	    
	    //**Update Account Details link verification
	    driver.findElement(PageAccountHome.UpdateAccountDetails).click();
	    Thread.sleep(3000);
	    driver.findElement(PageAccountHome.AccountHome).click();
	    Thread.sleep(3000);
	    
	    //** OnlineOrders and InStoreOrders link verification
	    JavascriptExecutor js = (JavascriptExecutor)driver;  // Finding out elements that are out of site
	    js.executeScript("arguments[0].click();", driver.findElement(PageAccountHome.OnlineOrders));         
	    Thread.sleep(3000);
	    driver.findElement(PageAccountHome.AccountHome).click();
	    sAssert.assertEquals(driver.findElements(PageAccountHome.OnlineOrders).isEmpty(),false,"Verifying online order");
	    driver.findElement(PageAccountHome.InStoreOrders).click();
	    Thread.sleep(3000);
	    driver.findElement(PageAccountHome.AccountHome).click();
	    Assert.assertEquals(driver.findElements(PageAccountHome.InStoreOrders).isEmpty(),false,"Verifying instore order");
	    
	    //** "Change Store link" verification
	    JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js1.executeScript("arguments[0].click();", driver.findElement(PageAccountHome.ChangeStore)); 
	    Assert.assertEquals(driver.findElements(PageAccountHome.ChangeStore).isEmpty(),false,"Verifying Change store link");
	    Thread.sleep(3000);
	    
	    JavascriptExecutor js2 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js2.executeScript("arguments[0].click();", driver.findElement(PageAccountHome.EditPreferredStore)); 
	    Assert.assertEquals(driver.findElements(PageAccountHome.EditPreferredStore).isEmpty(),false,"Verifying Preffered Store edit");
	    Thread.sleep(5000);
	    logger.log(LogStatus.PASS, "Edit Preferred Store pop-up closed");
	    
	    //** "Browser events link" verification
	    JavascriptExecutor js3 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js3.executeScript("arguments[0].click();", driver.findElement(PageAccountHome.BrowseEvents)); 
	    driver.navigate().back();
	    Thread.sleep(6000);
	    logger.log(LogStatus.PASS, "Browser events link verified");
//	    sAssert.assertAll();
	}
}