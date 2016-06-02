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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageHomepage;
import com.totalwine.test.pages.PageProductList;
import com.totalwine.test.trials.Browser;

public class MobilePLPFilter extends Browser {

	private String IP="98.169.134.0";

	@Test 
	public void MobileFilterTest () throws InterruptedException, BiffException, IOException {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		logger=report.startTest("Mobile PLP Filter Test");
		SiteAccess.ActionAccessMobileSite(driver, IP);

		// **  By passing location
		driver.findElement(By.cssSelector("div.ChooseStoreButtons > button#btnNo.btn.btn-gray")).click();
		Thread.sleep(1000);

		//Access the Mobile PLP
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageHomepage.MobileWineButton).click();
		PageLoad(driver);
		
		//Click the Filter button
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilter).click();
		Thread.sleep(3000);
		
		//Validate available filters
		Assert.assertEquals(driver.findElements(PageProductList.MobilePLPFilterApply).isEmpty(),false);
		Assert.assertEquals(driver.findElements(PageProductList.MobilePLPFilterClear).isEmpty(),false);
		
		//STORES
//		Assert.assertTrue(driver.findElements(By.cssSelector("li[data-facet=storename] > div > ul > li > a > span > input[data-chk=yes]")).size()==5); //Validate that 5 stores are checked by default
		
		//CATEGORY
		//driver.findElement(PageProductList.FacetCategory).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		js.executeScript("arguments[0].click();", driver.findElement(PageProductList.FacetCategory));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()[contains(.,'Rose & Blush Wine')]]")));
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterApply).click();
		PageLoad(driver);
		Assert.assertEquals(driver.findElements(By.xpath("//a[contains(@class,'analyticsProductName') and text()[contains(.,'Blush')]]")).isEmpty(), false);
		Assert.assertEquals(driver.findElements(By.xpath("//a[contains(@class,'analyticsProductName') and text()[contains(.,'Rose')]]")).isEmpty(), false);
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilter).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterClear).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		PageLoad(driver);
		driver.findElement(PageProductList.MobilePLPFilter).click();
		
		//WINE VARIETAL & TYPE
		//driver.findElement(PageProductList.FacetVarietal).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		js.executeScript("arguments[0].click();", driver.findElement(PageProductList.FacetVarietal));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()[contains(.,'Merlot')]]")));
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterApply).click();
		PageLoad(driver);
		Assert.assertEquals(driver.findElements(By.xpath("//a[contains(@class,'analyticsProductName') and text()[contains(.,'Merlot')]]")).isEmpty(), false);
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilter).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterClear).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		PageLoad(driver);
		driver.findElement(PageProductList.MobilePLPFilter).click();
		
		//STYLE
		//driver.findElement(PageProductList.FacetStyle).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		js.executeScript("arguments[0].click();", driver.findElement(PageProductList.FacetStyle));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()[contains(.,'Brut')]]")));
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterApply).click();
		PageLoad(driver);
		Assert.assertEquals(driver.findElements(By.xpath("//a[contains(@class,'analyticsProductName') and text()[contains(.,'Brut')]]")).isEmpty(), false);
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilter).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterClear).click();
		PageLoad(driver);
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilter).click();
		
		//BRAND
		//driver.findElement(PageProductList.FacetBrand).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		js.executeScript("arguments[0].click();", driver.findElement(PageProductList.FacetBrand));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()[contains(.,'14 Hands')]]")));
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterApply).click();
		PageLoad(driver);
		Assert.assertEquals(driver.findElements(By.xpath("//a[contains(@class,'analyticsProductName') and text()[contains(.,'14 Hands')]]")).isEmpty(), false);
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilter).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterClear).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		PageLoad(driver);
		driver.findElement(PageProductList.MobilePLPFilter).click();
		
		//COUNTRY/STATE
		//driver.findElement(PageProductList.FacetCountry).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		js.executeScript("arguments[0].click();", driver.findElement(PageProductList.FacetCountry));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()[contains(.,'Oregon')]]")));
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterApply).click();
		PageLoad(driver);
		Assert.assertEquals(driver.findElements(By.xpath("//a[contains(@class,'analyticsProductName') and text()[contains(.,'Oregon')]]")).isEmpty(), false);
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilter).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterClear).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		PageLoad(driver);
		driver.findElement(PageProductList.MobilePLPFilter).click();
		
		//REGION
		//driver.findElement(PageProductList.FacetRegion).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		js.executeScript("arguments[0].click();", driver.findElement(PageProductList.FacetRegion));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()[contains(.,'Alsace')]]")));
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterApply).click();
		PageLoad(driver);
		Assert.assertEquals(driver.findElements(By.xpath("//a[contains(@class,'analyticsProductName') and text()[contains(.,'Alsace')]]")).isEmpty(), false);
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilter).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterClear).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		PageLoad(driver);
		driver.findElement(PageProductList.MobilePLPFilter).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		
		//APPELLATION
		//driver.findElement(PageProductList.FacetAppellation).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		js.executeScript("arguments[0].click();", driver.findElement(PageProductList.FacetAppellation));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()[contains(.,'Pauillac')]]")));
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterApply).click();
		PageLoad(driver);
		Assert.assertEquals(driver.findElements(By.xpath("//a[contains(@class,'analyticsProductName') and text()[contains(.,'Pauillac')]]")).isEmpty(), false);
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilter).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterClear).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		PageLoad(driver);
		driver.findElement(PageProductList.MobilePLPFilter).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		
		//TOP RATED
		//driver.findElement(PageProductList.FacetTopRated).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		js.executeScript("arguments[0].click();", driver.findElement(PageProductList.FacetTopRated));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()[contains(.,'94 and')]]")));
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterApply).click();
		PageLoad(driver);
		Assert.assertEquals(driver.findElement(By.cssSelector("span.plp-list-img-wineSpec-badge > span")).getText(), "94");
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilter).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterClear).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		PageLoad(driver);
		driver.findElement(PageProductList.MobilePLPFilter).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		
		//RATING SOURCE
		//driver.findElement(PageProductList.FacetRatingSource).click();
		js.executeScript("arguments[0].click();", driver.findElement(PageProductList.FacetRatingSource));
		SiteAccess.ActionAccessMobileAgeGate(driver);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()[contains(.,'Galloni')]]")));
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterApply).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		PageLoad(driver);
		Assert.assertEquals(driver.findElements(By.xpath("//span[contains(@class,'plp-list-img-wineSpec-text') and text()[contains(.,'Antonio Galloni')]]")).isEmpty(), false);
		SiteAccess.ActionAccessMobileAgeGate(driver);
		//Assert.assertEquals(driver.findElement(By.cssSelector("span.plp-list-img-wineSpec-text")).getText(), "Antonio Galloni");
		driver.findElement(PageProductList.MobilePLPFilter).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterClear).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		PageLoad(driver);
		driver.findElement(PageProductList.MobilePLPFilter).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		
		//PRICE RANGE
		//driver.findElement(PageProductList.FacetPrice).click();
		js.executeScript("arguments[0].click();", driver.findElement(PageProductList.FacetPrice));
		SiteAccess.ActionAccessMobileAgeGate(driver);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()[contains(.,'Up to $10')]]")));
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterApply).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		PageLoad(driver);
		Assert.assertEquals(driver.findElements(By.xpath("//span[contains(@class,'price') and text()[contains(.,'8.99')]]")).isEmpty(), false);
		//Assert.assertEquals(driver.findElement(By.cssSelector("span.plp-list-img-wineSpec-text")).getText(), "Antonio Galloni");
		driver.findElement(PageProductList.MobilePLPFilter).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterClear).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		PageLoad(driver);
		driver.findElement(PageProductList.MobilePLPFilter).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		
		//SIZE
		//driver.findElement(PageProductList.FacetSize).click();
		js.executeScript("arguments[0].click();", driver.findElement(PageProductList.FacetSize));
		SiteAccess.ActionAccessMobileAgeGate(driver);
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()[contains(.,'Small Format')]]")));
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterApply).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		PageLoad(driver);
		Assert.assertEquals(driver.findElement(By.cssSelector("span.desc")).getText(), "187ml");
		driver.findElement(PageProductList.MobilePLPFilter).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		driver.findElement(PageProductList.MobilePLPFilterClear).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
		PageLoad(driver);
		driver.findElement(PageProductList.MobilePLPFilter).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);
	}
}
