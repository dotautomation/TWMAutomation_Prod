package com.totalwine.test.aml;

/*
 * User Types Workflow
 * Workflow:
 * 	1. Login with the following user types and validate the expected ensuing screens
 *  	1. Existing iCongo user (sourcesystem=iCongo, accountType=Webguest)
 *		2. Lane registered customer (sourceSystem=Relate, accountType=Loyalty) - Seyi
 *		3. Password rules not matching/missing mandatory data (sourcesystem=iCongo, accountType=WebMember, delete last name/first name/phone number)
 *		4. Loyalty ID not found in hybris aka Claim Registration (sourcesystem=iCongo, accountType=Webguest, has loyalty id in Relate)
 *		5. Phone/email does not exist (WebAccountRegistration.java)
 *		6. User is not existing email only customer (sourcesystem=ET/hybris, accountType=EmailSignup)
 *		7. Multiple records found in relate for email/phone (sourceSystem=Relate, accountType=Loyalty) - Seyi
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

import org.testng.*;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;


public class UserTypes extends Browser {
	
	private String IP="71.193.51.0";
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	  }  
	
	@Test 
	public void UserTypesTest () throws InterruptedException {
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	    driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
	    Thread.sleep(5000);

	    
	}

}
