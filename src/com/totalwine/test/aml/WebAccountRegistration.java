package com.totalwine.test.aml;

/*
 *  New User Registration using random credentials or credential from DB 
 *  Work flow : 
 *  1. Click on Sign In / Register link on the header
 *  2. Click on Need to create an account? "Start here" button
 *  3. Fill up all the required information in  "Your TotalWine.com account" page
 *  5. Click on the Register button
 *  6. Fillup form in "Tell us about yourself" page
 *  7. Click on "Save" button 
 *  8. Verify welcome message displays after account creation. 

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
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.trials.Browser;
import com.totalwine.test.actions.*;

public class WebAccountRegistration extends Browser {

	@DataProvider(name="CheckoutParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"Checkout", "Registration");
        return(retObjArr);
    } 
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();	
		 }  
	
	@Test (dataProvider = "CheckoutParameters")
	public void WebAccountRegistrationTest (String Location,String StoreName,String PDP,String Quantity,String ShipOption,String FirstName,
			String LastName,String Company,String Address1,String Address2,String City,String State,String Zip,String Email,String Password,
			String Phone,String CreditCard,String ExpirationMonth,String ExpirationYear,String CVV)
					
						throws InterruptedException, BiffException, IOException {
		
		logger=report.startTest("New User Registration using random credentials or credential from DB");
		
		//** Random E-mail Creation
		Random rand = new Random();
	    int randomNum = rand.nextInt((1000 - 1) + 1) + 1;
	    int randomNum_2 = rand.nextInt((1000 - 1) + 1) + 1;

		driver.get(ConfigurationFunctions.locationSet+Location);
		PageLoad(driver); // Will not trigger the next control until loading the page
		
		//** By Passing Age Gate and Welcome Modal
		Checkout.AgeGateWelcome(driver);
		PageLoad(driver); // Will not trigger the next control until loading the page
		
	    // ** Checking for survey pop-up
	    Checkout.SurverPopup(driver);
	    
	    
	    driver.findElement(PageGlobal.TopNavAccount).click();
	    Thread.sleep(2000);
	    driver.switchTo().frame(driver.findElement(By.id("iframe-signin-overlay")));
	    driver.findElement(PageGlobal.SignUp).click();
	    Thread.sleep(2000);
	
	    // ** Filling up "Create your account" page
	    driver.findElement(By.cssSelector("#firstName")).sendKeys(FirstName);
	    driver.findElement(By.cssSelector("#lastName")).sendKeys(LastName);
	    Thread.sleep(2000);

	    // *** Register Email from DB
//	    driver.findElement(By.cssSelector("#email")).sendKeys(Email);
	    
	    // *** Register Random Email
	    driver.findElement(By.cssSelector("#email")).sendKeys("autoemail_"+randomNum+"."+randomNum_2+"@totalwine.com");
    	String email = driver.findElement(By.cssSelector("#email")).getAttribute("value");
    	System.out.println("Registered Email Address: "+email);
    	
	    driver.findElement(By.cssSelector("#pwd")).sendKeys(Password);
	    driver.findElement(By.cssSelector("#phone")).sendKeys(Phone);
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("#shipToStateDropdown > div > div.dropdown.inst-state > div > span > i")).click();
	    Thread.sleep(4000);
	    WebElement element7 = driver.findElement(By.cssSelector(".undefined.undefined.anOption[data-val='US-VA']"));  
	    new Actions(driver).moveToElement(element7).perform();
	    Thread.sleep(2000);
	    element7.click();
	    Thread.sleep(4000);
	    driver.findElement(By.cssSelector("div.labelHolder.store > div > div > span > span")).click();
	    Thread.sleep(5000);
	    WebElement element8 = driver.findElement(By.cssSelector(".US-VA.anOption[data-val='205']"));  
	    new Actions(driver).moveToElement(element8).perform(); 
	    Thread.sleep(2000);
	    element8.click();
	    Thread.sleep(4000);
	    driver.findElement(By.cssSelector("#checkbox2")).click();
	    driver.findElement(By.cssSelector("#checkbox3")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("#btnnuregisteration")).click();
	    Thread.sleep(5000);

	    // Filling "Let's complete your Total Discovery profile" page
	    
	    driver.findElement(By.cssSelector("#address1")).sendKeys(Address1);
	    driver.findElement(By.cssSelector("#address2")).sendKeys(Address2);
	    WebElement scroll = driver.findElement(By.cssSelector("#city"));  
	    scroll.sendKeys(Keys.PAGE_DOWN); //  ** Scrolling down page
	    Thread.sleep(1000);
	    
	    // ** Checking for survey pop-up
	    Checkout.SurverPopup(driver);
	    
	    driver.findElement(By.cssSelector("#city")).sendKeys(City);
	    driver.findElement(By.cssSelector("div.labelHolder.state-drop > div > div > span > span")).click();
	    Thread.sleep(7000);
	    WebElement element9 = driver.findElement(By.cssSelector(".undefined.undefined.anOption[data-val='US-VA']"));  
	    new Actions(driver).moveToElement(element9).perform();
	    Thread.sleep(4000);
	    element9.click();
	    Thread.sleep(5000);
	    driver.findElement(By.cssSelector("#zipCode")).sendKeys(Zip);
	    driver.findElement(By.cssSelector("#phone")).sendKeys(Phone);
	    WebElement scroll3 = driver.findElement(By.cssSelector("#btnSaveAccountNew"));  
	    scroll3.sendKeys(Keys.PAGE_DOWN); //  ** Scrolling down page
	    Thread.sleep(1000);
	    driver.findElement(By.cssSelector("#c0020")).click();
	    driver.findElement(By.cssSelector("#btnSaveAccountNew")).click();
	    Thread.sleep(4000);  
	    Assert.assertEquals(driver.findElements(By.cssSelector(".ahp-heading")).isEmpty(),false, "If Account home doesn't shows up then test will fail");
		}
	}

//package com.totalwine.test.aml;
//
///*
// * Web Account Registration Workflow
// * Workflow:
// * 	1. Click the "Account" link in top nav
// * 	2. Assert the presence of all links in the popup screen
// * 	3. Click the "Sign Up" link and navigate to the Registration screen
// * 	4. Complete the form with registration information and submit the form
// * 	5. Validate the presence of elements on the registration confirmation screen
// * 	6. Complete preferences and save the information
// *  7. Validate the elements presented on the Account home page.
// *  8. Log out and attempt to re-login using the same credentials
// *  9. Validate the logged in state ensuring the credentials work.
// *  
// * Technical Modules:
// * 	1. BeforeMethod (Test Pre-requisites):
// * 			Invoke webdriver
// * 			Maximize browser window
// * 	2. Test (Workflow)
// * 	3. AfterMethod
// * 			Take screenshot, in case of failure
// * 			Close webdriver
// * 	4. AfterClass
// * 			Quit webdriver
// */
//
//import java.io.IOException;
//import jxl.read.biff.BiffException;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import com.totalwine.test.actions.SiteAccess;
//import com.totalwine.test.config.ConfigurationFunctions;
//import com.totalwine.test.pages.PageGlobal;
//import com.totalwine.test.trials.Browser;
//
//public class WebAccountRegistration extends Browser {
//	
//	//private WebDriver driver;
//
//@BeforeMethod
//  public void setUp() throws Exception {
//    driver.manage().window().maximize();
//  }  
//
//@Test 
//public void RegistrationTest () throws InterruptedException, BiffException, IOException {
//	
//	StringBuffer errorBuffer = new StringBuffer();
//	logger=report.startTest("Web Account Registration Test");
//    SiteAccess.ActionAccessSite(driver, "71.193.51.0");	
//    driver.findElement(PageGlobal.TopNavAccount).click();
//    Thread.sleep(2000);
//    
//    try {
//    	Assert.assertEquals(driver.findElements(By.linkText("Sign Into Your Account")).isEmpty(),false);
//	    Assert.assertEquals(driver.findElements(By.linkText("Account Profile")).isEmpty(),false);
//	    Assert.assertEquals(driver.findElements(By.linkText("Online Order History")).isEmpty(),false);
//	    Assert.assertEquals(driver.findElements(By.linkText("Order Status")).isEmpty(),false);
//	    Assert.assertEquals(driver.findElements(By.linkText("shopping list")).isEmpty(),false);
//	    Assert.assertEquals(driver.findElements(By.cssSelector("div.pgmtxt")).isEmpty(),false);
//	    Assert.assertEquals(driver.findElements(By.linkText("Learn more")).isEmpty(),false);
//	    Assert.assertEquals(driver.findElements(By.cssSelector("div.signinup-items")).isEmpty(),false);
//    } catch (AssertionError e) {
//    	errorBuffer.append(e.getMessage());
//    	System.out.println(e.toString());
//    }
//    
//    driver.findElement(PageGlobal.SignUp).click();
//    Thread.sleep(2000);
//    //driver.navigate().to("https://uat.totalwine.com/register");
//    Assert.assertEquals(driver.findElements(By.cssSelector("div.aml-heading")).isEmpty(),false);
//    driver.findElement(By.id("firstName")).clear();
//    driver.findElement(By.id("firstName")).sendKeys("Automated");
//    driver.findElement(By.id("lastName")).clear();
//    driver.findElement(By.id("lastName")).sendKeys("Tester");
//    driver.findElement(By.id("email")).clear();
//    driver.findElement(By.id("email")).sendKeys("automatedtester_"+ConfigurationFunctions.randInt()+"."+ConfigurationFunctions.randInt()+"@totalwine.com");
//    	String email = driver.findElement(By.id("email")).getAttribute("value");
//    	System.out.println("Registered Email Address: "+email);
//    driver.findElement(By.id("checkEmail")).clear();
//    driver.findElement(By.id("checkEmail")).sendKeys(email);
//    driver.findElement(By.id("pwd")).sendKeys("grapes");
//    driver.findElement(By.id("pwd")).sendKeys("grapes123");
//    Assert.assertEquals(driver.findElements(By.xpath("//form[@id='newuserregistration']/section/div[2]/div[5]/div[11]/div/ul/li[3]")).isEmpty(),false);
//    driver.findElement(By.id("pwd")).clear();
//    driver.findElement(By.id("pwd")).sendKeys("");
//    driver.findElement(By.id("pwd")).sendKeys("grapes123!");
//    Assert.assertEquals(driver.findElements(By.xpath("//form[@id='newuserregistration']/section/div[2]/div[5]/div[11]/div/ul/li[4]")).isEmpty(),false);
//    driver.findElement(By.id("checkPwd")).sendKeys("grapes123!");
//    driver.findElement(By.id("phone")).sendKeys("3015470004");
//    Assert.assertEquals("301-547-0004", driver.findElement(By.id("phone")).getAttribute("value"));
//    driver.findElement(By.id("compName")).clear();
//    driver.findElement(By.id("compName")).sendKeys("Total Wine & More");
//    driver.findElement(By.id("address1")).clear();
//    driver.findElement(By.id("address1")).sendKeys("6600 Rockledge Dr.");
//    driver.findElement(By.id("address2")).clear();
//    driver.findElement(By.id("address2")).sendKeys("Suite 210");
//    driver.findElement(By.id("city")).clear();
//    driver.findElement(By.id("city")).sendKeys("Bethesda");
//    WebElement element = driver.findElement(By.xpath("//div[10]/div/div/span/span"));  // Selection drop down menu
//    new Actions(driver).moveToElement(element).perform();  
//    element.click();
//    Thread.sleep(2000);
//    driver.findElement(By.xpath("//div[10]/div/div/div/div/div/ul/li[2]")).click();
//    driver.findElement(By.id("zipCode")).clear();
//    driver.findElement(By.id("zipCode")).sendKeys("20817");
//    Assert.assertEquals(driver.findElements(By.cssSelector("input[name=preferredStoreDefault]")).isEmpty(),false);
//    driver.findElement(By.cssSelector("input[name=ageCheck]")).sendKeys(Keys.ARROW_DOWN);;
//    driver.findElement(By.cssSelector("input[name=ageCheck]")).click();
//    driver.findElement(By.cssSelector("input[name=termsAndCondCheck]")).click();
//    driver.findElement(By.id("btnnuregisteration")).click();
//    Thread.sleep(6000);
//    Assert.assertEquals(driver.findElements(By.cssSelector("span.benefits-desc")).isEmpty(),false);
//    Assert.assertEquals(driver.findElements(By.linkText("Start shopping")).isEmpty(),false);
//    Assert.assertEquals(driver.findElements(By.id("c0010")).isEmpty(),false);
//    Assert.assertEquals(driver.findElements(By.id("c0020")).isEmpty(),false);
//    Assert.assertEquals(driver.findElements(By.id("c0030")).isEmpty(),false);
//    Assert.assertEquals(driver.findElements(By.id("c0050")).isEmpty(),false);
//    Assert.assertEquals(driver.findElements(By.cssSelector("em.icon.birthday")).isEmpty(),false);
//    Assert.assertEquals(driver.findElements(By.cssSelector("em.icon.mobilephone")).isEmpty(),false);
//    driver.findElement(By.id("btnSaveAccount")).sendKeys(Keys.ARROW_DOWN);
//    driver.findElement(By.id("btnSaveAccount")).click();
//    driver.findElement(By.cssSelector("div.ahp-heading")).click();
//    Assert.assertEquals(driver.findElements(By.cssSelector("div.ahp-heading")).isEmpty(),false);
//    Assert.assertEquals(driver.findElements(By.linkText("Your account")).isEmpty(),false);
//    Assert.assertEquals(driver.findElements(By.linkText("Orders")).isEmpty(),false);
//    Assert.assertEquals(driver.findElements(By.linkText("Your shopping lists")).isEmpty(),false);
//    Assert.assertEquals(driver.findElements(By.cssSelector("span.rewards-title")).isEmpty(),false);
//    Assert.assertEquals(driver.findElements(By.cssSelector("a[class=analyticsUpdateAcc]")).isEmpty(),false);
//    Assert.assertEquals(driver.findElements(By.linkText("Online order history")).isEmpty(),false);
//    Assert.assertEquals(driver.findElements(By.cssSelector("a[class=analyticsPrefStore]")).isEmpty(),false);
//    
//    //Logout and attempt to re-login using registration information
//    driver.findElement(By.linkText("Welcome, Automated")).click();
//    driver.findElement(By.linkText("Log out")).click();
//	}
//}