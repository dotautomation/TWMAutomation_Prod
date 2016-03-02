package com.totalwine.test.mobile;

/*
 * PLP Filter Workflow
 * Workflow:
 * 	1. Access the PLP for Wine > White Wine
 * 	2. Select the facet filters and validate the following elements associated with each selection: 
 * 	  a. Price Range: Validate that the price on the top PLP tile is within the price range selection
 *    b. Rating Source: Validate that the first PLP tile depicts the rating source selected
 *    c. Rating Range: Validate that the rating badge is present on the first PLP tile
 *    d. Country: Validate that the country selected is displayed as an attribute on the first PLP tile
 *    e. Appelation: Validate that the selected appelation is listed on the first PLP tile
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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.pages.PageHomepage;
import com.totalwine.test.pages.PageProductList;
import com.totalwine.test.trials.Browser;

public class MobilePLPSort extends Browser {

	//private String IP="71.193.51.0";

	@Test 
	public void MobileFilterTest () throws InterruptedException, BiffException, IOException {
		logger=report.startTest("Mobile PLP Sort Test");
		SiteAccess.ActionAccessMobileSite(driver, "71.193.51.0");
		
		//Access Mobile PLP
		driver.findElement(PageHomepage.MobileWineButton).click();
		Thread.sleep(3000);
		
		//Select SortOption = new Select(driver.findElement(PageProductList.MobilePLPSort));
		
		//Verify default sort "Most Popular"
		Assert.assertEquals(driver.findElement(By.cssSelector("select#sortOptions > option[selected=selected]")).getText().replaceAll("^\\s+", ""), "Most Popular","Most Popular wasn't the default selected option");
		Assert.assertEquals(driver.findElements(By.cssSelector("a.analyticsProductName[href*=kendall-jackson-chardonnay]")).isEmpty(), false); //Popular item is present
		
		//Verify "Our Favorites" sort
		driver.findElement(PageProductList.MobilePLPSort).click();
	    //SortOption.selectByVisibleText("Our Favorites");
	    driver.findElement(By.cssSelector("option[value=\"our-favorites\"]")).click();
	    Thread.sleep(3000);
	    for (int i=1;i<=10;i++) {
	    	Assert.assertEquals(driver.findElements(By.cssSelector("ul.plp-list > li:nth-child("+i+") > div > div > div.plp-list-img-wdlogo")).isEmpty(),false); //Top 10 results are WD
	    }
		
		//Verify "Expert Ratings" sort
	    driver.findElement(PageProductList.MobilePLPSort).click();
	    //SortOption.selectByVisibleText("Expert Ratings");
	    driver.findElement(By.cssSelector("option[value=\"expert-ratings\"]")).click();
	    Thread.sleep(3000);
	    Assert.assertEquals(driver.findElement(By.cssSelector("ul.plp-list > li:nth-child(1) > div > div > span.plp-list-img-wineSpec-badge > span")).getText(), "100"); //First result is 100 rated
	    Assert.assertEquals(driver.findElements(By.cssSelector("ul.plp-list > li:nth-child(1) > div > div > span.plp-list-img-wineSpec-text")).isEmpty(), false);
	    
		//Verify "Customer Ratings" sort
	    driver.findElement(PageProductList.MobilePLPSort).click();
	    //SortOption.selectByVisibleText("Customer Ratings");
	    driver.findElement(By.cssSelector("option[value=\"customer-ratings\"]")).click();
	    Thread.sleep(3000);
	    Assert.assertEquals(driver.findElements(By.cssSelector("ul.plp-list > li:nth-child(1) > div > div.pdpRatingStars > a > span > span[style=\"width:100.0%\"]")).isEmpty(),false); //First result is 5-star
	    
	    
		//Verify "Price (highest first)" sort
	    driver.findElement(PageProductList.MobilePLPSort).click();
	    //SortOption.selectByVisibleText("Price (highest first)");
	    driver.findElement(By.cssSelector("option[value=\"price-desc\"]")).click();
	    Thread.sleep(3000);
	    float TopPrice = Integer.parseInt(driver.findElement(By.cssSelector("ul.plp-list > li:nth-child(1) > div > div.plp-product-price >ul > li > span.price")).getText().replaceAll("[^\\d.]+", ""));
	    float SecondPrice = Integer.parseInt(driver.findElement(By.cssSelector("ul.plp-list > li:nth-child(2) > div > div.plp-product-price >ul > li > span.price")).getText().replaceAll("[^\\d.]+", ""));
	    Assert.assertTrue(TopPrice>SecondPrice);
	    
	    
		//Verify "Price (lowest first)" sort
	    driver.findElement(PageProductList.MobilePLPSort).click();
	    //SortOption.selectByVisibleText("Price (lowest first)");
	    driver.findElement(By.cssSelector("option[value=\"price-asc\"]")).click();
	    Thread.sleep(3000);
	    TopPrice = Integer.parseInt(driver.findElement(By.cssSelector("ul.plp-list > li:nth-child(1) > div > div.plp-product-price >ul > li > span.price")).getText().replaceAll("[^\\d.]+", ""));
	    SecondPrice = Integer.parseInt(driver.findElement(By.cssSelector("ul.plp-list > li:nth-child(2) > div > div.plp-product-price >ul > li > span.price")).getText().replaceAll("[^\\d.]+", ""));
	    Assert.assertTrue(TopPrice<SecondPrice);
	    
		//Verify "Name (A-Z)" sort
	    driver.findElement(PageProductList.MobilePLPSort).click();
	    //SortOption.selectByVisibleText("Name (A-Z)");
	    driver.findElement(By.cssSelector("option[value=\"name-asc\"]")).click();
	    Thread.sleep(3000);
	    Assert.assertTrue(driver.findElement(By.cssSelector("ul.plp-list > li:nth-child(1) > div > h2 > a.analyticsProductName")).getText().startsWith("1"));
	    
		//Verify "Name (Z-A)" sort
	    driver.findElement(PageProductList.MobilePLPSort).click();
	    //SortOption.selectByVisibleText("Name (Z-A)");
	    driver.findElement(By.cssSelector("option[value=\"name-desc\"]")).click();
	    Thread.sleep(3000);
	    Assert.assertTrue(driver.findElement(By.cssSelector("ul.plp-list > li:nth-child(1) > div > h2 > a.analyticsProductName")).getText().startsWith("Z"));
	}
}
