package com.totalwine.test.productlist;

/*
 * PLP Sort Workflow
 * Workflow:
 * 	1. Access the PLP for Wine > White Wine
 * 	2. Sort the PLP with the following selections and validate the results: 
 * 	  a. Our Favorites: Validate that the PLP item tile has a WD badge
 *    b. Name (ascending): Validate that the first PLP tile starts with a "1"
 *    c. Name (descending): Validate that the first PLP tile starts with a "Z"
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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.totalwine.test.pages.PageProductList;
import com.totalwine.test.trials.Browser;
import com.totalwine.test.actions.*;
public class Sort extends Browser {

	private String IP="71.193.51.0";
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();	
	}  
	
	@Test 
	public void SortTest () throws InterruptedException, BiffException, IOException {
		logger=report.startTest("PLP Sort Test");
		SiteAccess.ActionAccessSite(driver,IP);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		//Hover over the "Wine" top-level menu
		Actions action = new Actions(driver);
		WebElement wineNav = driver.findElement(By.xpath("//a[contains(@href,'/c0020')]")); 
		action.moveToElement(wineNav).build().perform(); //Top Level Menu Hover
		WebElement winePLPNav=driver.findElement(By.xpath("//a[contains(@href,'/white-wine/')]"));
		js.executeScript("arguments[0].click();", winePLPNav);
		Thread.sleep(5000);
		WebElement wineMove = driver.findElement(By.cssSelector("ul.header-classes")); //Moving the mouse away from the top level menu 
		action.moveToElement(wineMove).build().perform(); 
		Thread.sleep(5000);
	    
		//Our Favorites
		driver.findElement(PageProductList.SortDropdown).click();
		Thread.sleep(3000);
//	    driver.findElement(By.cssSelector("li[data-val=our-favorites]")).click();
	    js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("li[data-val=our-favorites]")));
	    Thread.sleep(3000);
	    Assert.assertEquals(driver.findElements(By.cssSelector("option[value=our-favorites]")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.plp-list-img-wdlogo")).isEmpty(),false);
	    
	    //Expert Ratings
	    driver.findElement(PageProductList.SortDropdown).click();
	    Thread.sleep(3000);
//	    driver.findElement(By.cssSelector("li[data-val=expert-ratings]")).click();
	    js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("li[data-val=expert-ratings]")));
	    Thread.sleep(3000);
	    Assert.assertEquals(driver.findElements(By.cssSelector("span.plp-product-desc-winespec-left-badge")).isEmpty(),false);
	    
	    
	    //Name (A-Z)
	    driver.findElement(PageProductList.SortDropdown).click();
	    Thread.sleep(3000);
	    //driver.findElement(By.cssSelector("li[data-val=name-asc]")).click();
	    js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("li[data-val=name-asc]")));
	    Thread.sleep(3000);
	    String ProductNameAlphaSort = driver.findElement(By.cssSelector("a.analyticsProductName")).getText();
	    driver.navigate().refresh();
	    
	    //Name (Z-A)
	    driver.findElement(PageProductList.SortDropdown).click();
	    Thread.sleep(2000);
	    //driver.findElement(By.cssSelector("div.contSelect.jspScrollable")).sendKeys(Keys.ARROW_DOWN);
	    //driver.findElement(By.cssSelector("li[data-val=name-desc]")).click();
	    js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("li[data-val=name-desc]")));
	    Thread.sleep(3000);
	    String ProductNameReverseAlphaSort = driver.findElement(By.cssSelector("a.analyticsProductName")).getText();
	    
	    Assert.assertEquals(ProductNameAlphaSort.startsWith("1"),true);
	    Assert.assertEquals(ProductNameReverseAlphaSort.startsWith("Z"),true);
	}
}