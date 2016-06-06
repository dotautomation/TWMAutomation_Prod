package com.totalwine.test.pdp;

/*
 * PDP Tabs Workflow
 * Workflow:
 * 	1. Browse to a PDP
 * 	  a. Validate the contents of each tab
 *  2. Iterate through the workflow for each item type (Wine, Beer, Spirits, Accessory, Cigar)
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

import org.testng.*;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class PDPTabs extends Browser {
	
	private String IP="71.193.51.0";
	
	@DataProvider(name="PDPParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"PDP", "pdpprod");
        return(retObjArr);
    } 
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	  }  
	
	@Test (dataProvider = "PDPParameters")
	public void PDPTest (String toplevel,String plp) throws InterruptedException, BiffException, IOException {
		logger=report.startTest("PDP Tabs Test");
		SiteAccess.ActionAccessSite(driver, IP);
	    
		Actions action=new Actions(driver);
		
		//Browse to PLP
		WebElement toplevelnav = driver.findElement(By.xpath("//a[contains(@href,'"+toplevel+"')]")); 
		action.moveToElement(toplevelnav).build().perform(); //Top Level Menu Hover
		WebElement plpnav=driver.findElement(By.xpath("//a[contains(@href,'"+plp+"')]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", plpnav);
		PageLoad(driver);
		
		//Access the PDP
		WebElement plpmove = driver.findElement(By.cssSelector("ul.header-classes")); //Moving the mouse away from the top level menu 
		action.moveToElement(plpmove).build().perform();
		
		//driver.findElement(By.xpath("//a[contains(@href,'"+plp+"?viewall=true')]")).click(); //For production since the SubCat Land page is setup
		//driver.findElement(By.xpath("//a[contains(@href,'000002?viewall=true')]")).click(); //For production since the SubCat Land page is setup
		PageLoad(driver);
		
		String winename = driver.findElement(By.cssSelector("a.analyticsProductName")).getText();
		System.out.println(winename);
		
		
		JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
		js1.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a.analyticsProductName")));        
//		driver.findElement(By.cssSelector("a.analyticsProductName")).click(); //Click the first item link in the PLP
		PageLoad(driver);
		Thread.sleep(2000);
		
		//Tab 1 - Overview
		Assert.assertEquals(driver.findElements(By.cssSelector("section.pdp-tab-overview-prod-img > div.pdp-tab-overview-prod-img-bottle-img.pdp-img-zoom-modal-zoom-reset > img.anPDPImage")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("h1.product-name")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.pdp-tab-overview-desc-name > div.wine_details > ul.wine-right-details > li > h2 > a.analyticsCountryState")).isEmpty(),false);
	    //Assert.assertEquals(driver.findElements(By.cssSelector("section.css-hook-desc > div.pdp-tab-overview-desc-price > ul > li > div > span.price-style")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("li.sale-price-present > div > span.price-style-mid")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("#pdpTabs > section.item.pdp-tab-overview > section.pdp-tab-overview-type")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("button.anAddToCart")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("button.anAddToListInit")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div#overview-qty")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("#pdpTabs > section.item.pdp-tab-overview > section.pdp-tab-overview-type > #variantForm > #overview-mililitres > div.customselect > span.itemval")).isEmpty(),false);
//	    Assert.assertEquals(driver.findElements(By.cssSelector("span.pdp-tabs-ind-left")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("img.anPDPImage.active")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.breadcrumbs")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("span.tabs-right.anPDPTab")).isEmpty(),false);
	    
	    driver.findElement(By.cssSelector("span.tabs-right.anPDPTab")).click();
	    Thread.sleep(2000);
	    
	    //Tab 2 - Product Details
	    Assert.assertEquals(driver.findElements(By.cssSelector("section.css-details-pd")).isEmpty(),false);
	    
	    driver.findElement(By.cssSelector("span.tabs-right.anPDPTab")).click();
	    Thread.sleep(3000);
	    
	    //Tab 3 - BazaarVoice
//	    Assert.assertEquals(driver.findElements(By.cssSelector("div.BVDITitle.BVDI_QTTitle")).isEmpty(),false);
//	    Assert.assertEquals(driver.findElements(By.cssSelector("img.BVImgOrSprite")).isEmpty(),false);
//	    Assert.assertEquals(driver.findElements(By.cssSelector("img.BVRRTrustMarkOverlayImage")).isEmpty(), false);
//	    Assert.assertEquals(driver.findElements(By.cssSelector("div#BVRRRatingSummaryLinkWriteID")).isEmpty(),false);
	    
	    
	    //Commenting since RR display is currently disabled in production
	    /*driver.findElement(By.cssSelector("span.tabs-right.anPDPTab")).click();
	    Thread.sleep(5000);
	    
	    //Tab 4 - RichRelevance
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.rr-strat-msg")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.rr-priceContainer")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("img.rr-image-asset")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("button.anAddToCart")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("button.anAddToListInit")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("span.tabs-left.anPDPTab")).isEmpty(),false);*/
	}
}