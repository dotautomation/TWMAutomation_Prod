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

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class Sort extends Browser {

	private String IP="71.193.51.0";
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();	
	}  
	
	@Test 
	public void SortTest () throws InterruptedException, BiffException, IOException {
		logger=report.startTest("PLP Sort Test");
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	    driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
	    Thread.sleep(5000);
		
		//Hover over the "Wine" top-level menu
		Actions action = new Actions(driver);
		WebElement wineNav = driver.findElement(By.xpath("//a[contains(@href,'/c0020')]")); 
		action.moveToElement(wineNav).build().perform(); //Top Level Menu Hover
		WebElement winePLPNav=driver.findElement(By.xpath("//a[contains(@href,'/white-wine/')]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", winePLPNav);
		Thread.sleep(5000);
		
		//driver.findElement(By.xpath("//a[contains(@href,'/white-wine/c/000002?viewall=true')]")).click(); //For production since the SubCat Land page is setup
		//Thread.sleep(5000);
		
		WebElement wineMove = driver.findElement(By.cssSelector("ul.header-classes")); //Moving the mouse away from the top level menu 
		action.moveToElement(wineMove).build().perform(); 
		
		
		//driver.get(ConfigurationFunctions.accessURL+"/white-wine/c/013005");
		Thread.sleep(5000);
	    driver.findElement(By.xpath("//div[2]/div/span/span")).click();
	    driver.findElement(By.xpath("//div[2]/div/div[2]/div/div/div/div/ul/li[2]")).click();
	    Assert.assertEquals(driver.findElements(By.cssSelector("option[value=our-favorites]")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div[class=plp-list-img-wdlogo]")).isEmpty(),false);
	    driver.findElement(By.xpath("//div[2]/div/span/span")).click();
	    driver.findElement(By.xpath("//div[2]/div/div[2]/div/div/div/div/ul/li[3]")).click();
	    Assert.assertEquals(driver.findElements(By.cssSelector("span.plp-product-desc-winespec-left-badge")).isEmpty(),false);
	    
	    driver.findElement(By.xpath("//div[2]/div/span/span")).click();
	    
	    WebElement scroll = driver.findElement(By.xpath("//div[2]/div/span/span"));

	    //do 
	    	scroll.sendKeys(Keys.ARROW_DOWN);
	    //while (driver.findElements(By.xpath("//div[2]/div/div[2]/div/div/div/div/ul/li[7]")).isEmpty()==false);
	    scroll.sendKeys(Keys.ARROW_DOWN);
	    scroll.sendKeys(Keys.ARROW_DOWN);
	    scroll.sendKeys(Keys.ARROW_DOWN);
	    scroll.sendKeys(Keys.ARROW_DOWN);
	    
	    driver.findElement(By.xpath("//div[2]/div/div[2]/div/div/div/div/ul/li[7]")).click();
	    Thread.sleep(3000);
	    String ProductNameAlphaSort = driver.findElement(By.cssSelector("a[class=analyticsProductName]")).getText();
	    driver.navigate().refresh();
	    driver.findElement(By.xpath("//div[2]/div/span/span")).click();
	    
	    //do 
	    WebElement scroll1 = driver.findElement(By.xpath("//div[2]/div/span/span"));
	    	scroll1.sendKeys(Keys.ARROW_DOWN);
	    //while (driver.findElements(By.xpath("//div[2]/div/div[2]/div/div/div/div/ul/li[8]")).isEmpty()==false);
	    	scroll1.sendKeys(Keys.ARROW_DOWN);
		    scroll1.sendKeys(Keys.ARROW_DOWN);
		    scroll1.sendKeys(Keys.ARROW_DOWN);
		    scroll1.sendKeys(Keys.ARROW_DOWN);
	    
	    driver.findElement(By.xpath("//div[2]/div/div[2]/div/div/div/div/ul/li[8]")).click();
	    Thread.sleep(3000);
	    String ProductNameReverseAlphaSort = driver.findElement(By.cssSelector("a[class=analyticsProductName]")).getText();
	    
	    Assert.assertEquals(ProductNameAlphaSort.startsWith("1"),true);
	    Assert.assertEquals(ProductNameReverseAlphaSort.startsWith("Z"),true);
	}
}
