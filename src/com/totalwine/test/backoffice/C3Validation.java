package com.totalwine.test.backoffice;

/*
 * OMS Login Workflow
 * Workflow:
 * 1. Access the backoffice
 * 2. Login using valid credentials
 * 3. Validate the menus displayed upon a successful login
 *		
 * Technical Modules:
 * 	1. BeforeMethod (Test Pre-requisites):
 * 			Invoke webdriver
 * 			Maximize browser window
 * 	2. Test (Workflow)
 * 	3. AfterMethod
 * 			Take screenshot
 * 			Close webdriver
 */

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class C3Validation extends Browser {
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	  }  
	
	@Test
	public void C3LoginTest () throws InterruptedException {
		logger=report.startTest("CS Cockpit Login Test");
		driver.get(ConfigurationFunctions.backofficeURL+"/cscockpit");
		Thread.sleep(5000);
		C3Login();
		
		//Validate Links in left menu
		Assert.assertEquals(driver.findElements(By.linkText("Find Customer")).isEmpty(), false);
		Assert.assertEquals(driver.findElements(By.linkText("New Customer")).isEmpty(), false);
		Assert.assertEquals(driver.findElements(By.linkText("Email Signup")).isEmpty(), false);
		Assert.assertEquals(driver.findElements(By.linkText("Find Order")).isEmpty(), false);
		Assert.assertEquals(driver.findElements(By.linkText("Find Ticket")).isEmpty(), false);
		Assert.assertEquals(driver.findElements(By.linkText("New Ticket")).isEmpty(), false);
		
		//Find a customer
		driver.findElement(By.linkText("Find Customer")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(@class, 'csSearchPane')]/input[1]")).clear();
		driver.findElement(By.xpath("//div[contains(@class, 'csSearchPane')]/input[1]")).sendKeys("Rajat");
		driver.findElement(By.xpath("//div[contains(@class, 'csSearchPane')]/input[2]")).clear();
		driver.findElement(By.xpath("//div[contains(@class, 'csSearchPane')]/input[2]")).sendKeys("Sud");
		driver.findElement(By.xpath("//td[text()[contains(.,'Search')]]")).click();
		Thread.sleep(10000);
		
		//Validate Customer search results
		Assert.assertEquals(driver.findElements(By.xpath("//div[text()[contains(.,'WebMember')]]")).isEmpty(), false);
		driver.findElement(By.xpath("//td[text()[contains(.,'Select')]]")).click();
		Thread.sleep(3000);
		Assert.assertEquals(driver.findElements(By.xpath("//span[text()[contains(.,'Customer Email ID')]]")).isEmpty(), false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div.ahp-rewardsClub")).isEmpty(),false);
		
		//Find an order
		driver.findElement(By.linkText("Find Order")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(@class, 'csSearchPane')]/input[1]")).clear();
		driver.findElement(By.xpath("//div[contains(@class, 'csSearchPane')]/input[1]")).sendKeys("28122029");
		driver.findElement(By.xpath("//td[text()[contains(.,'Search')]]")).click();
		Thread.sleep(10000);
		
		//Validate Order search results
		Assert.assertEquals(driver.findElements(By.xpath("//div[text()[contains(.,'Cancelled')]]")).isEmpty(), false);
		driver.findElement(By.xpath("//td[text()[contains(.,'Select')]]")).click();
		Thread.sleep(3000);
		Assert.assertEquals(driver.findElements(By.xpath("//div[text()[contains(.,'AUTHORIZATION')]]")).isEmpty(), false);
		Assert.assertEquals(driver.findElements(By.cssSelector("img.listViewCellImage")).isEmpty(),false);
		
		//Logout
		driver.findElement(By.xpath("//span[text()[contains(.,'Menu')]]")).click();
		driver.findElement(By.xpath("//a[text()[contains(.,'Logout')]]")).click();
	}
	
	public void C3Login() throws InterruptedException {
		//C3 Login
		driver.findElement(By.cssSelector("input[name=j_username]")).clear();
		driver.findElement(By.cssSelector("input[name=j_username]")).sendKeys("rsud");;
		driver.findElement(By.cssSelector("input[name=j_password]")).clear();
		driver.findElement(By.cssSelector("input[name=j_password]")).sendKeys(ConfigurationFunctions.TESTPWD);
		driver.findElement(By.cssSelector("td.z-button-cm")).click();
		Thread.sleep(3000);
	}
}
