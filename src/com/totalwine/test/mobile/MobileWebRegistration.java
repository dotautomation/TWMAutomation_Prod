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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class MobileWebRegistration extends Browser {
	
	@Test 
	public void MobileWebRegistrationTest () throws InterruptedException {
		
		driver.get(ConfigurationFunctions.locationSet+"71.193.51.0");
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//a[contains(@href,'.totalwine.com/my-account')]")).click(); //Click "My Account"
		Thread.sleep(3000);
		
		//Validate Login Screen
    	Assert.assertEquals(driver.findElements(By.cssSelector("em.icon.icon-login")).isEmpty(),false); //Login Icon
	    Assert.assertEquals(driver.findElements(By.cssSelector("input#j_usernameforLogin")).isEmpty(),false); //Email address
	    Assert.assertEquals(driver.findElements(By.cssSelector("input#j_passwordforLogin")).isEmpty(),false); //Password
	    Assert.assertEquals(driver.findElements(By.cssSelector("input#check_bx_mobile_1")).isEmpty(),false); //Remember Me
	    Assert.assertEquals(driver.findElements(By.cssSelector("button#btnAccLoginfrmLogin")).isEmpty(),false); //Sign in Button
	    Assert.assertEquals(driver.findElements(By.cssSelector("a.lg-btns-frgt")).isEmpty(),false); //Reset password
	    Assert.assertEquals(driver.findElements(By.cssSelector("a.btn.btn-red.anGetStarted")).isEmpty(),false); //Create an account
	    
	    driver.findElement(By.cssSelector("a.btn.btn-red.anGetStarted")).click();
	    Thread.sleep(3000);
	    
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
	    driver.findElement(By.id("checkEmail")).clear();
	    driver.findElement(By.id("checkEmail")).sendKeys(email);
	    driver.findElement(By.id("pwd")).clear();
	    driver.findElement(By.id("pwd")).sendKeys("grapes123!");
	    driver.findElement(By.id("checkPwd")).sendKeys("grapes123!");
	    driver.findElement(By.id("phone")).sendKeys("3015470004");
	    
	    driver.findElement(By.cssSelector("button#btnRegNext")).click();
	    Thread.sleep(2000);
	    
	    //Create an account - Step 2
	    Assert.assertEquals(driver.findElements(By.cssSelector("select.store-loc-search-by-state")).isEmpty(),false); //Preferred Store dropdown
	    driver.findElement(By.cssSelector("select.store-loc-search-by-state")).click();
	    Select preferredStoreOption = new Select(driver.findElement(By.cssSelector("select.store-loc-search-by-state")));
	    preferredStoreOption.selectByIndex(1);
	    driver.findElement(By.cssSelector("button#btnNextNoStores")).click();
	    
	    //Create an account - Step 3
	    driver.findElement(By.id("address1")).clear();
	    driver.findElement(By.id("address1")).sendKeys("6600 Rockledge Dr.");
	    driver.findElement(By.id("address2")).clear();
	    driver.findElement(By.id("address2")).sendKeys("Suite 210");
	    driver.findElement(By.id("city")).clear();
	    driver.findElement(By.id("city")).sendKeys("Bethesda");
	    Select stateOption = new Select(driver.findElement(By.cssSelector("select.createAccount-search-by-state")));
	    stateOption.selectByValue("US-MD");
	    driver.findElement(By.id("zipCode")).clear();
	    driver.findElement(By.id("zipCode")).sendKeys("20817");
	    driver.findElement(By.cssSelector("input#ageCheck")).click();
	    driver.findElement(By.cssSelector("input#termsAndCondCheck")).click();
	    driver.findElement(By.cssSelector("button#btnRegNextstep3")).click();
	    Thread.sleep(6000);
	    
	    
	    //Confirmation Screen
	    Assert.assertEquals(driver.findElements(By.cssSelector("a.btn-continue.clearfix")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.id("c0010")).isEmpty(),false); //Beer
	    Assert.assertEquals(driver.findElements(By.id("c0020")).isEmpty(),false); //Wine
	    Assert.assertEquals(driver.findElements(By.id("c0030")).isEmpty(),false); //Spirits
	    Assert.assertEquals(driver.findElements(By.id("c0050")).isEmpty(),false); //Accessories
	    Assert.assertEquals(driver.findElements(By.cssSelector("select#birthMonth")).isEmpty(),false); //Birth Month
	    Assert.assertEquals(driver.findElements(By.cssSelector("select#birthDay")).isEmpty(),false); //Birth Day
	    driver.findElement(By.id("btnSaveAccount")).click();
	    Thread.sleep(3000);
	    
	    //Account homepage (same as desktop)
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.ahp-heading")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.linkText("Your Account")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.linkText("Orders")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.linkText("Your shopping lists")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("span.rewards-title")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("a[class=analyticsUpdateAcc]")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.xpath("(//a[contains(text(),'Learn More')])[4]")).isEmpty(),false);
	    //Assert.assertEquals(driver.findElements(By.xpath("//div[3]/span[3]")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.linkText("Online order history")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("a[class=analyticsPrefStore]")).isEmpty(),false);
	    
	    //Logout
	    driver.findElement(By.linkText("Welcome, Automated")).click();
	    driver.findElement(By.linkText("Log out")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	    
	    //Relogin using the credentials above
	    driver.findElement(By.xpath("//a[contains(@href,'.totalwine.com/my-account')]")).click(); //Click "My Account"
		Thread.sleep(3000);
		driver.findElement(By.id("j_usernameforLogin")).sendKeys(email);
		driver.findElement(By.id("j_passwordforLogin")).sendKeys("grapes123!");
		driver.findElement(By.id("btnAccLoginfrmLogin")).click();
		Thread.sleep(3000);
		
		 //Account homepage (same as desktop)
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.ahp-heading")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.linkText("Your Account")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.linkText("Orders")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.linkText("Your shopping lists")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("span.rewards-title")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("a[class=analyticsUpdateAcc]")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.xpath("(//a[contains(text(),'Learn More')])[4]")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.linkText("Online order history")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("a[class=analyticsPrefStore]")).isEmpty(),false);
		
	    //Logout
	    driver.findElement(By.linkText("Welcome, Automated")).click();
	    driver.findElement(By.linkText("Log out")).click();
	}
}
