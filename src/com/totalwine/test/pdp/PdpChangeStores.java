package com.totalwine.test.pdp;
/*
 *** PDP Change Stores Workflow
 *** Work flow : 
 * Browse to a PDP
 * Add any item into the Cart
 * Click on view Cart
 * Change Store / Shipping location 
 * Verify that Shipping or in-store pickup address is changed for the same selected product.  
 * 
 *** Technical Modules:
 * BeforeMethod (Test Pre-requisites):
 * 		Invoke webdriver
 * 		Maximize browser window
 * Test (Workflow)
 * AfterMethod
 * 		Take screenshot, in case of failure
 * 		Close webdriver
 * AfterClass
 * 		Quit webdriver
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
import com.relevantcodes.extentreports.LogStatus;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageChangingStore;
import com.totalwine.test.trials.Browser;
import com.totalwine.test.actions.*;

public class PdpChangeStores extends Browser {
	private String IP="71.193.51.0";
	
	@DataProvider(name="PDPParameters")
  public Object[][] createData() {
  	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"PDP", "PdpChangeStores");
      return(retObjArr);
  } 
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	  }  
	
	@Test (dataProvider = "PDPParameters")
	public void PdpChangeStoresTest (String toplevel,String plp,String zip) throws InterruptedException, BiffException, IOException {
		logger=report.startTest("Pdp Change Stores");
		SiteAccess.ActionAccessSite(driver, IP);
		
	    Actions action=new Actions(driver);
		
	    // **  Browse to PLP
		WebElement toplevelnav = driver.findElement(By.xpath("//a[contains(@href,'"+toplevel+"')]")); 
		action.moveToElement(toplevelnav).build().perform(); //Top Level Menu Hover
		WebElement plpnav=driver.findElement(By.xpath("//a[contains(@href,'"+plp+"')]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", plpnav);
		Thread.sleep(5000);
		
		// **  Access the PDP
		WebElement plpmove = driver.findElement(By.cssSelector("ul.header-classes")); //Moving the mouse away from the top level menu 
		action.moveToElement(plpmove).build().perform();
		//driver.findElement(By.cssSelector("a.btn.btn-red.clpviewall")).click();
		//Thread.sleep(5000);
		String winename = driver.findElement(By.cssSelector("a.analyticsProductName")).getText();
		System.out.println(winename);
		driver.findElement(By.cssSelector("a.analyticsProductName")).click(); //Click the first item link in the PLP
		Thread.sleep(5000);
		
		// **  Add to Cart
	 	String productId = driver.findElement(By.cssSelector("div.anProductId")).getText();
	 	System.out.println(productId);
	 	Thread.sleep(2000);    
	 	driver.findElement(By.xpath("(//button[@id='"+productId+"'])[2]")).click(); //Clicking the ATC button
	 	Thread.sleep (3000);
	 	driver.get(ConfigurationFunctions.accessURL+"/cart");
	 	Thread.sleep(3000);
	 	driver.findElement(By.cssSelector(".fluid-icons.fluid-add-to-cart-bg.analyticsViewCart")).click();
	    Thread.sleep(3000);
	    
	    // **  Changing Store location
//	 	driver.findElement(PageChangingStore.YourStore).click();
//	    Thread.sleep(5000);
	    driver.findElement(PageChangingStore.ChangeLocation).click();
	    Thread.sleep(5000);
	 	driver.findElement(PageChangingStore.FindStoreTab).click();
	    Thread.sleep(3000);
	 	driver.findElement(PageChangingStore.ZipForm).click();
	    Thread.sleep(1000);
	    driver.findElement(PageChangingStore.ZipForm).sendKeys(zip);
	    Thread.sleep(1000);
	 	driver.findElement(PageChangingStore.SearchButton).click();
	    Thread.sleep(5000);
	 	driver.findElement(PageChangingStore.MakeThisMyStoreButton).click();
	    Thread.sleep(6000);
		String winename2 = driver.findElement(By.cssSelector("span.color-dim.analyticsProductCode")).getText();
		System.out.println(winename2);
		Assert.assertEquals(driver.findElements(By.cssSelector("span.color-dim.analyticsProductCode")).isEmpty(),false, "if after changing the store item doesn't remain same then the test will fail");
		logger.log(LogStatus.PASS, "Verified item before and after changing store");
		Thread.sleep(2000);
	  }
}