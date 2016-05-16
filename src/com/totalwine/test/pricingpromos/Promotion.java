package com.totalwine.test.pricingpromos;

/*
 * Promotions Workflow
 * Workflow:
 * 	1. 
 *  2.
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


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

//Promo Code in Bugfix 1221: $5 off $50 worth of WD Chardonnay, except for items ending in .97

public class Promotion extends Browser {

	private String IP="98.169.134.0";

	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	}  

	@Test //Eligible WD Item
	public void EligiblePromotion () throws InterruptedException {
		logger=report.startTest("Promotions Test");
		SiteAccess.ActionAccessSite(driver, IP);
    
    	//Add eligible item to cart
    	driver.get(ConfigurationFunctions.accessURL+"/wine/white-wine/chardonnay/cloud-break-chardonnay/p/110892750");
    	Thread.sleep(3000);
		String productId = driver.findElement(By.cssSelector("div.anProductId")).getText();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@id='"+productId+"'])[2]")).click();
		Thread.sleep(3000);
		
	    //Access cart and apply promotion
		driver.get(ConfigurationFunctions.accessURL+"/cart");
	    Thread.sleep(5000);
	    driver.findElement(By.name("qty")).clear();
	    driver.findElement(By.name("qty")).sendKeys("12");
	    
	    WebElement scroll_Country = driver.findElement(By.id("voucherCode"));
	    scroll_Country.sendKeys(Keys.ARROW_DOWN);
	    scroll_Country.sendKeys(Keys.ARROW_DOWN);
	    scroll_Country.sendKeys(Keys.ARROW_DOWN);
	    scroll_Country.sendKeys(Keys.ARROW_DOWN);
	    
	    driver.findElement(By.cssSelector("a.js-update-qty > span")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.id("voucherCode")).sendKeys("1221");
	    driver.findElement(By.cssSelector("input.anVoucherForm")).click();
	    Thread.sleep(3000);
	    
	    //Validate successful application of promotion
//	    Assert.assertEquals("Your promotion has been applied successfully.", driver.findElement(By.cssSelector("p.error-msg")).getText());

	    //Empty the cart
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript("arguments[0].click();", driver.findElement(By.id("RemoveProduct_0")));
	    Thread.sleep(2000);
	}
	
	@Test  //Ineligible WD Item
	public void IneligibleVarietalPromotion () throws InterruptedException {
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	    //driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
	    //Thread.sleep(5000);

	    //Add eligible item to cart
    	driver.get(ConfigurationFunctions.accessURL+"/wine/red-wine/cabernet-sauvignon/radius-cabernet/p/109682750");
    	Thread.sleep(3000);
		String productId = driver.findElement(By.cssSelector("div.anProductId")).getText();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@id='"+productId+"'])[2]")).click();
		Thread.sleep(5000);
		
	    //Access cart and apply promotion
		driver.get(ConfigurationFunctions.accessURL+"/cart");
	    Thread.sleep(3000);
	    driver.findElement(By.name("qty")).clear();
	    driver.findElement(By.name("qty")).sendKeys("6");
	    
	    WebElement scroll_Country = driver.findElement(By.id("voucherCode"));
	    scroll_Country.sendKeys(Keys.ARROW_DOWN);
	    scroll_Country.sendKeys(Keys.ARROW_DOWN);
	    scroll_Country.sendKeys(Keys.ARROW_DOWN);
	    scroll_Country.sendKeys(Keys.ARROW_DOWN);
	    
	    driver.findElement(By.cssSelector("a.js-update-qty > span")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.id("voucherCode")).sendKeys("1221");
	    driver.findElement(By.cssSelector("input.anVoucherForm")).click();
	    Thread.sleep(3000);
	    
	    //Validate successful application of promotion
	    Assert.assertEquals("Your order doesn't qualify for this promotion. Please check your order to ensure that it meets the requirements.", driver.findElement(By.cssSelector("p.error-msg")).getText());

	    //Empty the cart
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript("arguments[0].click();", driver.findElement(By.id("RemoveProduct_0")));
	    Thread.sleep(2000);
	}
	
	@Test //Ineligible .97 Item
	public void Ineligible97Promotion () throws InterruptedException {
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	    //driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
	    //Thread.sleep(5000);
	    
	    //Add eligible item to cart
    	driver.get(ConfigurationFunctions.accessURL+"/wine/white-wine/chardonnay/kendall-jackson-chardonnay/p/2403750");
    	Thread.sleep(3000);
		String productId = driver.findElement(By.cssSelector("div.anProductId")).getText();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@id='"+productId+"'])[2]")).click();
		Thread.sleep(5000);
		
	    //Access cart and apply promotion
		driver.get(ConfigurationFunctions.accessURL+"/cart");
	    Thread.sleep(3000);
	    driver.findElement(By.name("qty")).clear();
	    driver.findElement(By.name("qty")).sendKeys("6");
		    WebElement scroll_Country = driver.findElement(By.id("voucherCode"));
		    scroll_Country.sendKeys(Keys.ARROW_DOWN);
		    scroll_Country.sendKeys(Keys.ARROW_DOWN);
		    scroll_Country.sendKeys(Keys.ARROW_DOWN);
		    scroll_Country.sendKeys(Keys.ARROW_DOWN);
	    driver.findElement(By.cssSelector("a.js-update-qty > span")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.id("voucherCode")).sendKeys("1221");
	    driver.findElement(By.cssSelector("input.anVoucherForm")).click();
	    Thread.sleep(3000);
	    
	    //Validate successful application of promotion
	    Assert.assertEquals("Your order doesn't qualify for this promotion. Please check your order to ensure that it meets the requirements.", driver.findElement(By.cssSelector("p.error-msg")).getText());

	    //Empty the cart
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript("arguments[0].click();", driver.findElement(By.id("RemoveProduct_0")));
	    Thread.sleep(2000);
	}
	
	@Test //Ineligible Category
	public void IneligibleCategoryPromotion () throws InterruptedException {
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	    //driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
	    //Thread.sleep(5000);
	    
    	//Add ineligible beer item to cart
    	driver.get(ConfigurationFunctions.accessURL+"/beer/lager/euro-pale-lager/heineken/p/3380128");
    	Thread.sleep(3000);
		String productId = driver.findElement(By.cssSelector("div.anProductId")).getText();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@id='"+productId+"'])[2]")).click();
		Thread.sleep(5000);
		
	    //Access cart and apply promotion
		driver.get(ConfigurationFunctions.accessURL+"/cart");
	    Thread.sleep(3000);
	    driver.findElement(By.name("qty")).clear();
	    driver.findElement(By.name("qty")).sendKeys("6");
	    
	    WebElement scroll_Country = driver.findElement(By.id("voucherCode"));
	    scroll_Country.sendKeys(Keys.ARROW_DOWN);
	    scroll_Country.sendKeys(Keys.ARROW_DOWN);
	    scroll_Country.sendKeys(Keys.ARROW_DOWN);
	    scroll_Country.sendKeys(Keys.ARROW_DOWN);
	    
	    driver.findElement(By.cssSelector("a.js-update-qty > span")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.id("voucherCode")).sendKeys("1221");
	    driver.findElement(By.cssSelector("input.anVoucherForm")).click();
	    Thread.sleep(3000);
	    
	    //Validate successful application of promotion
	    Assert.assertEquals("Your order doesn't qualify for this promotion. Please check your order to ensure that it meets the requirements.", driver.findElement(By.cssSelector("p.error-msg")).getText());

	    //Empty the cart
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript("arguments[0].click();", driver.findElement(By.id("RemoveProduct_0")));
	    Thread.sleep(2000);
	}
	
	@Test  //Price restriction
	public void InEligiblePricePromotion () throws InterruptedException {
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	    //driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
	    //Thread.sleep(5000);
	   
    	//Add eligible item, but not meeting the total threshold (total price < $50)
    	driver.get(ConfigurationFunctions.accessURL+"/wine/white-wine/chardonnay/cloud-break-chardonnay/p/110892750");
    	Thread.sleep(3000);
		String productId = driver.findElement(By.cssSelector("div.anProductId")).getText();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@id='"+productId+"'])[2]")).click();
		Thread.sleep(5000);
		
	    //Access cart and apply promotion
		driver.get(ConfigurationFunctions.accessURL+"/cart");
	    Thread.sleep(3000);
	    driver.findElement(By.name("qty")).clear();
	    driver.findElement(By.name("qty")).sendKeys("2");
	    
	    WebElement scroll_Country = driver.findElement(By.id("voucherCode"));
	    scroll_Country.sendKeys(Keys.ARROW_DOWN);
	    scroll_Country.sendKeys(Keys.ARROW_DOWN);
	    scroll_Country.sendKeys(Keys.ARROW_DOWN);
	    scroll_Country.sendKeys(Keys.ARROW_DOWN);
	    
	    driver.findElement(By.cssSelector("a.js-update-qty > span")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.id("voucherCode")).sendKeys("1221");
	    driver.findElement(By.cssSelector("input.anVoucherForm")).click();
	    Thread.sleep(3000);
	    
	    //Validate successful application of promotion
	    Assert.assertEquals("Your order doesn't qualify for this promotion. Please check your order to ensure that it meets the requirements.", driver.findElement(By.cssSelector("p.error-msg")).getText());

	    //Empty the cart
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript("arguments[0].click();", driver.findElement(By.id("RemoveProduct_0")));
	    Thread.sleep(2000);
	}
}
