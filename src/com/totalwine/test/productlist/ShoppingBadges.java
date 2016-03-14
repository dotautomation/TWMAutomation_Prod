package com.totalwine.test.productlist;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageProductList;
import com.totalwine.test.trials.*;


public class ShoppingBadges extends Browser {
	
	 @BeforeClass
	  public void setUp() throws Exception {
	   driver.manage().window().maximize();
	   SiteAccess.ActionAccessSite(driver, "174.28.39.0");
	  }  
	
	@Test 
	//In Stock in CS
	public void isCSisNSisIFC () throws InterruptedException {
		logger=report.startTest("Shopping Badges Test: isCSisNSisIFC");
		driver.get(ConfigurationFunctions.accessURL+"/wine/red-wine/c/000009?plppricevalue=up-to-10&brandcode=ropiteau"); //94711750
		PageLoad(driver);
		Assert.assertTrue(driver.findElement(PageProductList.ShipAvailable).isDisplayed());
		Assert.assertTrue(driver.findElement(PageProductList.ISPAvailable).isDisplayed());
	}
	
	@Test 
	//In Stock in NS
	public void isCSooNSisIFC () {
		logger=report.startTest("Shopping Badges Test: isCSooNSisIFC");
		driver.get(ConfigurationFunctions.accessURL+"/wine/red-wine/c/000009?sort=price-desc&storename=1301,1302&plpprice=1300-1400&brandcode=chateau-latour");
		PageLoad(driver);
		Assert.assertTrue(driver.findElement(PageProductList.ShipAvailable).isDisplayed());
		Assert.assertTrue(driver.findElement(PageProductList.ISPAvailable).isDisplayed());
	}
	
	@Test 
	//OOS in Current Store, in stock in Nearby Store and IFC
	public void oosCSisNSisIFC () {
		logger=report.startTest("Shopping Badges Test: oosCSisNSisIFC");
	}
	
	@Test
	//OOS in Current Store, Nearby Store and in stock IFC
	public void oosCSoosNSisIFC () {
		logger=report.startTest("Shopping Badges Test: oosCSoosNSisIFC");
	}
	
	@Test 
	//In Stock in Current Store, Nearby Store and OOS in IFC
	public void isCSisNSoosIFC () {
		logger=report.startTest("Shopping Badges Test: isCSisNSoosIFC");
	}
	
	@Test 
	//In Stock in Current Store, OOS in Nearby Store and IFC
	public void isCSoosNSoosIFC () {
		logger=report.startTest("Shopping Badges Test: isCSoosNSoosIFC");
	}
	
	@Test 
	//OOS in Current Store, in stock in Nearby Store and OOS in IFC
	public void oosCSisNSoosIFC () {
		logger=report.startTest("Shopping Badges Test: oosCSisNSoosIFC");
	}
	
	@Test 
	//OOS in Current Store, Nearby Store and IFC
	public void oosCSoosNSoosIFC () {
		logger=report.startTest("Shopping Badges Test: oosCSoosNSoosIFC");
	}
	
	@Test
	//Not Carried in in Current Store, Nearby Store and IFC
	public void ncCSncNSncIFC () {
		logger=report.startTest("Shopping Badges Test: ncCSncNSncIFC");
	}
	
	@Test 
	//Not carried in Current Store, OOS in Nearby Store and in stock IFC
	public void ncCSoosNSisIFC () {
		logger=report.startTest("Shopping Badges Test: ncCSoosNSisIFC");
	}
	
	@Test 
	//Not carried in Current Store, Nearby Store and in stock IFC
	public void ncCSncNSisIFC () {
		logger=report.startTest("Shopping Badges Test: ncCSncNSisIFC");
	}
	
	@Test 
	//In Stock in Current Store, Nearby Store and not carried in IFC
	public void isCSisNSncIFC () {
		logger=report.startTest("Shopping Badges Test: isCSisNSncIFC");
	}
	
	@Test 
	//OOS in Current Store, Nearby Store and not carried in IFC
	public void oosCSoosNSncIFC () {
		logger=report.startTest("Shopping Badges Test: oosCSoosNSncIFC");
	}

}
