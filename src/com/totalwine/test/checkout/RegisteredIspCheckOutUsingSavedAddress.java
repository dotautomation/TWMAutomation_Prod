package com.totalwine.test.checkout;

/*
 ****  Registered ISP Checkout using saved address
 ****  Work flow : 
 *  1. Access “Change location”
 *  2. Click on Choose a shipping destination
 *  3. Choose a state from the drop down and click on “Make this my shipping state”
 *  4. Access PDP for item
 *  5. Add to cart
 *  6. View Cart
 *  7. Checkout using a registered user which has save credit card
 *  8. Tab 1 Checkout ( Pickup Address)
 *  9. Tab 2 Checkout (Used Saved Billing Address)
 *  10. Tab 3 Checkout (Review and Submit)
 *  11. Order Confirmation Page
 *  12. Verify welcome message (We're happy you've joined the Total Discovery program, ---) displays

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
import com.totalwine.test.actions.Checkout;
import com.totalwine.test.actions.ShoppingCart;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;
import jxl.read.biff.BiffException;

public class RegisteredIspCheckOutUsingSavedAddress extends Browser {

	@DataProvider(name="CheckoutParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"Checkout", "RegisteredIspCheckOutUsingSavedAddressUAT");
        return(retObjArr);
    } 

	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();	
		 }  

	@Test (dataProvider = "CheckoutParameters")
	public void RegisteredIspCheckOutUsingSavedAddressTest (String Location,String StoreName,String PDP,String Quantity,String ShipOption,String ISPOption,String FirstName,
			String LastName,String Company,String Address1,String Address2,String City,String State,String Zip,String Email, 
			String Phone,String CreditCard,String ExpirationMonth,String ExpirationYear,String CVV,String Password,String ItemType)
					
					throws InterruptedException, BiffException, IOException {
		logger=report.startTest("Registered ISP Checkout using saved address");
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
	    Thread.sleep(5000);
	    
	    //  ** Shopping Cart
	    JavascriptExecutor js = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("li[data-val="+ISPOption+"]"))); 
	    Thread.sleep(5000);
	    JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js1.executeScript("arguments[0].click();", driver.findElement(By.id("checkout"))); 
	    PageLoad(driver); 

	    // **  Login
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys(Email);
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys(Password);
	    driver.findElement(By.id("checkoutSignIn")).click();
	    Thread.sleep(3000);

	    // **  Checkout Tab-1
	    WebElement scroll5 = driver.findElement(By.id("btnPickup")); // ** Scrolling down page
	    scroll5.sendKeys(Keys.PAGE_DOWN);
	    driver.findElement(By.id("btnPickup")).click();
	    Thread.sleep(2000);

	    // **  Checkout Tab-2 [ Checkout using saved billing address ]
	    driver.findElement(By.cssSelector(".shippingaddress-option>span")).click();
	    WebElement scroll6 = driver.findElement(By.cssSelector(".btn.btn-red.anContinue")); //  ** Scrolling down page
	    scroll6.sendKeys(Keys.PAGE_DOWN);
	    Thread.sleep(1000);
	    driver.findElement(By.cssSelector(".btn.btn-red.anContinue")).click();
	    Thread.sleep(2000);

	    // **  Checkout Tab-3
	    WebElement scroll4 = driver.findElement(By.cssSelector(".btn-red.btn-place-order.anPlaceOrder")); //  ** Scrolling down page
	    scroll4.sendKeys(Keys.PAGE_DOWN);
	    Thread.sleep(1000);
	    Checkout.GuestCheckoutTab3(driver);
	    
	    //  ** Order Confirmation
	    sAssert.assertEquals(driver.findElements(By.cssSelector("div.co-conf-thank-text")).isEmpty(),false, "If Order confirmation msg doesn't appear then test will fail");
//	    sAssert.assertAll();
	}
}