package com.totalwine.test.lare;

/*
 * LARE Rules
 * Workflow:
 * 	1. User entered location/selected store or ship-to-state
 *  2. Deep Link
 *  3. Profile Store set to Always Use
 *  4. Default Web Store
 * Technical Modules:
 * 	Inherited Class from Browser
 *  1. BeforeMethod (Test Pre-requisites):
 * 			Maximize browser window
 * 	2. Test (Workflow)
 * 	3. AfterMethod (from Browser)
 * 			Take screenshot, in case of failure
 * 			Close webdriver
 */

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.trials.Browser;

public class LARERules extends Browser {
	
	private String IP="71.193.51.0"; //Sacramento
	private String UnknownIP="10.150.16.1"; //Unknown IP
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	  }  
	
	@Test 
	public void LAREUserEnteredLocationTest () throws InterruptedException {
		logger=report.startTest("LARE User Entered Location Test");
		//Rule: User entered location/selected store or ship-to-state
		//Action: User accesses site and changes store via global store selector
		//Validation: Customer is shopping only in the globally selected store (store is 0.0 miles away in the Store Selector)
		connect(IP);
		driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src")).click();
		Thread.sleep(2000);
	    driver.findElement(By.cssSelector("div.header-location-nearby-stores.flyover > div.location-near-by-store-locator > table > tbody > tr > td > a.header-change-location")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.id("newStoreSearch_box")).clear();
	    driver.findElement(By.id("newStoreSearch_box")).sendKeys("21224");
	    driver.findElement(By.cssSelector("button.btn-red.btn.store-finder")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("button#changeStoreBtn")).click();
	    Thread.sleep(5000);
	    Assert.assertEquals(driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src")).getText(),"Towson (Beltway), MD","The site session wasn't correctly displayed");
	    
	    Actions action=new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
	    WebElement wineNav = driver.findElement(By.xpath("//a[contains(@href,'c0020')]")); 
		action.moveToElement(wineNav).build().perform(); //Top Level Menu Hover
		WebElement winePLPNav=driver.findElement(By.xpath("//a[contains(@href,'000002')]"));
		js.executeScript("arguments[0].click();", winePLPNav);
		Thread.sleep(5000);
		WebElement wineMove = driver.findElement(By.cssSelector("ul.header-classes")); //Moving the mouse away from the top level menu 
		action.moveToElement(wineMove).build().perform(); 
		//driver.findElement(By.xpath("//a[contains(@href,'000002?viewall=true')]")).click(); //For production since the SubCat Land page is setup
		//Thread.sleep(5000);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.inner-items-wrapper > ul > li.act > a > span.checkStyle > label")).getText(),"Towson (Beltway), MD (0.0 miles)","The site session wasn't correctly displayed");
	}
	
	@Test
	public void LAREDeepLinkTest () throws InterruptedException {
		//Rule: Deep Link
		//Action: User accesses site via a deep link containing the store information
		//Validation: Global store header should have the deep link's store
		logger=report.startTest("LARE Deep Link Test");
		connect(IP);
		driver.get(ConfigurationFunctions.accessURL+"/wine/white-wine/chardonnay/null/j-lohr-arroyo-vista-chardonnay/p/91517750?s=205"); //McLean, VA
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src")).getText(),"McLean, VA","The site session wasn't correctly displayed");
	}
		
	@Test
	public void LAREAlwaysUseProfileTest () throws InterruptedException {
		//Rule: Profile Store set to Always Use
		//Action: User accesses site and then logs in
		//Validation: Global store header changes to the profile store marked as always use (rsud@totalwine.com/grapes123)
		logger=report.startTest("LARE Always Use Profile Test");
		connect(IP);
		
		driver.findElement(PageGlobal.TopNavAccount).click();
		Thread.sleep(2000);
	    driver.findElement(By.linkText("Sign into your account")).click();
		Thread.sleep(3000);
		driver.switchTo().frame("iframe-signin-overlay");
		driver.findElement(By.id("j_username")).clear();
		driver.findElement(By.id("j_username")).sendKeys("rsud@live.com");
		driver.findElement(By.id("j_password")).clear();
		driver.findElement(By.id("j_password")).sendKeys("yoyo55");
		//driver.findElement(By.cssSelector("section#sign-in-overlay > div.sign-in-container > div.form-container > div.loginform-wrapper > div.form-left > input#j_username")).sendKeys("rsud@totalwine.com");
	    //driver.findElement(By.cssSelector("section#sign-in-overlay > div.sign-in-container > div.form-container > div.loginform-wrapper > div.form-right> input#j_password")).sendKeys("grapes123");
	    driver.findElement(By.cssSelector("button.btn.btn-red.anLoginSubmit")).click();
	    Thread.sleep(5000);
	    driver.switchTo().activeElement();
	    Assert.assertEquals(driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src")).getText(),"McLean, VA","The site session wasn't correctly displayed");
	}
	
	@Test
	public void LAREDWSTest () throws InterruptedException {
		//Rule: Default Web Store
		//Action: User accesses the site from outside the US or user's location cannot be determined
		//Validation: Global store header is DWS (1108)
		logger=report.startTest("LARE DWS Test");
		driver.get(ConfigurationFunctions.locationSet+UnknownIP);
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
		//Location Intercept
		Assert.assertEquals(driver.findElements(By.cssSelector("div.ChooseStoreButtons > button.btn.btn-gray")).isEmpty(),false,"Location intercept's \"No thanks\" wasn't found");
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.ChooseStoreButtons > button#btnSelectLocation")).isEmpty(),false,"Location intercept's Select button wasn't found");
	    //driver.findElement(By.cssSelector("div.ChooseStoreButtons > #btnNo")).click();
	    driver.findElement(By.cssSelector("div.ChooseStoreButtons > button.btn.btn-gray")).click();
	    //driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
	    Assert.assertEquals(driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src")).getText(),"Sacramento (Arden), CA","The site session wasn't correctly displayed");
	}

	public void connect(String Address) throws InterruptedException {
		SiteAccess.ActionAccessSite(driver, Address);
	}
}
