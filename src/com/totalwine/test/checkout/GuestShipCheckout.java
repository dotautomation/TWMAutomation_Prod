package com.totalwine.test.checkout;

/*
 *  Guest Ship Checkout flow
 *  Work flow : 
 *  1. Access “Change location”
 *	2. Click on Choose a shipping destination
 *	3. Choose a state from the drop down and click on “Make this my shipping state”
 *	4. Access PDP for item
 *	5. Add to cart
 *	6. View Cart
 *	7. Initial Guest Checkout
 *	8. Tab 1 Checkout ( Delivery Address)
 *	9. Tab 2 Checkout (Billing)
 *	10. Tab 3 Checkout (Review and Submit)
 *	11. Order Confirmation Page

 * Technical Modules:
 * 	1. DataProvider: Checkout test input parameters
 * 	2. BeforeMethod (Test Pre-requisites):
 * 			Invoke WebDriver
 * 			Maximize browser window
 * 	3. Test (Work flow)
 * 	4. AfterMethod
 * 			Take screenshot, in case of failure
 * 			Close WebDriver
 * 	5. AfterClass
 * 			Quit WebDriver
 */

import java.io.IOException;
import jxl.read.biff.BiffException;
import org.testng.*;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.Keys;

import com.relevantcodes.extentreports.LogStatus;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class GuestShipCheckout extends Browser {

	@DataProvider(name="CheckoutParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"Checkout", "GuestShipCheckoutBF");
        return(retObjArr);
    } 
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();	
		 }  
	
	@Test (dataProvider = "CheckoutParameters")
	public void GuestShipCheckoutTest (String Location,String StoreName,String PDP,String Quantity,String ShipOption,String FirstName,
			String LastName,String Company,String Address1,String Address2,String City,String State,String Zip,String Email, 
			String Phone,String CreditCard,String ExpirationMonth,String ExpirationYear,String CVV)
					throws InterruptedException, BiffException, IOException {

		logger=report.startTest("Ship Registered Checkout Test");
		
		driver.get(ConfigurationFunctions.locationSet+Location);
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	    Assert.assertEquals(StoreName, driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src")).getText());
	    logger.log(LogStatus.PASS, "The site is configured for an Guest Ship Checkout order");
	    ConfigurationFunctions.highlightElement(driver,driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src")));
    
		// **  Add to Cart
		driver.get(ConfigurationFunctions.accessURL+PDP);
		Thread.sleep(3000);
		
		String productId = driver.findElement(By.cssSelector("div.anProductId")).getText();
		System.out.println(productId);
		Thread.sleep(2000);

	    driver.findElement(By.xpath("(//button[@id='"+productId+"'])[2]")).click(); //Clicking the ATC button
		Thread.sleep (3000);
		
	    driver.get(ConfigurationFunctions.accessURL+"/cart");
	    Thread.sleep(3000);

	    //  ** Shopping Cart
	    WebElement scroll = driver.findElement(By.id("checkout"));
	    scroll.sendKeys(Keys.PAGE_DOWN);
	    driver.findElement(By.id("zipCode")).click();
	    driver.findElement(By.id("zipCode")).clear();
	    driver.findElement(By.id("zipCode")).sendKeys(Zip);
	    driver.findElement(By.cssSelector("input.anZipForm")).click();
	    Thread.sleep(5000);

	    driver.findElement(By.cssSelector("#deliveryMode > div.customselect > span.itemval")).click();
	    driver.findElement(By.cssSelector("li[data-val="+ShipOption+"]")).click();
	    Thread.sleep(5000);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div[class=\"width-100 totalDotBorder noBorder ship-cost\"]")).isEmpty(),false); //Validate appearance of shipping cost
	    Assert.assertEquals(driver.findElements(By.cssSelector("input.anVoucherForm")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.name("qty")).isEmpty(),false);
	    driver.findElement(By.id("checkout")).click();
	    Thread.sleep(3000);

	    //  **  Next Page (Login/Checkout as Guest)
	    driver.findElement(By.cssSelector("#checkoutGuestForm > div.button-container > button.btn.btn-red")).click();
	    Thread.sleep(3000);

	    // **  Checkout Tab 1
	    driver.findElement(By.id("firstName")).clear();
	    driver.findElement(By.id("firstName")).sendKeys(FirstName);
	    driver.findElement(By.id("lastName")).clear();
	    driver.findElement(By.id("lastName")).sendKeys(LastName);
	    driver.findElement(By.id("companyName")).clear();
	    driver.findElement(By.id("companyName")).sendKeys(Company);
	    driver.findElement(By.id("addressLine1")).clear();
	    driver.findElement(By.id("addressLine1")).sendKeys(Address1);
	    driver.findElement(By.id("city")).clear();
	    driver.findElement(By.id("city")).sendKeys(City);
	    driver.findElement(By.id("shipping-email")).clear();
	    driver.findElement(By.id("shipping-email")).sendKeys(Email);
	    driver.findElement(By.id("shipping-phoneNumber")).clear();
	    driver.findElement(By.id("shipping-phoneNumber")).sendKeys(Phone);
	    driver.findElement(By.id("btnShipAuth1")).click();
	    Thread.sleep(5000);

	    // ** Checkout Tab 2
	    driver.findElement(By.id("ssl_account_data")).click();
	    driver.findElement(By.id("ssl_account_data")).clear();
	    driver.findElement(By.id("ssl_account_data")).sendKeys(CreditCard);
	    driver.findElement(By.cssSelector("div[class=\"inputHolder month\"]")).click();
	    driver.findElement(By.xpath("//td[2]/div/div/div/div/div/div/ul/li[2]")).click();
	    driver.findElement(By.cssSelector("div[class=\"inputHolder year\"]")).click();
	    driver.findElement(By.xpath("//td[2]/div[2]/div/div/div/div/div/ul/li[3]")).click();
	    driver.findElement(By.id("ssl_cvv2cvc2")).clear();
	    driver.findElement(By.id("ssl_cvv2cvc2")).sendKeys(CVV);
//	    driver.findElement(By.id("ssl_first_name")).clear();   // ** In UAT by default there information are selected
//	    driver.findElement(By.id("ssl_first_name")).sendKeys(FirstName);
//	    driver.findElement(By.id("ssl_last_name")).clear();
//	    driver.findElement(By.id("ssl_last_name")).sendKeys(LastName);
//	    driver.findElement(By.id("ssl_avs_address")).clear();
//	    driver.findElement(By.id("ssl_avs_address")).sendKeys(Address1);
//	    driver.findElement(By.id("ssl_company")).clear();
//	    driver.findElement(By.id("ssl_company")).sendKeys(Company);
//	    driver.findElement(By.id("ssl_address2")).clear();
//	    driver.findElement(By.id("ssl_address2")).sendKeys(Address2);
//	    driver.findElement(By.id("ssl_city")).clear();
//	    driver.findElement(By.id("ssl_city")).sendKeys(City);
//	    driver.findElement(By.xpath("//table[@id='tblAddress']/tbody/tr[7]/td[2]/div/div/span")).click();
//	    driver.findElement(By.cssSelector("li[data-val=\""+State+"\"]")).click();
//	    driver.findElement(By.id("ssl_avs_zip")).clear();
//	    driver.findElement(By.id("ssl_avs_zip")).sendKeys(Zip);
	    driver.findElement(By.name("process")).click();
	    Thread.sleep(10000);

	    // **  Checkout Tab 3
	    Assert.assertEquals(driver.findElements(By.cssSelector("span[data-attr=\"itemPrice_1\"]")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("span[data-attr=\"itemPrice_2\"]")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("span[class=\"price-text item-total anTax\"]")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("span[class=\"price-text item-total co-pr-item-total\"]")).isEmpty(),false);

	    driver.findElement(By.id("check_box_age")).click();
	    driver.findElement(By.cssSelector("button.btn-red.btn-place-order.anPlaceOrder")).click();
	    Thread.sleep(10000);

	    //  ** Order Confirmation
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.co-conf-thank-text")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div")).isEmpty(),false);
	}
	}