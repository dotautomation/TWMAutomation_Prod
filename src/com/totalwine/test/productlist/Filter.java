package com.totalwine.test.productlist;

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

import java.awt.AWTException;
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
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.trials.Browser;

public class Filter extends Browser {

	private String IP="71.193.51.0";

	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	}  
	
	@Test 
	public void FilterTest () throws InterruptedException, BiffException, IOException, AWTException {
		logger=report.startTest("PLP Filter Test");
		SiteAccess.ActionAccessSite(driver, IP);
	    Actions action=new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;

		//Browse to PLP
	    //Access Wine PLP
		WebElement wineNav = driver.findElement(By.xpath("//a[contains(@href,'/c/c0020')]")); 
		action.moveToElement(wineNav).build().perform(); //Top Level Menu Hover
		WebElement winePLPNav=driver.findElement(By.xpath("//a[contains(@href,'/wine/white-wine/c/')]"));
		js.executeScript("arguments[0].click();", winePLPNav);
		Thread.sleep(5000);
		WebElement wineMove = driver.findElement(By.cssSelector("ul.header-classes")); //Moving the mouse away from the top level menu 
		action.moveToElement(wineMove).build().perform(); 
		Thread.sleep(2000);
		
		//Varietal
		driver.findElement(By.linkText("Wine Varietal & Type")).click();
		driver.findElement(By.id("check_box_showmoreChardonnayvarietaltype")).click();
		Thread.sleep(3000);
	    String facetValue = driver.findElement(By.cssSelector("div.inner-items-wrapper > ul > li > a.filter-link > span.filter-value")).getText();
	    String varietalType = driver.findElement(By.xpath("(//a[contains(text(),'Chardonnay')])[2]")).getText();
	    System.out.println(facetValue);
	    System.out.println(varietalType);
	    Assert.assertTrue(varietalType.contains(facetValue));
	    driver.findElement(By.cssSelector("span.desc-text.clear-all")).click();
	    Thread.sleep(3000);
	    	
		//Country
	    WebElement scroll_Country = driver.findElement(By.linkText("Country/State"));
	    scroll_Country.sendKeys(Keys.ARROW_DOWN);
	    driver.findElement(By.linkText("Country/State")).click();
	    driver.findElement(By.id("check_box_showmoreCaliforniastate")).click();
	    Thread.sleep(3000);
	    facetValue = driver.findElement(By.cssSelector("span.filter-value")).getText();
	    Assert.assertEquals(facetValue, driver.findElement(By.cssSelector("a.analyticsCountryState")).getText());
	    driver.findElement(By.cssSelector("span.desc-text.clear-all")).click();
	    Thread.sleep(5000);
	    
	    //Price Range
	    WebElement wineMove1 = driver.findElement(By.cssSelector("ul.header-classes")); //Moving the mouse away from the top level menu 
		action.moveToElement(wineMove1).build().perform(); 
	    WebElement scroll_Price = driver.findElement(By.linkText("Price Range"));
	 	scroll_Price.sendKeys(Keys.ARROW_DOWN);
	 	Thread.sleep(5000);

	    JavascriptExecutor js7 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js7.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("section.wrapper > section.plp-leftnav-wrapper > aside > section > ul > li:nth-child(11) > a > em")));  
	    Thread.sleep(3000);	
	    JavascriptExecutor js4 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js4.executeScript("arguments[0].click();", driver.findElement(By.id("check_box_showmoreUp to $10plppricevalue")));  
	    Thread.sleep(3000);
	    facetValue = driver.findElement(By.cssSelector("span.filter-value")).getText();
	    String priceValue = driver.findElement(By.cssSelector("span.price")).getText();
	    System.out.println("Price Range: "+facetValue+"|"+priceValue);
	    driver.findElement(By.cssSelector("span.desc-text.clear-all")).click();
	    Thread.sleep(5000);
	    	 
	    //Rating Source
	    WebElement scroll_RatingSource = driver.findElement(By.linkText("Rating Source"));
	 	scroll_RatingSource.sendKeys(Keys.ARROW_DOWN);
	    driver.findElement(By.xpath("//a[contains(text(),'Rating Source')]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.id("check_box_showmoreAntonio Galloniratingsource")).click();
	    Thread.sleep(3000);
	    facetValue = driver.findElement(By.cssSelector("span.filter-value")).getText();
	    Assert.assertEquals(facetValue, driver.findElement(By.cssSelector("span.plp-product-desc-winespec-desc-title")).getText());
	    driver.findElement(By.cssSelector("span.desc-text.clear-all")).click();
	    Thread.sleep(5000);
	    
	    //Rating Range
	    WebElement scroll_RatingRange = driver.findElement(By.linkText("Top Rated"));
	 	scroll_RatingRange.sendKeys(Keys.ARROW_DOWN);
	 	scroll_RatingRange.sendKeys(Keys.ARROW_DOWN);
	 	driver.findElement(By.xpath("//a[contains(text(),'Top Rated')]")).click();
	    driver.findElement(By.id("check_box_showmore89 and Belowratingrange")).click();
	    Thread.sleep(3000);
	    facetValue = driver.findElement(By.cssSelector("span.filter-value")).getText();
	    String ratingRange = driver.findElement(By.cssSelector("span.plp-product-desc-winespec-left-badge")).getText();
	    System.out.println("Rating Range: "+facetValue+"|"+ratingRange);
	    driver.findElement(By.cssSelector("span.desc-text.clear-all")).click();
	    Thread.sleep(5000);
	    JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js1.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[contains(text(),'Appellation')]")));  
	    JavascriptExecutor js2 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js2.executeScript("arguments[0].click();", driver.findElement(By.id("check_box_showmoreRussian River Valleyappellation"))); 
	    Thread.sleep(3000);
	    facetValue = driver.findElement(By.cssSelector("span.filter-value")).getText();
	    Assert.assertTrue(driver.findElement(By.xpath("(//a[contains(text(),'Russian River Valley')])[2]")).getText().contains(facetValue));
	    driver.findElement(By.cssSelector("span.desc-text.clear-all")).click();
	    Thread.sleep(5000);
	}
}