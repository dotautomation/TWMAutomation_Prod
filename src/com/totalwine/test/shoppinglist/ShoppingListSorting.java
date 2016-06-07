package com.totalwine.test.shoppinglist;

/*
 * Shopping List (Sorting Shopping List)
 * Workflow:
 * 1. Click on "Shopping List" from the header menu 
 * 2. Login using such an account which has existed shopping list
 * 3. Select different sorting option from Sort by drop down
 * 4. Validate the sorting options are working properly
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageSignInModal;
import com.totalwine.test.trials.Browser;
import com.totalwine.test.actions.*;

public class ShoppingListSorting extends Browser {

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
	public void ShoppingListSortingTest (String Location,String Email, String Password)		
					throws InterruptedException, BiffException, IOException {		    
		driver.get(ConfigurationFunctions.locationSet+Location);
		logger=report.startTest("Shopping List Sorting Test");
		Browser.PageLoad(driver); // Will not trigger the next control until loading the page

		//**By Passing Age Gate and Welcome Modal
		Checkout.AgeGateWelcome(driver);  
		
	    //**Accessing Shopping List
	    driver.findElement(By.cssSelector("li.shipping-cont.loggedin-not-list > a > span.list-text")).click();
		Browser.PageLoad(driver); // Will not trigger the next control until loading the page

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

	    //  ** Checking for survey pop-up
	    Checkout.SurverPopup(driver);

	    //**Clicking on different sorting buttons
	    ShoppingList.SortingDropDown(driver);
	    driver.findElement(By.cssSelector(".undefined.anOption[data-val='department']")).click();
	    Thread.sleep(3000);
	    	    
	    ShoppingList.SortingDropDown(driver);
	    driver.findElement(By.cssSelector(".undefined.anOption[data-val='dateadded']")).click();
	    Thread.sleep(3000);
	    	    
	    ShoppingList.SortingDropDown(driver);
	    driver.findElement(By.cssSelector(".undefined.anOption[data-val='pricehightolow']")).click();
	    Thread.sleep(3000);

	    ShoppingList.SortingDropDown(driver);
	    driver.findElement(By.cssSelector(".undefined.anOption[data-val='pricelowtohigh']")).click();
	    Thread.sleep(3000);

	    ShoppingList.SortingDropDown(driver);
	    driver.findElement(By.cssSelector(".undefined.anOption[data-val='expertrating']")).click();
	    Thread.sleep(3000);
	    
	    ShoppingList.SortingDropDown(driver);
	    driver.findElement(By.cssSelector(".undefined.anOption[data-val='aisle']")).click();
	    Thread.sleep(3000);
	    
	    ShoppingList.SortingDropDown(driver);
	    driver.findElement(By.cssSelector(".undefined.anOption[data-val='atoz']")).click();
	    Thread.sleep(3000);

	    Assert.assertEquals(driver.findElements(By.cssSelector("div.page-header")).isEmpty(),false, "Shopping List Sorting Test");
	}
}