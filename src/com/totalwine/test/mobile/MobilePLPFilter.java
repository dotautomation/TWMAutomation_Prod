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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class MobilePLPFilter extends Browser {

	private String IP="71.193.51.0";

	@Test 
	public void MobileFilterTest () throws InterruptedException, BiffException, IOException {
		
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
		
		//Varietal
		driver.findElement(By.id("check_box_showmoreChardonnayvarietaltype")).click();
	    Thread.sleep(3000);
	    String facetValue = driver.findElement(By.cssSelector("span.filter-value")).getText();
	    String varietalType = driver.findElement(By.cssSelector("a[class=analyticsProductName]")).getText();
	    System.out.println(facetValue);
	    System.out.println(varietalType);
	    	Assert.assertTrue(varietalType.contains(facetValue));
	    driver.findElement(By.cssSelector("span.desc-text.clear-all")).click();
	    	Thread.sleep(3000);
	    	
	    //Price Range
	    //WebElement scroll = driver.findElement(By.linkText("Price Range"));
	    WebElement scroll = driver.findElement(By.linkText("plppricevalue"));
	 	scroll.sendKeys(Keys.PAGE_DOWN);
	 	//driver.findElement(By.xpath("//a[contains(text(),'Price Range')]")).click();
	 	driver.findElement(By.xpath("//a[contains(text(),'plppricevalue')]")).click();
	    driver.findElement(By.id("check_box_showmoreUp to $10plppricevalue")).click();
	    facetValue = driver.findElement(By.cssSelector("span.filter-value")).getText();
	    String priceValue = driver.findElement(By.cssSelector("span.price")).getText();
	    	System.out.println("Price Range: "+facetValue+"|"+priceValue);
	    driver.findElement(By.cssSelector("span.desc-text.clear-all")).click();
	    	 
	    //Rating Source
	    //scroll = driver.findElement(By.linkText("Rating Source"));
	    scroll = driver.findElement(By.linkText("ratingsource"));
	 	scroll.sendKeys(Keys.PAGE_DOWN);
	    //driver.findElement(By.xpath("//a[contains(text(),'Rating Source')]")).click();
	    driver.findElement(By.xpath("//a[contains(text(),'ratingsource')]")).click();
	    driver.findElement(By.id("check_box_showmoreWine Spectatorratingsource")).click();
	    //Assert.assertEquals(driver.findElements(By.linkText("Sign Into Your Account")).isEmpty(),false);
	    facetValue = driver.findElement(By.cssSelector("span.filter-value")).getText();
	    Assert.assertEquals(facetValue, driver.findElement(By.cssSelector("span.plp-product-desc-winespec-desc-title")).getText());
	    driver.findElement(By.cssSelector("span.desc-text.clear-all")).click();
	    
	    //Rating Range
	    //scroll = driver.findElement(By.linkText("Rating Range"));
	    scroll = driver.findElement(By.linkText("Expert Rating"));
	 	scroll.sendKeys(Keys.PAGE_DOWN);
	    //driver.findElement(By.xpath("//a[contains(text(),'Rating Range')]")).click();
	 	driver.findElement(By.xpath("//a[contains(text(),'Expert Rating')]")).click();
	    driver.findElement(By.id("check_box_showmore89 and Belowratingrange")).click();
	    facetValue = driver.findElement(By.cssSelector("span.filter-value")).getText();
	    String ratingRange = driver.findElement(By.cssSelector("span.plp-product-desc-winespec-left-badge")).getText();
	    	System.out.println("Rating Range: "+facetValue+"|"+ratingRange);
	    driver.findElement(By.cssSelector("span.desc-text.clear-all")).click();
	    
	    //Country
	    //scroll = driver.findElement(By.linkText("Country"));
	    scroll = driver.findElement(By.linkText("state"));
	 	//scroll.sendKeys(Keys.PAGE_DOWN);
	    //driver.findElement(By.xpath("(//a[contains(text(),'Country')])[2]")).click();
	 	//driver.findElement(By.xpath("(//a[contains(text(),'state')])")).click();
	    //driver.findElement(By.id("check_box_showmoreArgentinastate")).click();
	    //driver.findElement(By.id("check_box_showmoreArgentinabeerspiritscountrystate")).click();
	    driver.findElement(By.id("check_box_showmoreCaliforniastate")).click();
	    facetValue = driver.findElement(By.cssSelector("span.filter-value")).getText();
	    Assert.assertEquals(facetValue, driver.findElement(By.cssSelector("a.analyticsCountryState")).getText());
	    driver.findElement(By.cssSelector("span.desc-text.clear-all")).click();
	    
	    //Appellation
	    //scroll = driver.findElement(By.linkText("Appellation"));
	    scroll = driver.findElement(By.linkText("appellation"));
	 	scroll.sendKeys(Keys.PAGE_DOWN);
	    //driver.findElement(By.linkText("Appellation")).click();
	    //driver.findElement(By.xpath("//a[contains(text(),'Appellation')]")).click();
	    driver.findElement(By.xpath("//a[contains(text(),'appellation')]")).click();
	    driver.findElement(By.id("check_box_showmoreRussian River Valleyappellation")).click();
	    facetValue = driver.findElement(By.cssSelector("span.filter-value")).getText();
	    Assert.assertEquals(facetValue, driver.findElement(By.xpath("(//a[contains(text(),'Russian River Valley')])[2]")).getText());
	    driver.findElement(By.cssSelector("span.desc-text.clear-all")).click();
	}
}
