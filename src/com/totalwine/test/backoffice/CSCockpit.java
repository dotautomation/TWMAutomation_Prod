package com.totalwine.test.backoffice;

/*
 * CS Cockpit Workflow
 * Workflow:
 * 	1. 
 *  2.
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

import java.awt.AWTException;
import java.io.IOException;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class CSCockpit extends Browser {

	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	}  
	
	@Test 
	public void CSCockpitTest () throws InterruptedException {
		
		//Access CS Cockpit
		driver.get(ConfigurationFunctions.csCockpitURL);
		Thread.sleep(5000);
		
		//Login using Production credentials
		driver.findElement(By.cssSelector("input[name=j_username]")).clear();
	    driver.findElement(By.cssSelector("input[name=j_username]")).sendKeys("rsud");
	    driver.findElement(By.cssSelector("input[name=j_password]")).clear();
	    driver.findElement(By.cssSelector("input[name=j_password]")).sendKeys("yoyo55");
	    driver.findElement(By.cssSelector("td.z-button-cm")).click();
	    Thread.sleep(5000);
	    
	    //Validate elements upon initial login
	    //Assert.assertEquals(driver.findElements(By.cssSelector("li[title=Ticket Pool]")).isEmpty(),false);
	    //Assert.assertEquals(driver.findElements(By.cssSelector("li[title=Cart]")).isEmpty(),false);
	    //Assert.assertEquals(driver.findElements(By.cssSelector("li[title=End Session]")).isEmpty(),false);
	   
	    
		//Search for Products
		
	    
		//Search for Events
		
		//Search for Orders
	    driver.findElement(By.linkText("Find Order")).click();

		//Search for Customers
	    driver.findElement(By.linkText("Find Customer")).click();

	}
}
