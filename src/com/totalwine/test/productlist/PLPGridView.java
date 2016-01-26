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

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class PLPGridView extends Browser {

	private String IP="71.193.51.0";

	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	}  
	
	@Test 
	public void PLPGridTest () throws InterruptedException, BiffException, IOException, AWTException {
		
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	    driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
	    Thread.sleep(5000);
	    
	    //Navigate to a PLP
	    Actions action=new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
	    WebElement wineNav = driver.findElement(By.xpath("//a[contains(@href,'/c/c0020')]")); 
		action.moveToElement(wineNav).build().perform(); //Top Level Menu Hover
		WebElement winePLPNav=driver.findElement(By.xpath("//a[contains(@href,'/wine/red-wine/c/')]"));
		js.executeScript("arguments[0].click();", winePLPNav);
		Thread.sleep(5000);
		WebElement wineMove = driver.findElement(By.cssSelector("ul.header-classes")); //Moving the mouse away from the top level menu 
		action.moveToElement(wineMove).build().perform(); 
		Thread.sleep(2000);
		
	    //Apply a facet on the default list view
		driver.findElement(By.id("check_box_showmoreCabernet Sauvignonvarietaltype")).click(); //Cabernet Sauvignon facet
		Thread.sleep(3000);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.inner-items-wrapper > ul > li > a.filter-link > span.filter-value")).getText(), "Cabernet Sauvignon");
	    
		//Access the Grid View
		driver.findElement(By.cssSelector("a.show-grid.analyticsViewAsGrid")).click();
		Thread.sleep(3000);
		
	    //Validate that the previously applied facet persists
		Assert.assertEquals(driver.findElement(By.cssSelector("div.inner-items-wrapper > ul > li > a.filter-link > span.filter-value")).getText(), "Cabernet Sauvignon");
		
	    //Validate presence of elements (Store, Item Size, Price, Badges, Expert Rating, Customer Rating, Customer Reviews, ATC, ATL
		Assert.assertEquals(driver.findElements(By.cssSelector("div.plp-list-img-wdlogo")).isEmpty(), false); //WD Logo
		Assert.assertEquals(driver.findElements(By.cssSelector("h2.plp-product-title")).isEmpty(), false); //Title
		Assert.assertEquals(driver.findElement(By.cssSelector("div.plp-product-qty")).getText(), "750ml"); //Item Size
		Assert.assertEquals(driver.findElements(By.cssSelector("div.product-img-div")).isEmpty(), false); //Product image
		Assert.assertEquals(driver.findElements(By.cssSelector("div.wine-spectator-div")).isEmpty(), false); //Expert Rating 
		Assert.assertEquals(driver.findElements(By.cssSelector("div.plp-list-img-staff-bot-bust")).isEmpty(), false); //Merchandising badge
		Assert.assertEquals(driver.findElements(By.cssSelector("div.plp-product-buy-limited")).isEmpty(), false); //Ltd Qty.
		
		Assert.assertEquals(driver.findElements(By.cssSelector("div.plp-product-buy-price-mix")).isEmpty(), false); //Mix6 price
		Assert.assertEquals(driver.findElements(By.cssSelector("div.plp-product-buy-actual-price")).isEmpty(), false); //EDLP
		
		Assert.assertEquals(driver.findElements(By.cssSelector("div.pdpRatingStars")).isEmpty(), false); //Rating (stars) 
		Assert.assertEquals(driver.findElements(By.cssSelector("a.analyticsProductReviews")).isEmpty(), false); //No. of reviews
		
		Assert.assertEquals(driver.findElements(By.cssSelector("div.plp-product-shipping")).isEmpty(), false); //Shipping badge
		Assert.assertEquals(driver.findElements(By.cssSelector("div.plp-product-delivery")).isEmpty(), false); //Pickup badge
		Assert.assertEquals(driver.findElements(By.cssSelector("form.add_to_cart_form.clear_fix")).isEmpty(), false); // Add to cart
		Assert.assertEquals(driver.findElements(By.cssSelector("button.btn.btn-brown-pattern.anAddToListInit")).isEmpty(), false); //Add to list
		
	    //Validate absence of elements (Product code, Product attributes, Expert review)
		Assert.assertEquals(driver.findElement(By.cssSelector("div.plp-product-code")).getText(), ""); //Product Code
		
	    //Sort Grid PLP
	    driver.findElement(By.cssSelector("div.dropdown.plp-product-sorting-sortby-dropdown > div > span.itemval")).click(); //Sort dropdown
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("li[data-val=our-favorites]")).click(); //Our favorites
	    Thread.sleep(5000);
		Assert.assertEquals(driver.findElements(By.cssSelector("section.plp-product-content.grid")).isEmpty(),false);
		
	    //Validate grid view is retained during pagination
		js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.cssSelector("a.pager-next.analyticsPageView")));
		
		//driver.findElement(By.cssSelector("a.pager-next.analyticsPageView")).sendKeys(Keys.PAGE_DOWN);
	    driver.findElement(By.cssSelector("a.pager-next.analyticsPageView")).click(); //Next page
	    Thread.sleep(3000);
	    Assert.assertEquals(driver.findElements(By.cssSelector("section.plp-product-content.grid")).isEmpty(),false);
	}
}
