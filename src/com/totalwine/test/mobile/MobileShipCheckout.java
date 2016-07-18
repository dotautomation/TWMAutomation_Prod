package com.totalwine.test.mobile;

/*
 * Mobile Checkout Workflow
 * Workflow:
 * 	1. Access the Mobile PDP for item via the mobile homepage/PLP
 * 	2. Add to cart
 * 	3. View Cart
 * 	4. Initiate Guest Checkout
 * 	5. Enter email address associated with guest checkout
 * 	6. Tab 1 Checkout
 *  7. Tab 2 Checkout (Billing Details)
 *  8. Tab 3 Checkout (Confirm)
 *  9. Order Confirmation Page
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

import org.testng.*;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class MobileShipCheckout extends Browser {
	
		private String IP="66.230.105.38";
	
	@Test
	public void MobileShipCheckoutTest () throws InterruptedException {
		logger=report.startTest("Mobile Ship Checkout Test");
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
		
		//Navigate to test PDP
		driver.findElement(By.cssSelector("a.btn.btn-red.analyticsLinkComp[title=Wine]")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("a.analyticsProductName[href*=\"142211750\"]")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.cssSelector("a.analyticsProductName[href*=\"142211750\"]")).click();
		//driver.get(ConfigurationFunctions.accessURL+"/wine/red-wine/cabernet-sauvignon/iter-cabernet-sauvignon-napa-valley/p/142211750");
		Thread.sleep(3000);
		
		//Add to Cart and access cart
		driver.findElement(By.cssSelector("button.btnAddToCartPDP")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("em.mobile-cart")).click();
		Thread.sleep(3000);
		
		//Enter shipping zip and select shipping method
		driver.findElement(By.cssSelector("input.cart-qty.numonly")).clear();
		driver.findElement(By.cssSelector("input.cart-qty.numonly")).sendKeys("6");
		driver.findElement(By.cssSelector("a.js-update-qty")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input#zipCode")).clear();
		driver.findElement(By.cssSelector("input#zipCode")).sendKeys("99501");
		driver.findElement(By.cssSelector("input.anZipForm")).click();
		Thread.sleep(5000);
		new Select(driver.findElement(By.id("deliveryModeSelect"))).selectByVisibleText("Overnight");
		Thread.sleep(6000);
		
		//Initiate Checkout
		 // Shopping Cart
	    driver.findElement(By.id("checkout")).sendKeys(Keys.ARROW_DOWN);
	    driver.findElement(By.id("checkout")).click();
	    Thread.sleep(5000);
	    
	    // Next Page (Login/Checkout as Guest)
	    Assert.assertEquals(driver.findElements(By.id("j_username")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.id("j_password")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.checkStyle > label")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.id("forgotPasswordCheckout")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.id("checkoutSignIn")).isEmpty(),false);
	    JavascriptExecutor executor = (JavascriptExecutor)driver;
	    executor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#checkoutGuestForm > div > button.btn.btn-red.anCheckoutContinue")));
	    Thread.sleep(3000);
	    
	    // Checkout Tab 1
	    Assert.assertEquals(driver.findElements(By.cssSelector("a.shipping-tab")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("a.analyticsEditCart")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("section.instorepickup > h2")).isEmpty(),true); //Assert that ISP form is not displayed
	    Assert.assertEquals(driver.findElements(By.cssSelector("section.giftmessage > div.checkStyle > label")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.checkStyle > label")).isEmpty(),false);
	    driver.findElement(By.id("shipping-email")).click();
	    driver.findElement(By.id("shipping-email")).clear();
	    driver.findElement(By.id("shipping-email")).sendKeys("rsud@live.com");
	    driver.findElement(By.id("shipping-phoneNumber")).sendKeys("410-428-2222");
	    driver.findElement(By.id("firstName")).clear();
	    driver.findElement(By.id("firstName")).sendKeys("Automated");
	    driver.findElement(By.id("lastName")).clear();
	    driver.findElement(By.id("lastName")).sendKeys("Tester");
	    driver.findElement(By.id("companyName")).clear();
	    driver.findElement(By.id("companyName")).sendKeys("TWM");
	    driver.findElement(By.id("addressLine1")).clear();
	    driver.findElement(By.id("addressLine1")).sendKeys("825 W 4th Ave");
	    driver.findElement(By.id("city")).clear();
	    driver.findElement(By.id("city")).sendKeys("Anchorage");
	    driver.findElement(By.id("btnShipAuth1")).click();
	    Thread.sleep(3000);
	    
	    
	    //driver.findElement(By.id("btnPickup")).click();
	    //Thread.sleep(5000);
	    
	    // Checkout Tab 2
	    Assert.assertEquals(driver.findElements(By.cssSelector("a.billing-tab")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("span.hover.icon-que")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("a.analyticsEditCart")).isEmpty(),false);
	    driver.findElement(By.id("ssl_account_data")).click();
	    driver.findElement(By.id("ssl_account_data")).clear();
	    driver.findElement(By.id("ssl_account_data")).sendKeys("4124939999999990");
	    driver.findElement(By.id("custom_card_type")).click();
	    driver.findElement(By.cssSelector("div[class=\"inputHolder month\"]")).click();
	    driver.findElement(By.cssSelector("select#expiryMonth > option[value=\"01\"]")).click();
	    driver.findElement(By.cssSelector("div[class=\"inputHolder year\"]")).click();
	    driver.findElement(By.cssSelector("select#expiryYear > option[value=\"18\"]")).click();
	    driver.findElement(By.id("ssl_cvv2cvc2")).clear();
	    driver.findElement(By.id("ssl_cvv2cvc2")).sendKeys("123");
	    driver.findElement(By.id("billingaddress")).click();
	    Thread.sleep(3000);
	    //Assert.assertEquals(driver.findElement(By.id("ssl_last_name")).getText(),"Tester"); //Validate that last name is carried over
	    //Assert.assertEquals(driver.findElement(By.id("ssl_avs_address")).getText(),"825 W 4th Ave"); //Validate that address is carried over
	    driver.findElement(By.name("process")).click();
	    Thread.sleep(10000);
	    
	    // Checkout Tab 3
	    Assert.assertEquals(driver.findElements(By.cssSelector("a.review-tab")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("li[class=\"co-rvw co-rvw-instore\"]")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("li[class=\"co-rvw co-rvw-pymnt\"]")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("li[class=\"co-rvw\"]")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("span[data-attr=\"itemPrice_1\"]")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("span[data-attr=\"itemPrice_2\"]")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("span[class=\"price-text item-total anTax\"]")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("span[class=\"price-text item-total co-pr-item-total\"]")).isEmpty(),false);
	    
	    driver.findElement(By.id("check_box_age")).click();
	    driver.findElement(By.cssSelector("button.btn-red.btn-place-order.anPlaceOrder")).click();
	    Thread.sleep(10000);
	    
	    // Order Confirmation
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.co-conf-thank-text")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div")).isEmpty(),false);
	}
}
