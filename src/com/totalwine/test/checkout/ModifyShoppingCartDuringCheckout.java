package com.totalwine.test.checkout;

/*
 ****  Modify shopping cart during Checkout
 ****  Work flow : 
 *  1. Access “Change location”
 *  2. Click on Choose a shipping destination
 *  3. Choose a state from the drop down and click on “Make this my shipping state”
 *  4. Access PDP for item
 *  5. Add to cart
 *  6. View Cart
 *  7. Modify item quantity and click on update button
 *  7. Initiate registered user Checkout
 *  8. Tab 1 Checkout ( Pickup Address)
 *  9. Tab 2 Checkout (Billing)
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
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;
import jxl.read.biff.BiffException;
import com.totalwine.test.actions.*;

public class ModifyShoppingCartDuringCheckout extends Browser {

	@DataProvider(name="CheckoutParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"Checkout", "ModifyShoppingCartDuringCheckoutUAT");
        return(retObjArr);
    } 

	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();	
		 }  

	@Test (dataProvider = "CheckoutParameters")
	public void ModifyShoppingCartDuringCheckoutTest (String Location,String StoreName,String PDP,String Quantity,String ShipOption,String ISPOption,String FirstName,
			String LastName,String Company,String Address1,String Address2,String City,String State,String Zip,String Email, 
			String Phone,String CreditCard,String ExpirationMonth,String ExpirationYear,String CVV,String Password,String ItemType)
					
		throws InterruptedException, BiffException, IOException {
		logger=report.startTest("Modifying Shopping Cart during checkout");
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
	    Thread.sleep(7000);

	    //  ** Shopping Cart Modification during checkout  		    
	    WebElement scroll = driver.findElement(By.cssSelector("form#updateCartForm0 > input#qty.cart-qty.numonly"));
	    scroll.sendKeys(Keys.PAGE_DOWN);   // ** Scrolling page down upto the element
	    driver.findElement(By.cssSelector("form#updateCartForm0 > input#qty.cart-qty.numonly")).clear();
	    driver.findElement(By.cssSelector("form#updateCartForm0 > input#qty.cart-qty.numonly")).sendKeys(Quantity);

	    WebElement scroll2 = driver.findElement(By.id("checkout"));   // ** Scrolling page down upto the element
	    scroll2.sendKeys(Keys.PAGE_DOWN);

	    JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js1.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div#overview-qty.qty > form#updateCartForm0 > a.js-update-qty > em.update-icon-btn.icon")));
	    Thread.sleep(2000);

	    WebElement scroll3 = driver.findElement(By.id("checkout")); // ** Scrolling page down upto the element
	    scroll3.sendKeys(Keys.PAGE_DOWN);  

	    JavascriptExecutor js2 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js2.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("li[data-val="+ISPOption+"]")));
	    
	    JavascriptExecutor js3 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js3.executeScript("arguments[0].click();", driver.findElement(By.id("checkout")));
	    Thread.sleep(3000);

	    // **  Login
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys(Email);
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys(Password);
	    driver.findElement(By.id("checkoutSignIn")).click();
	    Thread.sleep(3000);

	    // **  Checkout Tab-1
	    WebElement scroll5 = driver.findElement(By.id("btnPickup")); //  ** Scrolling down page
	    scroll5.sendKeys(Keys.PAGE_DOWN);
	    
	    driver.findElement(By.id("btnPickup")).click();
	    Thread.sleep(2000);

	    // **  Checkout Tab-2
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
	    
	    driver.findElement(By.id("check_box_age")).click();
	    
	    driver.findElement(By.cssSelector(".btn-red.btn-place-order.anPlaceOrder")).click();
	    Thread.sleep(2000);
	}
}