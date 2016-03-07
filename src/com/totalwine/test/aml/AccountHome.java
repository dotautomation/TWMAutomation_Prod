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
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import com.relevantcodes.extentreports.LogStatus;
import com.totalwine.test.actions.Checkout;
import com.totalwine.test.config.ConfigurationFunctions;
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
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		
		//** By Passing Age Gate and Welcome Modal
		Checkout.AgeGateWelcome(driver);
	    
	    //**Access the sign in modal
	    driver.findElement(PageGlobal.TopNavAccount).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("a.btn.btn-red.acc-link.analyticsSignIn")).click();
	    
	    //**Enter valid credentials for an account having an online and in-store order history
	    driver.switchTo().frame("iframe-signin-overlay");
	    driver.findElement(PageSignInModal.ModalUsername).clear();
	    driver.findElement(PageSignInModal.ModalUsername).sendKeys("mhossain@totalwine.com");
	    driver.findElement(PageSignInModal.ModalPassword).clear();
	    driver.findElement(PageSignInModal.ModalPassword).sendKeys("grapes123");
	    driver.findElement(PageSignInModal.ModalSigninButton).click();
	    Thread.sleep(6000);
	    
	    //**Check for presence of merge cart modal
	    if (driver.findElements(By.cssSelector("button.btn.btn-red.cartMergeBtn")).size()!=0) {
	    	driver.findElement(By.cssSelector("button.btn.btn-red.cartMergeBtn")).click();
	    	Thread.sleep(2000);
	    }
	    
	    //**Navigate to the Account home link
	    driver.findElement(PageAccountHome.AccountHome).click();  
	    
	    //**Update Account Details link verification
	    driver.findElement(PageAccountHome.UpdateAccountDetails).click();
	    driver.findElement(PageAccountHome.AccountHome).click();
	    
	    //** OnlineOrders and InStoreOrders link verification
	    driver.findElement(PageAccountHome.OnlineOrders).click();
	    driver.findElement(PageAccountHome.AccountHome).click();
	    Assert.assertEquals(driver.findElements(PageAccountHome.OnlineOrders).isEmpty(),false,"Verifying online order");
	    driver.findElement(PageAccountHome.InStoreOrders).click();
	    driver.findElement(PageAccountHome.AccountHome).click();
	    Assert.assertEquals(driver.findElements(PageAccountHome.InStoreOrders).isEmpty(),false,"Verifying instore order");
	    
	    //** "Change Store link" verification
	    driver.findElement(PageAccountHome.ChangeStore).click();
	    Assert.assertEquals(driver.findElements(PageAccountHome.ChangeStore).isEmpty(),false,"Verifying Change store link");
	    Thread.sleep(3000);
	    driver.findElement(PageAccountHome.EditPreferredStore).click();
	    Assert.assertEquals(driver.findElements(PageAccountHome.EditPreferredStore).isEmpty(),false,"Verifying Preffered Store edit");
	    Thread.sleep(5000);
	    logger.log(LogStatus.PASS, "Edit Preferred Store pop-up closed");
	    
	    //** "Browser events link" verification
	    driver.findElement(PageAccountHome.BrowseEvents).click();
	    driver.navigate().back();
	    Thread.sleep(6000);
	    logger.log(LogStatus.PASS, "Browser events link verified");
	}
}
