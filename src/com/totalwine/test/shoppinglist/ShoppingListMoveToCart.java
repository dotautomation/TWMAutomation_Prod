package com.totalwine.test.shoppinglist;

/*
 * Shopping List (Item move to cart)
 * Workflow:
 * 1. Click on "Shopping List" from the header menu 
 * 2. Login using such an account which has existed shopping list
 * 3. Add an Item from the Shopping list into Shopping Cart
 * 4. Validate the product added into the shopping cart properly
 * 
 * Technical Modules:
 * 	1. BeforeMethod (Test Pre-requisites):
 * 			Invoke Webdriver
 * 			Maximize browser window
 * 	2. Test (Workflow)
 * 	3. AfterMethod
 * 			Take screenshot, in case of failure
 * 			Close Webdriver
 * 	4. AfterClass
 * 			Quit Webdriver
 */

import java.io.IOException;
import org.testng.Assert;
import jxl.read.biff.BiffException;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageSignInModal;
import com.totalwine.test.trials.Browser;
import com.relevantcodes.extentreports.LogStatus;
import com.totalwine.test.actions.*;

public class ShoppingListMoveToCart extends Browser {

	@DataProvider(name="CheckoutParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"ShoppingList", "ShoppingListSortingUAT");
        return(retObjArr);
    } 
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();	
		 }  
	
	@Test (dataProvider = "CheckoutParameters")
	public void ShoppingListMoveToCartTest (String Location,String Email, String Password)		
					throws InterruptedException, BiffException, IOException {		    
		driver.get(ConfigurationFunctions.locationSet+Location);
		logger=report.startTest("Shopping List - Item move to Cart Test");
		PageLoad(driver); // Will not trigger the next control until loading the page

		//**By Passing Age Gate and Welcome Modal
		Checkout.AgeGateWelcome(driver);  
		
	    //**Accessing Shopping List
	    driver.findElement(By.cssSelector("li.shipping-cont.loggedin-not-list > a > span.list-text")).click();
				
	    //**Enter valid credentials for an account having Pre-created shopping list
	    driver.switchTo().frame("iframe-signin-overlay");
	    driver.findElement(PageSignInModal.ModalUsername).clear();
	    driver.findElement(PageSignInModal.ModalUsername).sendKeys(Email);
	    driver.findElement(PageSignInModal.ModalPassword).clear();
	    driver.findElement(PageSignInModal.ModalPassword).sendKeys(Password);
	    driver.findElement(PageSignInModal.ModalSigninButton).click();
	    Thread.sleep(2000);

	    //**Checking for presence of merge cart modal
	    ShoppingList.MergeCartModal(driver);
	    Thread.sleep (5000);
	    
	    //** Checking for survey pop-up
	    Checkout.SurverPopup(driver);
	    
	    //**Inserting item from the Shopping-list into the Shopping Cart
			    
	    String ATC1 = driver.findElement(By.cssSelector("li:nth-child(2) > div > div.plp-product-desc-wrap > div.plp-product-row1.plp-product-row1-products > div.plp-product-desc > div > span.plp-product-qty.color-5b5b5b")).getText();
		System.out.println(ATC1);
		Thread.sleep(2000);
	    JavascriptExecutor js = (JavascriptExecutor)driver;  // Finding out elements that are out of site
	    js.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='"+ATC1+"']"))); //Clicking the ATC button   
		Thread.sleep (5000);
		
	    String ATC2 = driver.findElement(By.cssSelector("div.pdp-product-nos")).getText();
		System.out.println(ATC2);
		Thread.sleep(2000);   
	    driver.findElement(By.xpath(".//*[@id='"+ATC2+"']")).click(); //Clicking the ATC button
	    logger.log(LogStatus.PASS, "Validated adding into Cart from Shopping list");
	    Thread.sleep (5000);
	    Assert.assertEquals(driver.findElements(By.cssSelector("div.page-header")).isEmpty(),false, "ATC from Shopping list");
	}
}