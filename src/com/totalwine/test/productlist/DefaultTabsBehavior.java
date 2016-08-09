package com.totalwine.test.productlist;

/*
 * PLP Default Tabs Behavior Workflow
 * Workflow:
 * 	1. Access the site via IP-simulated locations
 * 	2. Access the Wine, Beer, Spirits and Accessories PLP
 * 	  a. If ATY is the default, then validate that it is active (css=li.active > h2 > a#plp-aty-tab) and FC is inactive
	  b. If FC is the default, then validate that it is active (css=li.active > h2 > a#plp-productfull-tabs) and ATY is inactive
 *  3. Within the ATY Tab, validate the default behavior for the ISP/Ship/Both sub-tabs
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

import org.testng.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;

import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class DefaultTabsBehavior extends Browser {
	
	//private String IP="71.193.51.0";
	
	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	}
	
	@DataProvider(name="PLPTabParameters")
    public Object[][] createData() {
		Object[][] retObjArr;
		retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"PLP", "plptabsprod");
        return(retObjArr);
    }
	
	@Test
	public void CountrySubCatPLPNav () throws InterruptedException {
		logger=report.startTest("PLP Sub Category Landing Pages Test");
		String IP="71.193.51.0";
		SiteAccess.ActionAccessSite(driver, IP);
	    
	    //Navigate to Country SubCat (Wine) Landing Page
	    Actions action=new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
	    WebElement wineNav = driver.findElement(By.xpath("//a[contains(@href,'/c/c0020')]")); 
		action.moveToElement(wineNav).build().perform(); //Top Level Menu Hover
		WebElement winePLPNav=driver.findElement(By.xpath("//a[contains(@href,'/c/000270')]")); //France
		js.executeScript("arguments[0].click();", winePLPNav);
		Thread.sleep(5000);
		
	    //Click a link under the "Wine Category"
		driver.findElement(By.xpath("//a[contains(@href,'/c/000270?producttype=red-wine&viewall=true')]")).click(); //Red Wine
//		Assert.assertEquals(driver.findElements(By.cssSelector("li.active > h2 > a#plp-aty-tab")).isEmpty(),false);
//		Assert.assertEquals(driver.findElements(By.cssSelector("li.active > h2 > a#plp-productfull-tabs")).isEmpty(),true);
	}
	
	@Test (dataProvider = "PLPTabParameters")
	public void PLPDefaultTabsTest (String IP,String Store,String Wine,String WinePLP,String Beer,String BeerPLP,String Spirits,String SpiritsPLP,
			String Acc,String AccPLP,String DefaultWine,String DefaultBeer,String DefaultSpirits,String DefaultAcc,String ATYWineSubTab,String ATYBeerSubTab,
			String ATYSpiritsSubTab,String ATYAccSubTab) throws InterruptedException {
		logger=report.startTest("PLP Default Tabs Test");
		SiteAccess.ActionAccessSite(driver, IP);
	    Actions action=new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;

		//Browse to PLP
	    //Access Wine PLP
		WebElement wineNav = driver.findElement(By.xpath("//a[contains(@href,'"+Wine+"')]")); 
		action.moveToElement(wineNav).build().perform(); //Top Level Menu Hover
		WebElement winePLPNav=driver.findElement(By.xpath("//a[contains(@href,'"+WinePLP+"')]"));
		js.executeScript("arguments[0].click();", winePLPNav);
		Thread.sleep(5000);
		WebElement wineMove = driver.findElement(By.cssSelector("ul.header-classes")); //Moving the mouse away from the top level menu 
		action.moveToElement(wineMove).build().perform(); 
	    //Validate default tab
		if (DefaultWine.equalsIgnoreCase("ATY")) {
//			Assert.assertEquals(driver.findElements(By.cssSelector("li.active > h2 > a#plp-aty-tab")).isEmpty(),false);
			Assert.assertEquals(driver.findElements(By.cssSelector("li.active > h2 > a#plp-productfull-tabs")).isEmpty(),true);
			//Validate ISP/Ship/Both sub-tab behavior
			if (ATYWineSubTab.equalsIgnoreCase("ISP")) {
				Assert.assertEquals(driver.findElements(By.cssSelector("li > a.an_isp.active")).isEmpty(),false);
				Assert.assertEquals(driver.findElements(By.cssSelector("li > a.an_shipTo.active")).isEmpty(),true);
				Assert.assertEquals(driver.findElements(By.cssSelector("li > a.an_avBoth.active")).isEmpty(),true);
			} else if (ATYWineSubTab.equalsIgnoreCase("ShipTo")) {
//				Assert.assertEquals(driver.findElements(By.cssSelector("li > a.an_isp.active")).isEmpty(),false);
				Assert.assertEquals(driver.findElements(By.cssSelector("li > a.an_shipTo.active")).isEmpty(),false);
				Assert.assertEquals(driver.findElements(By.cssSelector("li > a.an_avBoth.active")).isEmpty(),true);
			} else if (ATYWineSubTab.equalsIgnoreCase("Both")) {
				Assert.assertEquals(driver.findElements(By.cssSelector("li > a.an_isp.active")).isEmpty(),true);
				Assert.assertEquals(driver.findElements(By.cssSelector("li > a.an_shipTo.active")).isEmpty(),true);
				Assert.assertEquals(driver.findElements(By.cssSelector("li > a.an_avBoth.active")).isEmpty(),false);
			}
		} else {
			sAssert.assertEquals(driver.findElements(By.cssSelector("li.active > h2 > a#plp-aty-tab")).isEmpty(),true);
//			sAssert.assertEquals(driver.findElements(By.cssSelector("li.active > h2 > a#plp-productfull-tabs")).isEmpty(),false);
		}
		
	    //Access Beer PLP
		WebElement beerNav = driver.findElement(By.xpath("//a[contains(@href,'"+Beer+"')]")); 
		action.moveToElement(beerNav).build().perform(); //Top Level Menu Hover
		WebElement beerPLPNav=driver.findElement(By.xpath("//a[contains(@href,'"+BeerPLP+"')]"));
		js.executeScript("arguments[0].click();", beerPLPNav);
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//a[contains(@href,'41513?viewall=true')]")).click(); //For production since the SubCat Land page is setup
		Thread.sleep(5000);
		WebElement beerMove = driver.findElement(By.cssSelector("ul.header-classes")); //Moving the mouse away from the top level menu 
		action.moveToElement(beerMove).build().perform(); 
		
		//Validate default tab
		if (DefaultBeer.equalsIgnoreCase("ATY")) {
//			sAssert.assertEquals(driver.findElements(By.cssSelector("li.active > h2 > a#plp-aty-tab")).isEmpty(),false);
			sAssert.assertEquals(driver.findElements(By.cssSelector("li.active > h2 > a#plp-productfull-tabs")).isEmpty(),true);
			//Validate ISP/Ship/Both sub-tab behavior
			if (ATYBeerSubTab.equalsIgnoreCase("ISP")) {
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_isp.active")).isEmpty(),false);
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_shipTo.active")).isEmpty(),true);
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_avBoth.active")).isEmpty(),true);
			} else if (ATYBeerSubTab.equalsIgnoreCase("ShipTo")) {
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_isp.active")).isEmpty(),true);
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_shipTo.active")).isEmpty(),false);
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_avBoth.active")).isEmpty(),true);
			} else if (ATYBeerSubTab.equalsIgnoreCase("Both")) {
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_isp.active")).isEmpty(),true);
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_shipTo.active")).isEmpty(),true);
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_avBoth.active")).isEmpty(),false);
			}
		} else {
			sAssert.assertEquals(driver.findElements(By.cssSelector("li.active > h2 > a#plp-aty-tab")).isEmpty(),true);
//			sAssert.assertEquals(driver.findElements(By.cssSelector("li.active > h2 > a#plp-productfull-tabs")).isEmpty(),false);
		}
		
	    //Access Spirits PLP
		WebElement spiritsNav = driver.findElement(By.xpath("//a[contains(@href,'"+Spirits+"')]")); 
		action.moveToElement(spiritsNav).build().perform(); //Top Level Menu Hover
		WebElement spiritsPLPNav=driver.findElement(By.xpath("//a[contains(@href,'"+SpiritsPLP+"')]"));
		js.executeScript("arguments[0].click();", spiritsPLPNav);
		Thread.sleep(5000);
		WebElement spiritsMove = driver.findElement(By.cssSelector("ul.header-classes")); //Moving the mouse away from the top level menu 
		action.moveToElement(spiritsMove).build().perform(); 
		//Validate default tab
		if (DefaultSpirits.equalsIgnoreCase("ATY")) {
//			sAssert.assertEquals(driver.findElements(By.cssSelector("li.active > h2 > a#plp-aty-tab")).isEmpty(),false);
			sAssert.assertEquals(driver.findElements(By.cssSelector("li.active > h2 > a#plp-productfull-tabs")).isEmpty(),true);
			//Validate ISP/Ship/Both sub-tab behavior
			if (ATYSpiritsSubTab.equalsIgnoreCase("ISP")) {
//				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_isp.active")).isEmpty(),true);
//				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_shipTo.active")).isEmpty(),true);
//				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_avBoth.active")).isEmpty(),false);
			} else if (ATYSpiritsSubTab.equalsIgnoreCase("ShipTo")) {
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_isp.active")).isEmpty(),true);
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_shipTo.active")).isEmpty(),false);
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_avBoth.active")).isEmpty(),true);
			} else if (ATYSpiritsSubTab.equalsIgnoreCase("Both")) {
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_isp.active")).isEmpty(),true);
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_shipTo.active")).isEmpty(),true);
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_avBoth.active")).isEmpty(),false);
			}
		} else {
			sAssert.assertEquals(driver.findElements(By.cssSelector("li.active > h2 > a#plp-aty-tab")).isEmpty(),true);
//			Assert.assertEquals(driver.findElements(By.cssSelector("li.active > h2 > a#plp-productfull-tabs")).isEmpty(),false);
		}
		
	    //Access Accessories PLP
		WebElement accNav = driver.findElement(By.xpath("//a[contains(@href,'"+Acc+"')]")); 
		action.moveToElement(accNav).build().perform(); //Top Level Menu Hover
		WebElement accPLPNav=driver.findElement(By.xpath("//a[contains(@href,'"+AccPLP+"')]"));
		js.executeScript("arguments[0].click();", accPLPNav);
		Thread.sleep(7000);
		WebElement accMove = driver.findElement(By.cssSelector("ul.header-classes")); //Moving the mouse away from the top level menu 
		action.moveToElement(accMove).build().perform(); 
		//Validate default tab: ATY will always be the default
		if (DefaultAcc.equalsIgnoreCase("ATY")) {
//			Assert.assertEquals(driver.findElements(By.cssSelector("li.active > h2 > a#plp-aty-tab")).isEmpty(),false);
			sAssert.assertEquals(driver.findElements(By.cssSelector("li.active > h2 > a#plp-productfull-tabs")).isEmpty(),true);
			//Validate ISP/Ship/Both sub-tab behavior
			if (ATYAccSubTab.equalsIgnoreCase("ISP")) {
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_isp.active")).isEmpty(),false);
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_shipTo.active")).isEmpty(),true);
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_avBoth.active")).isEmpty(),false);
			} else if (ATYAccSubTab.equalsIgnoreCase("ShipTo")) {
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_isp.active")).isEmpty(),true);
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_shipTo.active")).isEmpty(),false);
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_avBoth.active")).isEmpty(),true);
			} else if (ATYAccSubTab.equalsIgnoreCase("Both")) {
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_isp.active")).isEmpty(),true);
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_shipTo.active")).isEmpty(),true);
				sAssert.assertEquals(driver.findElements(By.cssSelector("li > a.an_avBoth.active")).isEmpty(),false);
			}
		} else {
			sAssert.assertEquals(driver.findElements(By.cssSelector("li.active > h2 > a#plp-aty-tab")).isEmpty(),true);
			sAssert.assertEquals(driver.findElements(By.cssSelector("li.active > h2 > a#plp-productfull-tabs")).isEmpty(),false);
		}
	}
}