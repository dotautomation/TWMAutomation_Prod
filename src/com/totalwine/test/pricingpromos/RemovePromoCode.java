package com.totalwine.test.pricingpromos;

/*
 ****  Modify shopping cart during Checkout
 ****  Work flow : 
 *  1. First need to find out some promo code from  - HMC (Hybris Management Console) – http://uat.totalwine.com/hmc/hybris > Marketing ( left navigation) >
 *   		Promotions > Search –Attribute ( Available Online ) > Enabled (Yes) > Available online (Yes) > Click on Search > Click on any product >  
 *   			Click on TWM Attributes tab > See the Voucher Codes value ( Ex: 15chard)
 *  2. Add a product into the cart which has promotional offer [ can find out from /hmc/hybris  ]
 *  3. In the shopping cart page insert promo code. 
 *  4. After clicking on the submit button, confirmation msg (Your promotion has been applied successfully.) should be displayed.
 *  4. Click on Remove button below "Your discounts" section
 *  5. confirmation msg (Your promotion has been removed successfully.) should be displayed.
 *  6. Validate all confirmation message
 **** Technical Modules:
 * 	1. DataProvider: Checkout test input parameters
 * 	2. BeforeMethod (Test Pre-requisites):
 * 			Invoke WebDriver
 * 			Maximize browser window
 * 	3. Test (Workflow)
 * 	4. AfterMethod
 * 			Take screenshot, in case of failure
 * 			Close WebDriver
 * 	5. AfterClass
 * 			Quit WebDriver
 */

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;
import jxl.read.biff.BiffException;
import com.totalwine.test.actions.*;

public class RemovePromoCode extends Browser {

	@DataProvider(name="CheckoutParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"PricingPromos", "RemovePromoCodeUAT");
        return(retObjArr);
    } 

	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();	
		 }  

	@Test (dataProvider = "CheckoutParameters")
	public void RemovePromoCodeTest (String Location,String PDP,String PromoCode,String Quantity,String ShipOption,String ISPOption,String Email,String Password)
					
		throws InterruptedException, BiffException, IOException {
		logger=report.startTest("Adding and Removing Promo Code");
		driver.get(ConfigurationFunctions.locationSet+Location);
		Thread.sleep(5000);
		
		//** By Passing Age Gate and Welcome Modal
		Checkout.AgeGateWelcome(driver);

   	 	// **  Selecting a product from PDP
		driver.get(ConfigurationFunctions.accessURL+PDP);
		Thread.sleep(3000);

		// **  Adding item to Cart
		ShoppingCart.ATC(driver);
		Thread.sleep(3000);
	    driver.get(ConfigurationFunctions.accessURL+"/cart");
	    Thread.sleep(3000);

	    //  ** Adding Promo Code		    
	    WebElement scroll = driver.findElement(By.cssSelector("#voucherCode"));
	    scroll.sendKeys(Keys.PAGE_DOWN);   // ** Scrolling page down upto the element   
	    driver.findElement(By.cssSelector("#voucherCode")).clear();
	    driver.findElement(By.cssSelector("#voucherCode")).sendKeys(PromoCode);
	    JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js1.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#voucherForm > div.col-4 > input.anVoucherForm")));
	    Thread.sleep(2000);
	    
	    // ** Validate whether promo code successfully applied or not
//	    Assert.assertEquals("Your promotion has been applied successfully.", driver.findElement(By.cssSelector(".error-msg")).getText());

	    //  ** Removing Promo Code	
	    JavascriptExecutor js2 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js2.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".cart-remove>a")));
	    Thread.sleep(3000);
	    
	    // ** Validate whether promo code successfully removed or not
//	    sAssert.assertEquals(driver.findElements(By.cssSelector("p.error-msg")).isEmpty(),false, "Validate whether promo code successfully removed or not");  
	    Thread.sleep(3000);
//	    sAssert.assertAll();
	 }
}