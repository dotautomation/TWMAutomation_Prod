package com.totalwine.test.review;

/*
 ****  Writing customer review
 ****  Work flow : 
 *  1. Access to PDP of any item
 *  2. Access to Customer Ratings & Reviews page for that product 
 *  3. Click on write a review
 *  4. Signup for a new random account as using the same email cann't re-write review for the same product. 
 *  4. After registering a new user, it will redirect to "Write your review" page
 *  5. Fillup all the required field and click on save button
 *  6. Validate confirmation message "Thanks For Your Review"
 *  
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
import java.util.Random;
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
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.trials.Browser;
import jxl.read.biff.BiffException;
import com.totalwine.test.actions.*;

public class CustomerReview extends Browser {

	@DataProvider(name="CheckoutParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"CustomerReview", "CustomerRatingReviewUAT");
        return(retObjArr);
    } 

	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();	
		 }  

	@Test (dataProvider = "CheckoutParameters")
	public void CustomerRatingsReviewsTest (String IP,String PDP,String Email,String Password,String ReviewTitle,String MyReview,String DescribeItem,
			String NickName,String FirstName,String LastName,String Phone,String Address1,String City,String Zip)
					
		throws InterruptedException, BiffException, IOException {
		logger=report.startTest("Writing Customer Ratings/Reviews");
		
		//** Random E-mail Creation
		Random rand = new Random();
	    int randomNum = rand.nextInt((1000 - 1) + 1) + 1;
	    int randomNum_2 = rand.nextInt((1000 - 1) + 1) + 1;
		
		driver.get(ConfigurationFunctions.locationSet+IP);
		PageLoad(driver); // Will not trigger the next control until loading the page
		
		//** By Passing Age Gate and Welcome Modal
		Checkout.AgeGateWelcome(driver);
		PageLoad(driver); // Will not trigger the next control until loading the page

	    // **  Selecting a product from PDP
		driver.get(ConfigurationFunctions.accessURL+PDP);
		Thread.sleep(7000);
		PageLoad(driver); // Will not trigger the next control until loading the page

	    //** Accessing Customer Ratings & Reviews Page
	    Assert.assertEquals(driver.findElements(By.cssSelector("section.css-details-pd")).isEmpty(),false);
	    
	    driver.findElement(By.cssSelector("span.tabs-right.anPDPTab")).click();
	    Thread.sleep(5000);
	    
	    driver.findElement(By.cssSelector("span.tabs-right.anPDPTab")).click();
	    Thread.sleep(5000);

	    driver.findElement(By.cssSelector("#BVRRRatingSummaryLinkWriteID>a")).click();
	    Thread.sleep(6000);

	    //** Registering new email for writing review as for a specific product one review can write from one email
	    driver.switchTo().frame(driver.findElement(By.id("iframe-signin-overlay")));
	    driver.findElement(PageGlobal.SignUp).click();
	    Thread.sleep(2000);
	
	    // ** Filling up "Create your account" page
	    driver.findElement(By.cssSelector("#firstName")).sendKeys(FirstName);
	    driver.findElement(By.cssSelector("#lastName")).sendKeys(LastName);
	    Thread.sleep(2000);

	    // *** Register Email from DB
//	    driver.findElement(By.cssSelector("#email")).sendKeys(Email);
//	    driver.findElement(By.cssSelector("#checkEmail")).sendKeys(Email);
	    
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
	    Thread.sleep(3000);
	    
	    //** Filling Customer Ratings & Reviews form
	    driver.findElement(By.cssSelector("#star_link_rating_5")).click();
	    Thread.sleep(1000);

	    driver.findElement(By.cssSelector("#BVFieldRecommendYesLabelID")).click();
	    Thread.sleep(1000);

	    WebElement scroll4 = driver.findElement(By.cssSelector("#BVFieldTitleID"));  
	    scroll4.sendKeys(Keys.PAGE_DOWN); //  ** Scrolling down page
	    driver.findElement(By.cssSelector("#BVFieldTitleID")).sendKeys(ReviewTitle);
	    Thread.sleep(1000);
	    
	    driver.findElement(By.cssSelector("#BVFieldReviewtextID")).sendKeys(MyReview);
	    Thread.sleep(1000);

	    driver.findElement(By.cssSelector("#BVProTextBox1ID")).sendKeys(DescribeItem);
	    Thread.sleep(1000);
	    
	    driver.findElement(By.cssSelector("#BVProTextBox1ID")).sendKeys(DescribeItem);
	    Thread.sleep(1000);

	    driver.findElement(By.cssSelector("#BVFieldUsernicknameID")).sendKeys("nick"+randomNum+"name"+randomNum_2+"test");
	    Thread.sleep(1000);

	    JavascriptExecutor js = (JavascriptExecutor)driver;  // Finding out elements that are out of site
	    js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#BVFieldAgreedtotermsandconditionsLabelID")));
	    Thread.sleep(1000);
	    
	    JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of site
	    js1.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#BVButtonSubmitID > button")));
	    Thread.sleep(3000);
	    
	    // ** Validate Review and Ratings msg 
	    sAssert.assertEquals(driver.findElements(By.cssSelector("#BVMessagePageHeaderID")).isEmpty(),false, "Review and Ratings validated"); 
	    Thread.sleep(3000);
	 }
}