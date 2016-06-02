package com.totalwine.test.mobile;

/*
 * Web Account Registration Workflow
 * Workflow:
 * 	1. Click the "Account" link in top nav
 * 	2. Assert the presence of all links in the popup screen
 * 	3. Click the "Sign Up" link and navigate to the Registration screen
 * 	4. Complete the form with registration information and submit the form
 * 	5. Validate the presence of elements on the registration confirmation screen
 * 	6. Complete preferences and save the information
 *  7. Validate the elements presented on the Account home page.
 *  8. Log out
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

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class MobileWebRegistration extends Browser {
	
	@Test 
	public void MobileWebRegistrationTest () throws InterruptedException {
		logger=report.startTest("Mobile Web Registration Test");
		driver.get(ConfigurationFunctions.locationSet+"71.193.51.0");
		Thread.sleep(2000);
		SiteAccess.ActionAccessMobileAgeGate(driver);
		Thread.sleep(2000);
		
		// **  By passing location
		driver.findElement(By.cssSelector("div.ChooseStoreButtons > button#btnNo.btn.btn-gray")).click();
		Thread.sleep(1000);

		JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
		js1.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[contains(@href,'.totalwine.com/my-account')]")));        
		Thread.sleep(3000);
		
		//Validate Login Screen
    	Assert.assertEquals(driver.findElements(By.cssSelector("em.icon.icon-login")).isEmpty(),true); //Login Icon
	    Assert.assertEquals(driver.findElements(By.cssSelector("input#j_usernameforLogin")).isEmpty(),false); //Email address
	    Assert.assertEquals(driver.findElements(By.cssSelector("input#j_passwordforLogin")).isEmpty(),false); //Password
	    Assert.assertEquals(driver.findElements(By.cssSelector("input#check_bx_mobile_1")).isEmpty(),false); //Remember Me
	    Assert.assertEquals(driver.findElements(By.cssSelector("button#btnAccLoginfrmLogin")).isEmpty(),false); //Sign in Button
	    Assert.assertEquals(driver.findElements(By.cssSelector("a.lg-btns-frgt")).isEmpty(),false); //Reset password
	    Assert.assertEquals(driver.findElements(By.cssSelector("a.btn.btn-red.anGetStarted")).isEmpty(),false); //Create an account
	    
	    SiteAccess.ActionAccessMobileAgeGate(driver);
	    driver.findElement(By.cssSelector("a.btn.btn-red.anGetStarted")).click();
	    Thread.sleep(2000);
	    
	    //Create an account - Step 1
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.lg-crte-acc-hd-t")).isEmpty(),false);
	    driver.findElement(By.id("firstName")).clear();
	    driver.findElement(By.id("firstName")).sendKeys("Automated");
	    driver.findElement(By.id("lastName")).clear();
	    driver.findElement(By.id("lastName")).sendKeys("Tester");
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("automatedtester_"+ConfigurationFunctions.randInt()+"."+ConfigurationFunctions.randInt()+"@totalwine.com");
	    	String email = driver.findElement(By.id("email")).getAttribute("value");
	    	System.out.println("Registered Email Address: "+email);
	    driver.findElement(By.id("pwd")).clear();
	    driver.findElement(By.id("pwd")).sendKeys("grapes123");
	    driver.findElement(By.id("phone")).sendKeys("3015470004");
	    driver.findElement(By.cssSelector("#storeState")).click();
	    Thread.sleep(3000);
	    WebElement element5 = driver.findElement(By.cssSelector("#storeState > option:nth-child(1)"));  
	    new Actions(driver).moveToElement(element5).perform();
	    element5.click();
	    Thread.sleep(4000);
	    driver.findElement(By.cssSelector("#preferredStore")).click();
	    Thread.sleep(3000);
	    WebElement element8 = driver.findElement(By.cssSelector("#preferredStore > option:nth-child(1)"));  
	    new Actions(driver).moveToElement(element8).perform();  
	    element8.click();
	    Thread.sleep(3000);
	    driver.findElement(By.cssSelector("#ageCheck")).click();
	    driver.findElement(By.cssSelector("#termsAndCondCheck")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("#btnRegNext")).click();
	    Thread.sleep(3000);
	    
	    //Confirmation Screen
	    Assert.assertEquals(driver.findElements(By.cssSelector(".wel-heading")).isEmpty(),false);

	    // Filling up "Tell us about yourself"
	    driver.findElement(By.cssSelector("#address1")).sendKeys("6600 rockledge dr");
	    driver.findElement(By.cssSelector("#city")).sendKeys("Bethesda");
	    WebElement element9 = driver.findElement(By.cssSelector("#state > option:nth-child(2)"));  
	    new Actions(driver).moveToElement(element9).perform();  
	    element9.click();
	    Thread.sleep(3000);
	    driver.findElement(By.cssSelector("#zipCode")).sendKeys("20817");
	    driver.findElement(By.cssSelector("#btnSaveAccount")).click();
	    Thread.sleep(3000);

	    //Confirmation Screen
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.ahp-welcome > div.ahp-welcomeHeading")).isEmpty(),false);
	}
}