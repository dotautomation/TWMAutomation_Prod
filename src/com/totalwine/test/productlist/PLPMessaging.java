package com.totalwine.test.productlist;

/*
Scenarios: 
1. Customer could not be geo located, hasn't visited the website before, and declined to enter location.
2. Customer is outside of the United States viewing TWM website.
3. Customer is in a state with no nearby stores OR Customer has been geolocated and there are no stores within BDR
4. Customer is in a state where a specific department is not carried but that department is available in a nearby store. Shipping and ISP are available.
5. Customer is in a state where a specific department is not carried but that department is available in a nearby store. Shipping is only available for Accessories.
6. Customer is in a state where the IFC is in different price zone than nearby store. OR Customer is in a non-market state but there are nearby stores
7. Customer is in a state that has no nearby stores and only Accessories can be shipped.
8. Customer has a saved Ship to State in profile OR Customer manually enters a Ship to State OR Ship to State saved in cookie
 */

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.pages.PageProductList;
import com.totalwine.test.trials.Browser;

public class PLPMessaging extends Browser {
	
	String [] IPAddresses = {"10.125.18.63"/*Unknown IP*/,
			"131.228.17.26"/*London*/,
			"66.230.105.38"/*Idaho*/,
			"98.169.134.0"/*McLean*/,
			"24.47.97.83"/*Connecticut*/,
			"174.28.39.0"/*New Mexico*/,
			"208.53.192.14"/*South Dakota*/};
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	}  
	
	@Test //Unknown IP
	public void PLPMessagingUnknownIPTest () throws InterruptedException {
		logger=report.startTest("PLP Messaging Test for undetected IP");
		driver.get(ConfigurationFunctions.locationSet+"10.125.18.63");
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
		//Location Intercept
	    driver.findElement(PageGlobal.LocationInterceptNo).click();

	    //Validate Global header
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src"))).build().perform();
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.cssSelector("div.popover-content > div.mCartBg > div.itemsInCart.textAlignCenter > #welcome-tooltip-body > p")).getText());
		sAssert.assertTrue(driver.findElement(By.cssSelector("div.popover-content > div.mCartBg > div.itemsInCart.textAlignCenter > #welcome-tooltip-body > p")).getText().contains("We were unable to determine your location"),"PLP Messaging for unknown IP is incorrect");
		
		//Validate Messaging above PLP tabs
		driver.get(ConfigurationFunctions.accessURL+"/wine/red-wine/c/000009");
		PageLoad(driver);
		Assert.assertTrue(driver.findElement(By.cssSelector("p.msg-noitems")).getText().contains("Location not detected."), "PLP Tab messaging is not correctly displayed");
		
		PLPMessagingValidation();
		sAssert.assertAll();
	}
	
	
	@Test //International location
	public void PLPMessagingInternationalTest () throws InterruptedException {
		logger=report.startTest("PLP Messaging Test for international location");
		SiteAccess.ActionAccessSite(driver, "131.228.17.26");

	    //Validate Global header
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src"))).build().perform();
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.cssSelector("div.popover-content > div.mCartBg > div.itemsInCart.textAlignCenter > #welcome-tooltip-body")).getText());
		Assert.assertTrue(driver.findElement(By.cssSelector("div.popover-content > div.mCartBg > div.itemsInCart.textAlignCenter > #welcome-tooltip-body")).getText().contains("We detected that you are accessing the website from outside of the U.S."),"PLP Messaging for international IP is incorrect");
		
		//Validate Messaging above PLP tabs
		driver.get(ConfigurationFunctions.accessURL+"/wine/red-wine/c/000009");
		PageLoad(driver);
		sAssert.assertTrue(driver.findElement(PageProductList.GlobalMessaging).getText().contains("Default store is set."), "PLP Tab messaging is not correctly displayed");
		
		PLPMessagingValidation();
		sAssert.assertAll();
	}
	
	@Test //No stores in BDR
	public void PLPMessagingNoBDRTest () throws InterruptedException {
		logger=report.startTest("PLP Messaging Test for location with no stores in BDR");
		SiteAccess.ActionAccessSite(driver, "66.230.105.38");

	    //Validate Global header
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src"))).build().perform();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector("div.popover-content > div.mCartBg > div.itemsInCart.textAlignCenter > #welcome-tooltip-body")).getText().contains("Sorry! We could not find any Total Wine & More stores near your location."),"PLP Messaging for location with no stores in BDR is incorrect");
		
		//Validate Messaging above PLP tabs
		driver.get(ConfigurationFunctions.accessURL+"/wine/red-wine/c/000009");
		PageLoad(driver);
		sAssert.assertTrue(driver.findElement(PageProductList.GlobalMessaging).getText().contains("Accessories & More,Wine can be shipped to your selected"), "PLP Tab messaging is not correctly displayed");
		
		//Validate Spirits PLP
		driver.get(ConfigurationFunctions.accessURL+"/spirits/bourbon/small-batch-bourbon/c/000866");
		PageLoad(driver);
		sAssert.assertTrue(driver.findElement(PageProductList.GlobalMessaging).getText().contains("Accessories & More,Wine can be shipped to your selected"), "Spirits PLP messaging is not correctly displayed");
		
		//Validate Beer PLP
		driver.get(ConfigurationFunctions.accessURL+"/beer/ale/amber-red-ale/c/001174");
		PageLoad(driver);
		sAssert.assertTrue(driver.findElement(PageProductList.GlobalMessaging).getText().contains("Accessories & More,Wine can be shipped to your selected"), "Beer PLP messaging is not correctly displayed");
		
		//Validate Accessories PLP
		driver.get(ConfigurationFunctions.accessURL+"/accessories-more/mixers-water-soda/cocktail-rimmers/c/001548");
		PageLoad(driver);
		sAssert.assertEquals(driver.findElements(PageProductList.GlobalMessaging).isEmpty(),false, "Accessories PLP messaging is not correctly displayed");
		sAssert.assertAll();
	}
	
	
	@Test //Department unavailable at store but available nearby
	public void PLPMessagingNearbyDepartment () throws InterruptedException {
		logger=report.startTest("PLP Messaging Test for a store with an available department nearby");
		SiteAccess.ActionAccessSite(driver, "98.169.134.0");

	    //Validate Global header
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src"))).build().perform();
		Thread.sleep(2000);
		sAssert.assertTrue(driver.findElement(By.cssSelector("div.popover-content > div.mCartBg > div.itemsInCart.textAlignCenter > #welcome-tooltip-body")).getText().contains("information about your closest store."),"PLP Messaging for department availability in nearby store is incorrect");
		
		//Validate Spirits PLP
		driver.get(ConfigurationFunctions.accessURL+"/spirits/bourbon/small-batch-bourbon/c/000866");
		PageLoad(driver);
		sAssert.assertEquals(driver.findElements(By.xpath("//input[contains(@id,'Laurel')]")).isEmpty(), false,"Nearby store with available department is not displayed");
		sAssert.assertAll();
	}
	
	@Test //ISP and IFC are in different price zones
	public void PLPMessagingDiffISPShip () throws InterruptedException {
		logger=report.startTest("PLP Messaging Test for a store with an IFC in a different price zone");
		SiteAccess.ActionAccessSite(driver,"174.28.39.0");

	    //Validate Global header
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src"))).build().perform();
		Thread.sleep(2000);
		sAssert.assertTrue(driver.findElement(By.cssSelector("div.popover-content > div.mCartBg > div.itemsInCart.textAlignCenter > #welcome-tooltip-body")).getText().contains("information about your closest store."),"PLP Messaging for ISP/IFC in different price zones is incorrect");
		
		//Validate Messaging above PLP tabs
		driver.get(ConfigurationFunctions.accessURL+"/wine/red-wine/c/000009");
		PageLoad(driver);
		driver.findElement(By.cssSelector("a.an_shipTo")).click();
		PageLoad(driver);
		sAssert.assertEquals(driver.findElement(PageProductList.PLPMessaging).getText().contains("Pricing for items in your nearby stores and your ship-to state may differ."),true, "PLP Messaging is incorrect");
		sAssert.assertAll();
	}
	
	@Test //Only accessories can be shipped to location
	public void PLPMessagingOnlyAccessories () throws InterruptedException {
		logger=report.startTest("PLP Messaging Test for a location where only accessories can be shipped");
		SiteAccess.ActionAccessSite(driver,"208.53.192.14");

	    //Validate Global header
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src"))).build().perform();
		Thread.sleep(2000);
		sAssert.assertTrue(driver.findElement(By.cssSelector("div.popover-content > div.mCartBg > div.itemsInCart.textAlignCenter > #welcome-tooltip-body")).getText().contains("Sorry! We could not find any Total Wine & More stores near your location"),"PLP Messaging for ISP/IFC in different price zones is incorrect");
		
		//Validate Messaging above PLP tabs
		driver.get(ConfigurationFunctions.accessURL+"/wine/red-wine/c/000009");
		PageLoad(driver);
		sAssert.assertEquals(driver.findElement(PageProductList.GlobalMessaging).getText().contains("Unfortunately, we do not have any stores near you. Check out our full catalog of products and shippable Accessories"),true, "PLP Messaging is incorrect");
		sAssert.assertAll();
	}
	
	
	public void PLPMessagingValidation () {
		//Validate Spirits PLP
		driver.get(ConfigurationFunctions.accessURL+"/spirits/bourbon/small-batch-bourbon/c/000866");
		PageLoad(driver);
		sAssert.assertTrue(driver.findElement(PageProductList.PLPMessaging).getText().contains("Due to regulatory constraints, Spirits are not eligible for shipping."), "Spirits PLP messaging is not correctly displayed");
		
		//Validate Beer PLP
		driver.get(ConfigurationFunctions.accessURL+"/beer/ale/amber-red-ale/c/001174");
		PageLoad(driver);
		sAssert.assertTrue(driver.findElement(PageProductList.PLPMessaging).getText().contains("Due to regulatory constraints, Beer is not eligible for shipping"), "Beer PLP messaging is not correctly displayed");
		
		//Validate Accessories PLP
		driver.get(ConfigurationFunctions.accessURL+"/accessories-more/mixers-water-soda/cocktail-rimmers/c/001548");
		PageLoad(driver);
		sAssert.assertEquals(driver.findElements(PageProductList.PLPMessaging).isEmpty(),true, "Accessories PLP messaging is not correctly displayed");
	}
	
}
