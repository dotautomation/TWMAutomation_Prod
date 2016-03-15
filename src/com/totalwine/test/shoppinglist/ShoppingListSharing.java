package com.totalwine.test.shoppinglist;

/*
 * Shopping List (Sharing via Email)
 * Workflow:
 * 1. Click on "Shopping List" from the header menu 
 * 2. Login using such an account which has existed shopping list
 * 3. Click on the Email Share link
 * 4. Fill up all fields including CC me and click on send list button
 * 4. Validate the email has send properly
 * 
 * Technical Modules:
 * 	1. BeforeMethod (Test Pre-requisites):
 * 			Invoke Webdriver
 * 			Maximize browser window
 * 	2. Test (Workflow)
 * 	3. AfterMethod
 * 			Take screenshot, in case of failure
 * 			Close Webdriver
 * 	4. AfterClass
 * 			Quit Webdriver
 */

import java.io.IOException;
import org.testng.Assert;
import jxl.read.biff.BiffException;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageSignInModal;
import com.totalwine.test.trials.Browser;
import com.relevantcodes.extentreports.*;
import com.totalwine.test.actions.*;

public class ShoppingListSharing extends Browser {

	@DataProvider(name="CheckoutParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"ShoppingList", "ShoppingListSharingUAT");
        return(retObjArr);
    } 
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();	
		 }  
	
	@Test (dataProvider = "CheckoutParameters")
	public void ShoppingListSharingTest (String Location,String Email,String Password,String RecipientName,String RecipientEmail,String Message)		
					throws InterruptedException, BiffException, IOException {		    
		driver.get(ConfigurationFunctions.locationSet+Location);
		logger=report.startTest("Shopping List - Sharing via Email");
		PageLoad(driver); // Will not trigger the next control until loading the page

		//**By Passing Age Gate and Welcome Modal
		Checkout.AgeGateWelcome(driver);  
		
	    //**Accessing Shopping List
	    driver.findElement(By.cssSelector("li.shipping-cont.loggedin-not-list > a > span.list-text")).click();
				
	    //**Enter valid credentials for an account having Pre-created shopping list
	    driver.switchTo().frame("iframe-signin-overlay");
	    driver.findElement(PageSignInModal.ModalUsername).clear();
	    driver.findElement(PageSignInModal.ModalUsername).sendKeys(Email);
	    driver.findElement(PageSignInModal.ModalPassword).clear();
	    driver.findElement(PageSignInModal.ModalPassword).sendKeys(Password);
	    driver.findElement(PageSignInModal.ModalSigninButton).click();
	    Thread.sleep(2000);

	    //**Checking for presence of merge cart modal
	    ShoppingList.MergeCartModal(driver);
	    Thread.sleep (5000);
	    
	    //** Checking for survey pop-up
	    Checkout.SurverPopup(driver);
	    
	    //**Clicking on E-mail link
	    driver.findElement(By.cssSelector(".analyticsEmailList")).click();
	    Thread.sleep (5000);
	    
	    //**Filling the "Email shopping list form"
	    driver.findElement(By.cssSelector("#recipientName")).sendKeys(RecipientName);
	    driver.findElement(By.cssSelector("#recipientEmail")).sendKeys(RecipientEmail);
	    driver.findElement(By.cssSelector("#message")).sendKeys(Message);
	    driver.findElement(By.cssSelector("#check_box_100")).click();
	    driver.findElement(By.cssSelector("#emailSLBtn")).click();
	    Thread.sleep (8000);
//	    Assert.assertEquals(driver.findElements(By.cssSelector("div.page-header")).isEmpty(),false, "Sent email verification");
	}
}