package com.totalwine.test.aml;
/*
 **** Account Home
 **** Work flow : 
 *  Click on "Account" > "Sign into your account"  (from the header) or "Account info" > "Account login" in footer section 
 *  In the Sign in popup > Signin using registered email and Password.
 *  Member's Account Home page appear
 *  Click on "Address book" link from the left navigation panel.  
 *  Click on "Edit" in the "Profile Address" Column. Add/Edit Address and Save.
 *  Click on "Add new address". Add/Edit Address and Save.

 **** Technical Modules:
 * 	DataProvider: Checkout test input parameters
 * 	BeforeMethod (Test Pre-requisites):
 * 		Invoke WebDriver
 * 		Maximize browser window
 * 	Test (Workflow)
 * 	AfterMethod
 * 		Take screenshot, in case of failure
 * 		Close WebDriver
 * 	AfterClass
 * 		Quit WebDriver
 */

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.LogStatus;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;
import com.totalwine.test.pages.*;

public class AddressBook extends Browser {
	
	private String IP="71.193.51.0";
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	  }  
	
	@Test
	public void AddressBookTest() throws InterruptedException {
		logger=report.startTest("AML - Registered users Account Home ( Dashboard) verification. ");
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(PageGlobal.AgeGateYes).click();
		Thread.sleep(5000);
	    driver.findElement(PageGlobal.NewSiteIntroClose).click();
	    Thread.sleep(5000);
	    
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
	    
	    //**Navigate to the User Address book link
	    driver.findElement(PageAccountHome.AddressBook).click();  
	    
	    //** Editing and verifying "Profile Address" 
	    driver.findElement(PageAccountHome.EditProfileAddress).click();
	    Assert.assertEquals(driver.findElements(PageAccountHome.EditProfileAddress).isEmpty(),false);
	    Thread.sleep(6000);
	    driver.findElement(PageAccountHome.EditAddressClose).click();
	    Assert.assertEquals(driver.findElements(PageAccountHome.EditAddressClose).isEmpty(),false);
	    Thread.sleep(5000);
	    logger.log(LogStatus.PASS, "verifying Profile Address");
	    	    
	    //** Adding and verifying "New Address" insertion 
	    driver.findElement(PageAccountHome.AddNewAddress).click();
	    Assert.assertEquals(driver.findElements(PageAccountHome.AddNewAddress).isEmpty(),false);
	    Thread.sleep(5000);
	    driver.findElement(PageAccountHome.AddAddressClose).click();
	    Assert.assertEquals(driver.findElements(PageAccountHome.AddAddressClose).isEmpty(),false);
	    Thread.sleep(5000);
	    logger.log(LogStatus.PASS, "verifying Profile Address");
		}
}