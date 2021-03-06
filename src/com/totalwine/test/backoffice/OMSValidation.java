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

public class OMSValidation extends Browser {
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	  }  
	
	@Test
	public void OMSLoginTest () throws InterruptedException {
		logger=report.startTest("OMS Login Test");
		driver.get(ConfigurationFunctions.backofficeURL+"/backoffice");
		PageLoad(driver);
		Thread.sleep(7000);
		OMSLogin();
		
		//OMS Authority Groups Validation
	    sAssert.assertEquals(driver.findElements(By.xpath("//*[text()[contains(.,'inventorymanager')]]")).isEmpty(),false);
	    sAssert.assertEquals(driver.findElements(By.xpath("//*[text()[contains(.,'storeuser')]]")).isEmpty(),false);
	    sAssert.assertEquals(driver.findElements(By.xpath("//*[text()[contains(.,'omsadmin')]]")).isEmpty(),false);
	    System.out.println("Validated the OMS Authority Groups");
	    
	    //Inventory Manager Validation
	    driver.findElement(By.xpath("//*[text()[contains(.,'inventorymanager')]]")).click();
		PageLoad(driver);
		Thread.sleep(7000);
	    driver.findElement(By.cssSelector("button[ytestid=selectorButton]")).click();
		PageLoad(driver);
		Thread.sleep(7000);
//	    Assert.assertEquals(driver.findElements(By.xpath("//*[text()[contains(.,'Inventory')]]")).isEmpty(),false);
//	    Assert.assertEquals(driver.findElements(By.xpath("//*[text()[contains(.,'Search by SKU')]]")).isEmpty(),false);
//	    Assert.assertEquals(driver.findElements(By.xpath("//*[text()[contains(.,'Search by Location')]]")).isEmpty(),false);
	    sAssert.assertEquals(driver.findElements(By.xpath("//*[text()[contains(.,'Stockroom Locations')]]")).isEmpty(),false);
	    sAssert.assertEquals(driver.findElements(By.xpath("//*[text()[contains(.,'Imports')]]")).isEmpty(),false);
	    sAssert.assertEquals(driver.findElements(By.cssSelector("input.yw-search-box.z-textbox")).isEmpty(),false);
	    OMSLogout();
		PageLoad(driver);
		Thread.sleep(7000);
	    System.out.println("Validated the apperance of the Inventory Manager menu items");
	    
	    //Store User Validation
	    OMSLogin();
		PageLoad(driver);
		Thread.sleep(7000);
	    driver.findElement(By.xpath("//*[text()[contains(.,'storeuser')]]")).click();
		PageLoad(driver);
		Thread.sleep(7000);
	    driver.findElement(By.cssSelector("button[ytestid=selectorButton]")).click();
		PageLoad(driver);
		Thread.sleep(7000);
	    Assert.assertEquals(driver.findElements(By.cssSelector("span[ytestid=\"node.id.oms.orders\"]")).isEmpty(),false);
	    driver.findElement(By.cssSelector("span[ytestid=\"node.id.oms.orders\"]")).click();
		PageLoad(driver);
		Thread.sleep(7000);
	    sAssert.assertEquals(driver.findElements(By.xpath("//*[text()[contains(.,'To Be Packed')]]")).isEmpty(),false);
	    sAssert.assertEquals(driver.findElements(By.xpath("//*[text()[contains(.,'To Be Shipped')]]")).isEmpty(),false);
	    sAssert.assertEquals(driver.findElements(By.xpath("//*[text()[contains(.,'Ready for Pickup')]]")).isEmpty(),false);
	    sAssert.assertEquals(driver.findElements(By.xpath("//*[text()[contains(.,'Past/Almost Due')]]")).isEmpty(),false);
	    sAssert.assertEquals(driver.findElements(By.xpath("//*[text()[contains(.,'All Orders')]]")).isEmpty(),false);
	    driver.findElement(By.xpath("//*[text()[contains(.,'To Be Packed')]]")).click();
		PageLoad(driver);
		Thread.sleep(7000);
	    sAssert.assertEquals(driver.findElements(By.cssSelector("div.z-listcell-cnt")).isEmpty(),false); //to-be-packed table is populated
	    OMSLogout();
		PageLoad(driver);
		Thread.sleep(7000);
	    System.out.println("Validated the Store User interface's menu items");
	    
	    //OMS Admin Validation
	    OMSLogin();
		PageLoad(driver);
		Thread.sleep(7000);
	    driver.findElement(By.xpath("//*[text()[contains(.,'omsadmin')]]")).click();
		PageLoad(driver);
		Thread.sleep(7000);
	    driver.findElement(By.cssSelector("button[ytestid=selectorButton]")).click();
		PageLoad(driver);
		Thread.sleep(7000);
	    sAssert.assertEquals(driver.findElements(By.cssSelector("span[ytestid=\"node.id.oms.orders\"]")).isEmpty(),false);
	    OMSLogout();
		PageLoad(driver);
		Thread.sleep(7000);
	    System.out.println("Validated the OMS Admin's menu items");
	}
	public void OMSLogin() throws InterruptedException {
		//OMS Login
		driver.findElement(By.cssSelector("input.login.z-textbox[name=j_username]")).clear();
		driver.findElement(By.cssSelector("input.login.z-textbox[name=j_username]")).sendKeys("rsud");
		driver.findElement(By.cssSelector("input.login.z-textbox[name=j_password]")).clear();
		driver.findElement(By.cssSelector("input.login.z-textbox[name=j_password]")).sendKeys("yoyo55");
		driver.findElement(By.id("lgBtn")).click();
		PageLoad(driver);
		Thread.sleep(7000);
	}
	public void OMSLogout() throws InterruptedException {
//		driver.findElement(By.cssSelector("div.yw-statusToolbar > div > div > div > div.z-toolbarbutton-cnt")).click();
		driver.findElement(By.xpath(".//*[@id='jBuPb']/div/div/img")).click();
		PageLoad(driver);
		Thread.sleep(7000);
	}
}