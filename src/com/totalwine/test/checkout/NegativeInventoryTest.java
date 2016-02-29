package com.totalwine.test.checkout;

/*
 * Checkout Workflow
 * Workflow:
 * 	1. Access PDP for item
 * 	2. Add to cart
 * 	3. View Cart
 * 	4. Initial Guest Checkout
 * 	5. Enter email address associated with guest checkout
 * 	6. Tab 1 Checkout
 *  7. Tab 2 Checkout (Billing Details)
 *  8. Tab 3 Checkout (Confirm)
 *  9. Order Confirmation Page
 *  
 * Technical Modules:
 * 	1. DataProvider: Checkout test input parameters
 * 	2. BeforeMethod (Test Pre-requisites):
 * 			Invoke webdriver
 * 			Maximize browser window
 * 	3. Test (Workflow)
 * 	4. AfterMethod
 * 			Take screenshot
 * 			Close webdriver
 * 	5. AfterClass
 * 			Quit webdriver
 */

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.testng.*;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.Keys;

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class NegativeInventoryTest extends Browser {

	@DataProvider(name="CheckoutParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"Checkout", "ISPcheckoutNeg");
        return(retObjArr);
    } 
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();	
		   
	  } 
	
	@BeforeTest
	public void delay() throws Exception {
		Thread.sleep(10000);
	}
	
	@Test (dataProvider = "CheckoutParameters",invocationCount=70)
	public void ISPCheckoutTest (String Location,String StoreName,String PDP,String ISPOption,String Quantity,String Email,String CreditCard,String ExpirationMonth,String ExpirationYear,
			String CVV,String FirstName,String LastName,String Company,String Address1,String Address2,String City,String State,String Zip) throws InterruptedException, BiffException, IOException {
		String[] ProductURL = {"/wine/red-wine/red-blend/bevan-proprietary-red-sugarloaf-mountain-oscar/p/129666750",
				"/wine/red-wine/merlot/duckhorn-merlot-napa/p/3809750",
				"/spirits/tequila/blanco-silver/sauza-hornitos-plata-tequila/p/103361750",
				"/wine/red-wine/rhone-blend/domaine-fontanyl-cotes-de-provence/p/100452750",
				"/wine/white-wine/chardonnay/muirwood-chardonnay-arroyo-seco/p/94390750",
				"/spirits/gin//tanqueray-gin/p/718175",
				"/beer/ale/stout/irish-dry-stout/guinness-extra-stout/p/3376126"
				};
		driver.get(ConfigurationFunctions.locationSet+Location);
		Thread.sleep(5000);
		if (driver.findElements(By.id("btnYes")).size()!=0)
			driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	    driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
	    Thread.sleep(5000);
	    Assert.assertEquals(StoreName, driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src")).getText());
	    ConfigurationFunctions.highlightElement(driver,driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src")));
		
		// Add to Cart
		driver.get(ConfigurationFunctions.accessURL+PDP);
		Thread.sleep(3000);
		String productId = driver.findElement(By.cssSelector("div.anProductId")).getText();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@id='"+productId+"'])[2]")).click();
		Thread.sleep (3000);

		//Add multiple items to cart
		for (String Product : ProductURL) {
			driver.get(ConfigurationFunctions.accessURL+Product);
			Thread.sleep(3000);
			productId = driver.findElement(By.cssSelector("div.anProductId")).getText();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//button[@id='"+productId+"'])[2]")).click();
			Thread.sleep (3000);
		}
		
	    driver.get(ConfigurationFunctions.accessURL+"/cart");
	    Thread.sleep(3000);
	    
	    // Shopping Cart
	    WebElement scroll = driver.findElement(By.id("checkout"));
	    scroll.sendKeys(Keys.PAGE_DOWN);
	    driver.findElement(By.cssSelector("#deliveryModeInStore > div.customselect > span.itemval")).click();
	    driver.findElement(By.cssSelector("li[data-val="+ISPOption+"]")).click();
	    driver.findElement(By.id("checkout")).click();
	    Thread.sleep(3000);
	    
	    // Next Page (Login/Checkout as Guest)
	    driver.findElement(By.cssSelector("#checkoutGuestForm > div.button-container > button.btn.btn-red")).click();
	    Thread.sleep(3000);
	    
	    // Checkout Tab 1
	    driver.findElement(By.id("shipping-email")).click();
	    driver.findElement(By.id("shipping-email")).clear();
	    driver.findElement(By.id("shipping-email")).sendKeys(Email);
	    driver.findElement(By.id("pickup-phoneNumber")).sendKeys("410-428-2222");
	    driver.findElement(By.id("btnPickup")).click();
	    Thread.sleep(5000);
	    
	    // Checkout Tab 2
	    driver.findElement(By.id("ssl_account_data")).click();
	    driver.findElement(By.id("ssl_account_data")).clear();
	    driver.findElement(By.id("ssl_account_data")).sendKeys(CreditCard);
	    driver.findElement(By.id("custom_card_type")).click();
	    driver.findElement(By.cssSelector("div[class=\"inputHolder month\"]")).click();
	    driver.findElement(By.xpath("//td[2]/div/div/div/div/div/div/ul/li[2]")).click();
	    driver.findElement(By.cssSelector("div[class=\"inputHolder year\"]")).click();
	    driver.findElement(By.xpath("//td[2]/div[2]/div/div/div/div/div/ul/li[3]")).click();
	    driver.findElement(By.id("ssl_cvv2cvc2")).clear();
	    driver.findElement(By.id("ssl_cvv2cvc2")).sendKeys(CVV);
	    driver.findElement(By.id("ssl_first_name")).clear();
	    driver.findElement(By.id("ssl_first_name")).sendKeys(FirstName);
	    driver.findElement(By.id("ssl_last_name")).clear();
	    driver.findElement(By.id("ssl_last_name")).sendKeys(LastName);
	    driver.findElement(By.id("ssl_avs_address")).clear();
	    driver.findElement(By.id("ssl_avs_address")).sendKeys(Address1);
	    driver.findElement(By.id("ssl_company")).clear();
	    driver.findElement(By.id("ssl_company")).sendKeys(Company);
	    driver.findElement(By.id("ssl_address2")).clear();
	    driver.findElement(By.id("ssl_address2")).sendKeys(Address2);
	    driver.findElement(By.id("ssl_city")).clear();
	    driver.findElement(By.id("ssl_city")).sendKeys(City);
	    driver.findElement(By.xpath("//table[@id='tblAddress']/tbody/tr[7]/td[2]/div/div/span")).click();
	    driver.findElement(By.cssSelector("li[data-val=\""+State+"\"]")).click();
	    driver.findElement(By.id("ssl_avs_zip")).clear();
	    driver.findElement(By.id("ssl_avs_zip")).sendKeys(Zip);
	    driver.findElement(By.name("process")).click();
	    Thread.sleep(10000);
	    
	    // Checkout Tab 3
	    driver.findElement(By.id("check_box_age")).click();
	    driver.findElement(By.xpath("//form[@id='placeOrderForm1']/section/div/button")).click();
	    Thread.sleep(10000);
	    
	    // Order Confirmation
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.co-conf-thank-text")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div")).isEmpty(),false);
	}
}
