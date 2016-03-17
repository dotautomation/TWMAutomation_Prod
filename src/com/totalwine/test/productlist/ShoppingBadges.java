package com.totalwine.test.productlist;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageProductList;
import com.totalwine.test.trials.*;


public class ShoppingBadges extends Browser {
	
	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	}  
	
	@DataProvider(name="PLPBadgeParameters")
    public Object[][] createData() {
		Object[][] retObjArr;
		retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"PLP", "badges");
        return(retObjArr);
    }
	 
	@Test (dataProvider="PLPBadgeParameters")
	//In Stock in CS
	public void ShoppingBadgesTest (String url,String ship,String isp,String store,String log) throws InterruptedException {
		logger=report.startTest("Shopping Badges Test: "+log);
		SiteAccess.ActionAccessSite(driver, "174.28.39.0");
		driver.get(ConfigurationFunctions.accessURL+url); //94711750
		PageLoad(driver);
		if(ship.equals("Ship"))
			Assert.assertTrue(driver.findElements(PageProductList.ShipAvailable).isEmpty(),"An item expected to be in stock for shipping isn't available");
		else if (ship.equals("NoShip"))
			Assert.assertTrue(driver.findElements(PageProductList.ShipUnavailable).isEmpty(),"An item expected to be unavailable for shipping is available");
		else {
			Assert.assertFalse(driver.findElements(PageProductList.ShipAvailable).isEmpty(),"An item not carried in IFC was displayed");
			Assert.assertFalse(driver.findElements(PageProductList.ShipUnavailable).isEmpty(),"An item not carried in IFC was displayed");
		}
		if (isp.equals("ISP"))
			Assert.assertTrue(driver.findElements(PageProductList.ISPAvailable).isEmpty(),"An item expected to be in stock for ISP isn't available");
		else if (isp.equals("NoISP"))
			Assert.assertTrue(driver.findElements(PageProductList.ISPUnavailable).isEmpty(),"An item expected to be unavailable for ISP is available");
		else {
			Assert.assertFalse(driver.findElements(PageProductList.ISPAvailable).isEmpty(),"An item not carried in store was displayed");
			Assert.assertFalse(driver.findElements(PageProductList.ISPUnavailable).isEmpty(),"An item not carried in store was displayed");
		}
		if (driver.findElements(By.cssSelector("span.plp-product-pickup-location")).size()!=0)
			Assert.assertEquals(driver.findElement(By.cssSelector("span.plp-product-pickup-location")).getText(), store,"The incorrect store was displayed in the PLP tile");
	}	
	/*
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
	}*/

}
