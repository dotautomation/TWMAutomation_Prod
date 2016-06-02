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
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;
import com.totalwine.test.actions.*;

public class GuestShipCheckout extends Browser {

	@DataProvider(name="CheckoutParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"Checkout", "GuestShipCheckoutUAT");
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

		logger=report.startTest("Guest Ship Checkout");
		
		driver.get(ConfigurationFunctions.locationSet+Location);
		PageLoad(driver); // Will not trigger the next control until loading the page
		
		//** By Passing Age Gate and Welcome Modal
		Checkout.AgeGateWelcome(driver);
		PageLoad(driver); // Will not trigger the next control until loading the page
		
	    // **  Selecting a product from PDP
		driver.get(ConfigurationFunctions.accessURL+PDP);
		Thread.sleep(7000);
		PageLoad(driver); // Will not trigger the next control until loading the page
				
		// **  Adding item to Cart
		ShoppingCart.ATC(driver);
		Thread.sleep(3000);
	    driver.get(ConfigurationFunctions.accessURL+"/cart");
	    Thread.sleep(7000);
	    PageLoad(driver); // Will not trigger the next control until loading the page

	    //  ** Shopping Cart
	    JavascriptExecutor js = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js.executeScript("arguments[0].click();", driver.findElement(By.id("zipCode")));  
	    driver.findElement(By.id("zipCode")).clear();
	    driver.findElement(By.id("zipCode")).sendKeys(Zip);
	    PageLoad(driver); 
	    driver.findElement(By.cssSelector("input.anZipForm")).click();
	    Thread.sleep(9000);
	    PageLoad(driver); 
	    driver.findElement(By.cssSelector("#deliveryMode > div.customselect > span.itemval")).click();
	    Thread.sleep(7000);
	    driver.findElement(By.cssSelector("li[data-val="+ShipOption+"]")).click();
	    Thread.sleep(7000);
	    JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js1.executeScript("arguments[0].click();", driver.findElement(By.id("checkout"))); 
	    Thread.sleep(5000);
	    PageLoad(driver); 
  
	    //  **  Next Page (Login/Checkout as Guest)
	    JavascriptExecutor js3 = (JavascriptExecutor)driver;  // Finding out elements that are out of site
	    js3.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#checkoutGuestForm > div.button-container > button.btn.btn-red")));     
	    Thread.sleep(3000);
	    PageLoad(driver); 

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
	    Checkout.GuestCheckoutTab3(driver);

	    //  ** Order Confirmation
//	    Assert.assertEquals(driver.findElements(By.cssSelector("div.co-conf-thank-text")).isEmpty(),false, "If Order confirmation msg doesn't appear then test will fail");
		}
	}