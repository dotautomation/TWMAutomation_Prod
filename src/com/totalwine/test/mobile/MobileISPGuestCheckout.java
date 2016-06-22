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
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class MobileISPGuestCheckout extends Browser {
	
		private String IP="72.66.119.61";
	
@Test
public void MobileISPGuestCheckoutTest () throws InterruptedException {
	logger=report.startTest("Mobile ISP Guest Checkout Test");
	driver.get(ConfigurationFunctions.locationSet+IP);
	Thread.sleep(5000);
	SiteAccess.ActionAccessMobileAgeGate(driver);
	Thread.sleep(3000);
	
	// **  By passing location
	driver.findElement(By.cssSelector("div.ChooseStoreButtons > button#btnNo.btn.btn-gray")).click();
	Thread.sleep(2000);
	SiteAccess.ActionAccessMobileAgeGate(driver);
	Thread.sleep(4000);
	
	//Navigate to test PDP
	driver.get(ConfigurationFunctions.accessURL+"/beer/lager/light-lager/coors-light/p/3283125?s=1108&igrules=true");
	Thread.sleep(3000);
	SiteAccess.ActionAccessMobileAgeGate(driver);
	Thread.sleep(2000);
	
	//Add to Cart and access cart
	JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	js1.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button.btnAddToCartPDP")));     
	Thread.sleep(3000);
	SiteAccess.ActionAccessMobileAgeGate(driver);
	Thread.sleep(2000);
	driver.findElement(By.cssSelector("em.mobile-cart")).click();
	Thread.sleep(3000);
	SiteAccess.ActionAccessMobileAgeGate(driver);
	Thread.sleep(3000);
	
	//Initiate Checkout
	// Shopping Cart
    driver.findElement(By.id("checkout")).sendKeys(Keys.PAGE_DOWN);
    Thread.sleep(2000);
    //driver.findElement(By.cssSelector("#deliveryModeInStore > div.customselect > span.itemval")).click();
    Assert.assertEquals(driver.findElements(By.cssSelector("input.anVoucherForm")).isEmpty(),false);
    Assert.assertEquals(driver.findElements(By.name("qty")).isEmpty(),false);
    driver.findElement(By.cssSelector("#checkout")).click();
	Thread.sleep(2000);
    
    // Next Page (Login/Checkout as Guest)
//    Assert.assertEquals(driver.findElements(By.id("j_username")).isEmpty(),false);
//    Assert.assertEquals(driver.findElements(By.id("j_password")).isEmpty(),false);
//    Assert.assertEquals(driver.findElements(By.cssSelector("div.checkStyle > label")).isEmpty(),false);
//    Assert.assertEquals(driver.findElements(By.id("forgotPasswordCheckout")).isEmpty(),false);
//    Assert.assertEquals(driver.findElements(By.id("checkoutSignIn")).isEmpty(),false);
    JavascriptExecutor executor = (JavascriptExecutor)driver;
    executor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#checkoutGuestForm > div > button.btn.btn-red.anCheckoutContinue")));
	Thread.sleep(2000);
    
    // Checkout Tab 1
    Assert.assertEquals(driver.findElements(By.cssSelector("a.instorepickup-tab")).isEmpty(),false);
    Assert.assertEquals(driver.findElements(By.cssSelector("a.analyticsEditCart")).isEmpty(),false);
    Assert.assertEquals(driver.findElements(By.cssSelector("section.instorepickup > h2")).isEmpty(),false);
    Assert.assertEquals(driver.findElements(By.cssSelector("section.giftmessage > div.checkStyle > label")).isEmpty(),false);
    Assert.assertEquals(driver.findElements(By.cssSelector("section.someoneelsepicking > div.checkStyle > label")).isEmpty(),false);
    Assert.assertEquals(driver.findElements(By.cssSelector("div.checkStyle > label")).isEmpty(),false);
    driver.findElement(By.id("shipping-email")).click();
    driver.findElement(By.id("shipping-email")).clear();
    driver.findElement(By.id("shipping-email")).sendKeys("mhossain@totalwine.com");
    driver.findElement(By.id("pickup-phoneNumber")).sendKeys("703-844-0877");
    driver.findElement(By.id("btnPickup")).click();
	Thread.sleep(2000);
    
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
    driver.findElement(By.id("ssl_first_name")).clear();
    driver.findElement(By.id("ssl_first_name")).sendKeys("DOT Mobile");
    driver.findElement(By.id("ssl_last_name")).clear();
    driver.findElement(By.id("ssl_last_name")).sendKeys("Tester");
    driver.findElement(By.id("ssl_avs_address")).clear();
    driver.findElement(By.id("ssl_avs_address")).sendKeys("6600 rockledge dr");
    driver.findElement(By.id("ssl_company")).clear();
    driver.findElement(By.id("ssl_company")).sendKeys("TWM");
    driver.findElement(By.id("ssl_address2")).clear();
    driver.findElement(By.id("ssl_address2")).sendKeys("");
    driver.findElement(By.id("ssl_city")).clear();
    driver.findElement(By.id("ssl_city")).sendKeys("Bethesda");
    driver.findElement(By.cssSelector("select")).click();
    driver.findElement(By.cssSelector("select#ssl_state > option[value=\"Maryland\"]")).click();
    driver.findElement(By.id("ssl_avs_zip")).clear();
    driver.findElement(By.id("ssl_avs_zip")).sendKeys("20817");
    driver.findElement(By.name("process")).click();
    Thread.sleep(10000);
    
    // Checkout Tab 3
    Assert.assertEquals(driver.findElements(By.cssSelector("a.review-tab")).isEmpty(),false);
    Assert.assertEquals(driver.findElements(By.cssSelector("li[class=\"co-rvw co-rvw-instore\"]")).isEmpty(),false);
    Assert.assertEquals(driver.findElements(By.cssSelector("li[class=\"co-rvw co-rvw-pymnt\"]")).isEmpty(),false);
    Assert.assertEquals(driver.findElements(By.cssSelector("li.co-rvw.co-rvw-billing")).isEmpty(),false);
    Assert.assertEquals(driver.findElements(By.cssSelector("span[data-attr=\"itemPrice_1\"]")).isEmpty(),false);
    Assert.assertEquals(driver.findElements(By.cssSelector("span[data-attr=\"itemPrice_2\"]")).isEmpty(),false);
    Assert.assertEquals(driver.findElements(By.cssSelector("span[class=\"price-text item-total anTax\"]")).isEmpty(),false);
    Assert.assertEquals(driver.findElements(By.cssSelector("span[class=\"price-text item-total co-pr-item-total\"]")).isEmpty(),false);
    
    driver.findElement(By.id("check_box_age")).click();
    driver.findElement(By.cssSelector("button.btn-red.btn-place-order.anPlaceOrder")).click();
    Thread.sleep(10000);
    
    // Order Confirmation
//    Assert.assertEquals(driver.findElements(By.cssSelector("div.co-conf-thank-text")).isEmpty(),false);
//    Assert.assertEquals(driver.findElements(By.cssSelector("div")).isEmpty(),false);	
	}
}