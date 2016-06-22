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

import java.io.File;
import java.io.IOException;
import jxl.read.biff.BiffException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class ShoppingListBrowse extends Browser {
	
	public String IP = "71.193.51.0";

	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	  } 

	@Test
	public void ShoppingListBrowseTest () throws InterruptedException, BiffException, IOException {
		logger=report.startTest("Shopping List Browse Test");
		SiteAccess.ActionAccessSite(driver, IP);
	    
	    driver.findElement(By.linkText("Shopping List")).click();
	    Thread.sleep(7000);

	    //Login to retrieve Shopping List
	    driver.switchTo().frame(0);
        WebElement webelement= driver.switchTo().activeElement();
	    webelement.click();
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("mhossain@totalwine.com");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("grapes123");
	    driver.findElement(By.xpath("//button[@type='button']")).click();
	    Thread.sleep(7000);

	    //Check for the merge cart modal
	    if (driver.findElements(By.cssSelector("button.btn.btn-red.cartMergeBtn")).size()!=0)
	    	driver.findElement(By.cssSelector("button.btn.btn-red.cartMergeBtn")).click();
	    Thread.sleep(3000);
	    
	    //Verify Page Elements on Shopping List 
	    
	    //Check for survey popup
	    if (driver.findElements(By.xpath("//img[contains(@src,'https://qdistribution.qualtrics.com/WRQualtricsShared/Graphics//black_popup_x.png')]")).size()!=0)
	    	driver.findElement(By.xpath("//img[contains(@src,'https://qdistribution.qualtrics.com/WRQualtricsShared/Graphics//black_popup_x.png')]")).click();
	    Thread.sleep(2000);
	    
//	    Assert.assertEquals(driver.findElements(By.cssSelector("a.plp-product-title")).isEmpty(),false);
//	    Assert.assertEquals(driver.findElements(By.cssSelector("span.an_ListName")).isEmpty(),false);
//	    Assert.assertEquals(driver.findElements(By.linkText("Store")).isEmpty(),false);
//	    Assert.assertEquals(driver.findElements(By.linkText("Department")).isEmpty(),false);
//	    Assert.assertEquals(driver.findElements(By.linkText("Availability")).isEmpty(),false);
//	    Assert.assertEquals(driver.findElements(By.linkText("Email")).isEmpty(),false);
//	    Assert.assertEquals(driver.findElements(By.linkText("Print")).isEmpty(),false);
//	    Assert.assertEquals(driver.findElements(By.linkText("Delete")).isEmpty(),false);

	    //Navigate to PDP from Shopping List
	    driver.findElement(By.cssSelector("a.plp-product-title")).click();
	    Assert.assertEquals(driver.findElements(By.cssSelector("section.pdp-wrapper")).isEmpty(),false);
	    
	    //Validate Login and then Log out
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.header-wrapper > ul:nth-child(2) > li.loggedin-user > a > span.list-text")).isEmpty(),false);
	    driver.findElement(By.cssSelector(".fluid-sign-in-logo.fluid-icons")).click();
	    
	    JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js1.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".analyticsHeaderLink[href='http://uat.totalwine.com/logout']")));        
	    Thread.sleep(5000);
	    Assert.assertEquals(driver.findElements(By.linkText("Sign In/Register")).isEmpty(),false);
	}
	
	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException { 
		if(testResult.getStatus() == ITestResult.FAILURE) { 
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("c:\\totalwine\\TWMAutomation\\FailureScreenshots\\FAIL "+testResult.getName()+"  "+ConfigurationFunctions.now()+".png")); 
		}
		driver.close();
	}
}