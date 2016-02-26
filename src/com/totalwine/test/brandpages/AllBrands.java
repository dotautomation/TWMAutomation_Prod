package com.totalwine.test.brandpages;

/*
 * View all Brands Page Workflow
 * Workflow:
 * 1. Click on "View all Brands" link from global nav
 * 2. Validate the Wine Brands page
 * 	Look and Feel
 * 	All alpha
 * 3. Click on "Z"
 * 4. Validate that brands start with "Z" 
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.trials.Browser;

public class AllBrands extends Browser {

	private String IP="71.193.51.0";

	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	}  
	
	@Test 
	public void AllBrandsPageTest () throws InterruptedException, BiffException, IOException, AWTException {
		logger=report.startTest("View All Brands Page Test");
		SiteAccess.ActionAccessSite(driver, IP);
		
		//Click on "View all Brands" link from global nav
		WebElement topLevelMenuItem = driver.findElement(By.cssSelector("ul.nav > li.menu:nth-child(2) > a")); //Wine
		Actions action=new Actions(driver);
		action.moveToElement(topLevelMenuItem).build().perform(); //Hover over Wine
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("a[href*=\"totalwine.com/wine-brands\"]")).click();
		Thread.sleep(3000);
		logger.log(LogStatus.PASS, "Successfully navigated to All Brands page");
		
		//Validate the Wine Brands page
		//Look and Feel
		String [] alphaExpected = {"#","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"}; 
		int alphaCount = driver.findElements(By.cssSelector("span.word.anAlpha")).size();
		for (int i=0;i<alphaCount;i++) {
			Assert.assertEquals(driver.findElement(By.cssSelector("span.word.anAlpha:nth-child("+(i+1)+")")).getText(), alphaExpected[i]);//Validate all alpha
			logger.log(LogStatus.PASS, "Validated "+alphaExpected[i]);
		}
		//Click on "Z"
		driver.findElement(By.cssSelector("span#key_Z")).click();
		Thread.sleep(2000);
		
		//Validate that brands start with "Z" 
		int resultCount = driver.findElements(By.cssSelector("ul#showList > li > span")).size();
		for (int j=0;j>resultCount;j++) {
			Assert.assertEquals(driver.findElement(By.cssSelector("ul#showList > li > span:nth-child("+(j+1)+") > a")).getText().startsWith("Z"), "Z");
		}
	}
}
