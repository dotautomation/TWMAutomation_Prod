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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class ISPCheckout extends Browser {
	public String locationSet = "http://twmuatwebserver:webserveruattwm@uat.totalwine.com/?remoteTestIPAddress=";
	/*public static WebDriver driver;
	ProfilesIni profile = new ProfilesIni();
	FirefoxProfile testProfile = profile.getProfile("WebDriver");*/

	@DataProvider(name="CheckoutParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"Checkout", "ISPcheckoutUAT");
        return(retObjArr);
    }
	
	@BeforeMethod
	  public void setUp() throws Exception {
		//driver = new FirefoxDriver(testProfile);
	    driver.manage().window().maximize();	
	  } 
	
	@Test (dataProvider = "CheckoutParameters")
	public void ISPCheckoutTest (String Location,String StoreName,String PDP,String ISPOption,String Quantity,String Email,String CreditCard,String ExpirationMonth,String ExpirationYear,
			String CVV,String FirstName,String LastName,String Company,String Address1,String Address2,String City,String State,String Zip) throws InterruptedException, BiffException, IOException {
		logger=report.startTest("ISP Guest Checkout Test");
	    SiteAccess.ActionAccessSite(driver, Location);
	    sAssert.assertEquals(driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src")).getText(),StoreName,"ISP Store name isn't correctly displayed");
	    ConfigurationFunctions.highlightElement(driver,driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src")));
		
		// Add to Cart
		driver.get(PDP);
		Thread.sleep(3000);
		String productId = driver.findElement(By.cssSelector("div.anProductId")).getText();
		Assert.assertEquals(driver.findElements(By.xpath("(//button[@id='"+productId+"'])[2]")).isEmpty(), false,"The ATC button isn't on the PDP indicating that the test item may be OOS");
		driver.findElement(By.xpath("(//button[@id='"+productId+"'])[2]")).click();
	    Thread.sleep (2000);
	    driver.get("http://twmuatwebserver:webserveruattwm@uat.totalwine.com/cart");
	    Thread.sleep(3000);
	    
	    // Shopping Cart
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#deliveryModeInStore > div.customselect > span.itemval")));
	    js.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("li[data-val="+ISPOption+"]")));
	    Assert.assertEquals(driver.findElements(By.cssSelector("input.anVoucherForm")).isEmpty(),false,"Promo code field isn't correctly displayed");
	    Assert.assertEquals(driver.findElements(By.name("qty")).isEmpty(),false,"Quantity field isn't correctly displayed");
	    Thread.sleep(2000);
	    js.executeScript("arguments[0].click()", driver.findElement(By.id("checkout")));
	    Thread.sleep(3000);
	    
	    // Next Page (Login/Checkout as Guest)
	    Assert.assertEquals(driver.findElements(By.id("j_username")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.id("j_password")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.checkStyle > label")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.id("forgotPasswordCheckout")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.id("checkoutSignIn")).isEmpty(),false);
	    driver.findElement(By.cssSelector("#checkoutGuestForm > div.button-container > button.btn.btn-red")).click();
	    Thread.sleep(3000);
	    
	    // Checkout Tab 1
	    sAssert.assertEquals(driver.findElements(By.cssSelector("a.instorepickup-tab")).isEmpty(),false,"The in store pickup tab title isn't correctly displayed on Tab 1");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("a.analyticsEditCart")).isEmpty(),false,"The Edit Cart link isn't displayed on Tab 2");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("section.instorepickup > h2")).isEmpty(),false,"In store pickup heading isn't correctly displayed");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("section.giftmessage > div.checkStyle > label")).isEmpty(),false,"Gift messaging section isn't correctly displayed");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("section.someoneelsepicking > div.checkStyle > label")).isEmpty(),false,"3rd party pickup section isn't correctly displayed");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("div.checkStyle > label")).isEmpty(),false);
	    driver.findElement(By.id("shipping-email")).click();
	    driver.findElement(By.id("shipping-email")).clear();
	    driver.findElement(By.id("shipping-email")).sendKeys(Email);
	    driver.findElement(By.id("pickup-phoneNumber")).sendKeys("410-428-2222");
	    driver.findElement(By.id("btnPickup")).click();
	    Thread.sleep(5000);
	    
	    // Checkout Tab 2
	    sAssert.assertEquals(driver.findElements(By.cssSelector("a.billing-tab")).isEmpty(),false,"The billing tab title isn't correctly displayed on Tab 2");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("span.hover.icon-que")).isEmpty(),false);
	    sAssert.assertEquals(driver.findElements(By.cssSelector("a.analyticsEditCart")).isEmpty(),false,"The Edit Cart link isn't displayed on Tab 2");
	    driver.findElement(By.id("ssl_account_data")).click();
	    driver.findElement(By.id("ssl_account_data")).clear();
	    driver.findElement(By.id("ssl_account_data")).sendKeys(CreditCard);
	    driver.findElement(By.id("custom_card_type")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("div[class=\"inputHolder month\"]")).click();
	    Thread.sleep(6000);	    
	    WebElement element1 = driver.findElement(By.xpath("//td[2]/div/div/div/div/div/div/ul/li[2]"));  
	    new Actions(driver).moveToElement(element1).perform();  
	    element1.click();
	    Thread.sleep(4000);
	    driver.findElement(By.cssSelector("div[class=\"inputHolder year\"]")).click();
	    Thread.sleep(5000);	    
	    WebElement element2 = driver.findElement(By.xpath("//td[2]/div[2]/div/div/div/div/div/ul/li[3]"));  
	    new Actions(driver).moveToElement(element2).perform();  
	    element2.click();
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
	    Thread.sleep(3000);
	    driver.findElement(By.cssSelector("li[data-val=\""+State+"\"]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.id("ssl_avs_zip")).clear();
	    driver.findElement(By.id("ssl_avs_zip")).sendKeys(Zip);
	    driver.findElement(By.name("process")).click();
	    Thread.sleep(10000);
	    
	    // Checkout Tab 3
	    sAssert.assertEquals(driver.findElements(By.cssSelector("a.review-tab")).isEmpty(),false,"The review tab isn't correctly displayed");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("li[class=\"co-rvw co-rvw-instore\"]")).isEmpty(),false,"In-store pickup section isn't displayed properly");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("li[class=\"co-rvw co-rvw-pymnt\"]")).isEmpty(),false,"Payment details section isn't displayed properly");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("li[class=\"co-rvw co-rvw-billing\"]")).isEmpty(),false,"Billing address section isn't displayed properly");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("span[data-attr=\"itemPrice_1\"]")).isEmpty(),false,"The item price isn't displayed correctly on Checkout Tab 3");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("span[data-attr=\"itemPrice_2\"]")).isEmpty(),false,"The item price isn't displayed correctly on Checkout Tab 3");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("span[class=\"price-text item-total anTax\"]")).isEmpty(),false,"The tax isn't displayed correctly on Checkout Tab 3");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("span[class=\"price-text item-total co-pr-item-total\"]")).isEmpty(),false,"The total order price isn't displayed correctly on Checkout Tab 3");
	    driver.findElement(By.id("check_box_age")).click();
	    driver.findElement(By.xpath("//form[@id='placeOrderForm1']/section/div/button")).click();
	    Thread.sleep(10000);
	    
	    // Order Confirmation
	    sAssert.assertEquals(driver.findElements(By.cssSelector("div.co-conf-help-link")).isEmpty(),false,"The help link isn't displayed on the Order confirmation page");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("div.co-conf-thank-text")).isEmpty(),false,"The thank-you text isn't displayed on the Order confirmation page");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("div")).isEmpty(),false);
//	    sAssert.assertAll();
	}
}