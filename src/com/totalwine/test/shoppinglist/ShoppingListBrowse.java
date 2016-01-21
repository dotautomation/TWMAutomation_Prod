package com.totalwine.test.shoppinglist;

/*
 * Shopping List (Browse)
 * Workflow:
 * 	1. Access a shopping list for a registered user
 * 	2. Validate the presence of items in the shopping list 
 * 	3. Click through an item from the shopping list and validate the appearance of the PDP
 *  4. Log out of the registered account
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

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class ShoppingListBrowse extends Browser {
	
	public String IP = "71.193.51.0";
	//public WebDriver driver;
	//ProfilesIni profile = new ProfilesIni();
	//FirefoxProfile testProfile = profile.getProfile("WebDriver");

	@BeforeMethod
	public void setUp() throws Exception {
		//this.driver = ConfigurationFunctions.driver;
		//driver = new FirefoxDriver(testProfile);
		driver.manage().window().maximize();
	  } 

	@Test
	public void ShoppingListBrowseTest () throws InterruptedException, BiffException, IOException {
		
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	    driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
	    Thread.sleep(5000);
	    
	    driver.findElement(By.linkText("Shopping List")).click();
	    Thread.sleep(5000);

	    //Login to retrieve Shopping List
	    driver.switchTo().frame("iframe-signin-overlay");
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("rsud@live.com");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("yoyo55");
	    driver.findElement(By.xpath("//button[@type='button']")).click();
	    Thread.sleep(6000);

	    //Verify Page Elements on Shopping List 
        //WebElement webelement1= driver.switchTo().activeElement();
	    //webelement1.click();
	    
	    Assert.assertEquals(driver.findElements(By.cssSelector("a.plp-product-title")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("span.an_ListName")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.linkText("Store")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.linkText("Department")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.linkText("Availability")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.linkText("Email")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.linkText("Print")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.linkText("Delete")).isEmpty(),false);

	    //Navigate to PDP from Shopping List
	    driver.findElement(By.cssSelector("a.plp-product-title")).click();
	    Thread.sleep(3000);
	    Assert.assertEquals(driver.findElements(By.cssSelector("section.pdp-wrapper")).isEmpty(),false);
	    
	    //Validate Login and then Log out
	    Assert.assertEquals(driver.findElements(By.linkText("Welcome, Rajat")).isEmpty(),false);
	    driver.findElement(By.linkText("Welcome, Rajat")).click();
	    driver.findElement(By.linkText("Log out")).click();
	    Thread.sleep(5000);
	    Assert.assertEquals(driver.findElements(By.linkText("Account")).isEmpty(),false);
	}
	
	/*@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException { 
		if(testResult.getStatus() == ITestResult.FAILURE) { 
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("c:\\totalwine\\TWMAutomation\\FailureScreenshots\\FAIL "+testResult.getName()+"  "+ConfigurationFunctions.now()+".png")); 
		}
		driver.close();
	}*/
}
