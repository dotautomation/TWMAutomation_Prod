package com.totalwine.test.categorylanding;

/*
 * PLP Category Landing Workflow
 * Workflow:
 * 	1. Access a category with no category landing page set up (e.g. White Wine)
 * 	  a. Validate the presence of PLP elements and absence of Category Landing page elements
 * 	2. Access a category with a category landing page set up (e.g. Wine)
 * 	  a. Validate the absence of PLP elements and presence of Category Landing page elements
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

public class CatLandNav extends Browser {
	
	//public WebDriver driver;
	//ProfilesIni profile = new ProfilesIni();
	//FirefoxProfile testProfile = profile.getProfile("WebDriver");

	private String IP="71.193.51.0";
	
	@DataProvider(name="CatLandParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"CatLand", "catlandprod");
        return(retObjArr);
    } 
	
	@BeforeMethod
	  public void setUp() throws Exception {
		//this.driver = ConfigurationFunctions.driver;
		//driver = new FirefoxDriver(testProfile);
		//testProfile.setEnableNativeEvents(true);
	    driver.manage().window().maximize();
	  }  
	
	@Test (dataProvider = "CatLandParameters")
	public void CatLandNavTest (String toplevel,String plp,String catlandpage) throws InterruptedException, BiffException, IOException {
		logger=report.startTest("Category Landing Page Test");
		//ConfigurationFunctions.initialStartUp("71.193.51.0");
		SiteAccess.ActionAccessSite(driver, IP);
	    
		Actions action=new Actions(driver);
		
		WebElement toplevelnav = driver.findElement(By.xpath("//a[contains(@href,'"+toplevel+"')]"));
		action.moveToElement(toplevelnav).build().perform();
		
		//Navigate to Category without Cat Landing
		WebElement plpnav=driver.findElement(By.xpath("//a[contains(@href,'"+plp+"')]"));
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", plpnav);
		Thread.sleep(5000);
		//Validate PLP presence and CatLand absence
		Assert.assertEquals(driver.findElements(By.cssSelector("div.plp-product-desc")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("a.analyticsHeroLink")).isEmpty(),true);
		
		//Navigate to Category with Cat Landing
		WebElement toplevelnav1 = driver.findElement(By.xpath("//a[contains(@href,'"+toplevel+"')]"));
		action.moveToElement(toplevelnav1).build().perform();
		Thread.sleep(5000);
		WebElement catlandpage1=driver.findElement(By.xpath("//a[contains(@href,'"+catlandpage+"')]"));
		
		JavascriptExecutor js1 = (JavascriptExecutor)driver;
		js1.executeScript("arguments[0].click();", catlandpage1);
		Thread.sleep(5000);
		//Validate PLP presence and CatLand absence
		Assert.assertEquals(driver.findElements(By.id("plp-aty-tab")).isEmpty(),true);
		//Assert.assertEquals(driver.findElements(By.cssSelector("a.analyticsHeroLink")).isEmpty(),false);
	}
}
