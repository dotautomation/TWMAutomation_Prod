package com.totalwine.test.checkout;
/*
 *  Creating account after Guest Checkout flow
 *  Work flow : 
 *  1. Access “Change location”
 *  2. Click on Choose a shipping destination
 *  3. Choose a state from the drop down and click on “Make this my shipping state”
 *  4. Access PDP for item
 *  5. Add to cart
 *  6. View Cart
 *  7. Initial Guest Checkout
 *  8. Tab 1 Checkout ( Delivery Address)
 *  9. Tab 2 Checkout (Billing)
 *  10. Tab 3 Checkout (Review and Submit)
 *  11. Order Confirmation Page
 *  12. On the right middle , click on “Create Account”
 *  13. In the “Sign up now” window fillup email, password, confirm password, preferred store, and then click on save button. 
 *  14. Fill-up all required fields and click on register
 *  15. Verify welcome message (We're happy you've joined the Total Discovery program, ---) displays

 * Technical Modules:
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
import java.util.Random;
import jxl.read.biff.BiffException;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;
import com.totalwine.test.actions.*;

public class CreateAccountAfterGuestCheckout extends Browser {

	@DataProvider(name="CheckoutParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"Checkout", "CreateAccountAfterGuestCheckoutUAT");
        return(retObjArr);
    } 
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();	
		 }  
	
	@Test (dataProvider = "CheckoutParameters")
	public void CreateAccountAfterGuestCheckoutTest (String Location,String StoreName,String PDP,String Quantity,String ShipOption,String FirstName,
			String LastName,String Company,String Address1,String Address2,String City,String State,String Zip,String Email, 
			String Phone,String CreditCard,String ExpirationMonth,String ExpirationYear,String CVV)
					
					throws InterruptedException, BiffException, IOException {
		
		Random rand = new Random();
	    int randomNum = rand.nextInt((1000 - 1) + 1) + 1;
	    int randomNum_2 = rand.nextInt((1000 - 1) + 1) + 1;
	    logger=report.startTest("Creating account after Guest Checkout");
	    
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
		Thread.sleep(2000);
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
	    Thread.sleep(6000);
	    PageLoad(driver); 
  
	    //  **  Next Page (Login/Checkout as Guest)
	    JavascriptExecutor js3 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
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

	    // *** Creating Random Email
	    driver.findElement(By.id("shipping-email")).clear();
	    driver.findElement(By.id("shipping-email")).sendKeys("autoemail_"+randomNum+"."+randomNum_2+"@totalwine.com");
	    	String email = driver.findElement(By.id("shipping-email")).getAttribute("value");
	    	System.out.println("Registered Email Address: "+email);

	    driver.findElement(By.id("shipping-emailmsg")).click();  // ** Need to Unsubscribe Marketing message only in UAT
	    Thread.sleep(1000);
	    
	    driver.findElement(By.id("shipping-phoneNumber")).clear();
	    driver.findElement(By.id("shipping-phoneNumber")).sendKeys(Phone);
	    
	    driver.findElement(By.id("btnShipAuth1")).click();
	    Thread.sleep(7000);

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
//	    driver.findElement(By.id("ssl_first_name")).clear();   // *** In UAT by default these information are selected
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
//	    driver.findElement(By.xpath("//table[@id='tblAddress']/tbody/tr[7]/td[2]/div/div/span")).click(); // ** Adding State from drop-down menu
//	    driver.findElement(By.cssSelector("li[data-val=\""+State+"\"]")).click();
//	    driver.findElement(By.id("ssl_avs_zip")).clear();
//	    driver.findElement(By.id("ssl_avs_zip")).sendKeys(Zip);
	    driver.findElement(By.name("process")).click();
	    Thread.sleep(5000);

	    // **  Checkout Tab 3
	    Checkout.GuestCheckoutTab3(driver);
	    Thread.sleep(5000);

	    //  ** Order Confirmation
	    sAssert.assertEquals(driver.findElements(By.cssSelector("div.co-conf-thank-text")).isEmpty(),false, "If Order confirmation msg doesn't appear then test will fail");

	    //  ** Creating Account
	    JavascriptExecutor js2 = (JavascriptExecutor)driver;  // Finding out elements that are out of site
	    js2.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#btnCreateAcc")));    

	    //  ** Checking for survey pop-up
	    Checkout.SurverPopup(driver);
	    driver.findElement(By.xpath(".//*[@id='co-pass']")).sendKeys("grapes123");
	    driver.findElement(By.xpath(".//*[@id='co-pass-re']")).sendKeys("grapes123");
	    
	    // ** Checking for survey pop-up
	    Checkout.SurverPopup(driver);
	    
	    // **Creating account after guest check out
	    WebElement element1 = driver.findElement(By.xpath(".//*[@id='frmCOCreateAcc']/table/tbody/tr[5]/td[2]/div/div/div/span/span"));  
        	new Actions(driver).moveToElement(element1).perform();   //  ** Selecting element from drop-down
        	element1.click();
	    Thread.sleep(2000);

	    driver.findElement(By.xpath(".//*[@id='frmCOCreateAcc']/table/tbody/tr[5]/td[2]/div/div/div/div/div/div[1]/ul/li[3]")).click();
	    Thread.sleep(2000);

	    driver.findElement(By.id("check_box_100")).click();
	    driver.findElement(By.id("check_box_101")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.id("btnCOSaveAuth")).click();
	    Thread.sleep(5000);
//	    Assert.assertEquals(driver.findElements(By.cssSelector(".ahp-welcomeHeading")).isEmpty(),false, "If account doesn't create then test will fail");
//	    sAssert.assertAll();
		}
	}