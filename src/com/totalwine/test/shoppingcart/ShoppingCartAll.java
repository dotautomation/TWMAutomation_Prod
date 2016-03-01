package com.totalwine.test.shoppingcart;

/*
 *** Shopping Cart (According to FSD/BRD Covering Flows 
 *		- Mini Cart Display, Shopping Cart Display, Add/Remove items in Cart, Edit item quantities, 
 *								Merge Cart, Re-order Items and Moving Items from Cart to List);
 *** Workflow:
 * Navigate to PDP
 * Add 3+ different items into Shopping Cart
 * Validate maximum 3 items are displaying in the mini cart
 * Modify mini cart (remove an item from the mini cart).
 * Access main shopping cart and validate all added items are displaying properly
 * Modify main shopping cart by updating product quantity
 * Validate confirmation message "The number of product has been updated"
 * Sign into user account using such an existing registered account which has online shopping order history and created shopping list.
 * Merge Cart modal with previous items will popup. Click on Proceed.
 * Click on online order history tab from the left navigation.
 * Sort order using "Order placed within" and "Sort by" drop down
 * Click on a previous existing order
 * Select any item and click on Re-order
 * ATC modal will display.
 * Select size from drop down and click on ATC button
 * Return in the main Shopping Cart
 * Select any item and click on the "Move to list" button. 
 * Move the item into a pre-existing shopping list.
 * Validate "Move to shopping list" confirmation appear on top.
 *   
 *** Technical Modules:
 * BeforeMethod (Test Pre-requisites):
 *  	Invoke webdriver
 * 		Maximize browser window
 * Test (Workflow)
 * AfterMethod
 * 		Take screenshot, in case of failure
 * 		Close webdriver
 * AfterClass
 * 		Quit webdriver
 */

import java.io.IOException;
import jxl.read.biff.BiffException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;
import com.totalwine.test.pages.*;
import com.totalwine.test.actions.*;

public class ShoppingCartAll extends Browser {
	
	public String IP = "71.193.51.0";

	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	  } 
	
	@Test
	public void ShoppingCartAllTest () throws InterruptedException, BiffException, IOException {
		logger=report.startTest("Shopping Cart All");
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(PageGlobal.AgeGateYes).click();
		Thread.sleep(5000);
	    driver.findElement(PageGlobal.NewSiteIntroClose).click();
	    Thread.sleep(5000);

	    ShoppingCart.MouseHoverWine(driver);

	    //** Adding 4 different items into the shopping cart
	    driver.findElement(By.cssSelector("li:nth-child(2) > div > div.plp-product-desc-wrap > div.plp-product-desc > h2 > a")).click();
	    ShoppingCart.ATC(driver);
	    ShoppingCart.MouseHoverWine(driver);

		driver.findElement(By.cssSelector("li:nth-child(3) > div > div.plp-product-desc-wrap > div.plp-product-desc > h2 > a")).click();
	    ShoppingCart.ATC(driver);
		ShoppingCart.MouseHoverWine(driver);	      
	    
	    driver.findElement(By.cssSelector("li:nth-child(4) > div > div.plp-product-desc-wrap > div.plp-product-desc > h2 > a")).click();
	    ShoppingCart.ATC(driver);
		ShoppingCart.MouseHoverWine(driver);

	    driver.findElement(By.cssSelector("li:nth-child(5) > div > div.plp-product-desc-wrap > div.plp-product-desc > h2 > a")).sendKeys(Keys.ARROW_DOWN); //Scrolling down
	    driver.findElement(By.cssSelector("li:nth-child(5) > div > div.plp-product-desc-wrap > div.plp-product-desc > h2 > a")).click();
	    ShoppingCart.ATC(driver);
		ShoppingCart.MouseHoverWine(driver);
		Thread.sleep (1000);
	    logger.log(LogStatus.PASS, "Added 4 different items into the cart");

	    //** Mouse Hover Actions on MiniCart for validating 3 display items
	    Actions action5 = new Actions(driver); 
	    WebElement MiniCart = driver.findElement(By.id("cartItemCount"));
	    action5.moveToElement(MiniCart).build().perform();
	    Thread.sleep (5000);
	    
	    //** Removing 1 item directly from MiniCart
	    driver.findElement(By.cssSelector(".remove.analyticsRemoveProduct")).click();
	    logger.log(LogStatus.PASS, "Deleted Item from Minicart");
	    Thread.sleep (6000);
	    
	    //** Main Cart display
	    driver.findElement(By.id("cartItemCount")).click();
	    logger.log(LogStatus.PASS, "Validating items in Minicart");
	    Thread.sleep (5000);

	    WebElement scroll = driver.findElement(By.cssSelector(".anVoucherForm"));  
	    scroll.sendKeys(Keys.PAGE_DOWN); //  ** Scrolling down page

	    //** Modify (remove) items in main cart
	    driver.findElement(By.cssSelector("#RemoveProduct_0 > em")).click();
	    Assert.assertEquals(driver.findElements(By.cssSelector(".text-arrow.analyticsChangeDM")).isEmpty(),false);
	    logger.log(LogStatus.PASS, "Validated Item removed from main cart");
	    Thread.sleep (3000);

	    //** Modify (Add) items in main cart		    
	    WebElement scroll1 = driver.findElement(By.cssSelector("input#qty.cart-qty.numonly"));
	    scroll1.sendKeys(Keys.PAGE_DOWN);   // ** Scrolling page down upto the element
	    driver.findElement(By.cssSelector("input#qty.cart-qty.numonly")).clear();
	    driver.findElement(By.cssSelector("input#qty.cart-qty.numonly")).sendKeys("2");

	    WebElement scroll2 = driver.findElement(By.id("checkout"));   // ** Scrolling page down upto the element
	    scroll2.sendKeys(Keys.PAGE_DOWN);

	    WebElement element = driver.findElement(By.cssSelector("em.update-icon-btn.icon"));  
	    new Actions(driver).moveToElement(element).perform();  // ** Move to the specific element. Need to use while element can't detect normal way
	    element.click();
	    Assert.assertEquals(driver.findElements(By.cssSelector(".text-arrow.analyticsChangeDM")).isEmpty(),false);
	    logger.log(LogStatus.PASS, "Validated Item modified (added) in main cart");
	    Thread.sleep(5000);
	    
	    //**Access the sign in modal
	    driver.findElement(PageGlobal.TopNavAccount).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("a.btn.btn-red.acc-link.analyticsSignIn")).click();
	    
	    //**Enter valid credentials for an account having an online and in-store order history
	    driver.switchTo().frame("iframe-signin-overlay");
	    driver.findElement(PageSignInModal.ModalUsername).clear();
	    driver.findElement(PageSignInModal.ModalUsername).sendKeys("mhossain@totalwine.com");
	    driver.findElement(PageSignInModal.ModalPassword).clear();
	    driver.findElement(PageSignInModal.ModalPassword).sendKeys("grapes123");
	    driver.findElement(PageSignInModal.ModalSigninButton).click();
	    Thread.sleep(6000);
	    
	    //**Check for presence of merge cart modal
	    if (driver.findElements(By.cssSelector("button.btn.btn-red.cartMergeBtn")).size()!=0) {
	    	driver.findElement(By.cssSelector("button.btn.btn-red.cartMergeBtn")).click();
	    	Thread.sleep(2000);
	    }
	    
	    //**Navigate to the Online order history
	    driver.findElement(PageOrderHistory.OnlineOrderHistory).click();
	    Thread.sleep(3000);
	    
	    //**Select option to view order history within Last 24 months
	    driver.findElement(PageOrderHistory.OrderPlacedWithinDropdown).click();
	    Thread.sleep(2000);
	    driver.findElement(PageOrderHistory.Last24Months).click();
	    logger.log(LogStatus.PASS, "Validated Order history");
	    Thread.sleep(3000);
	    
	    //**Re-ordering Items from order history
	    driver.findElement(By.cssSelector("div:nth-child(4) > div.oh-accordion-closed > div:nth-child(5)")).click();
	    Thread.sleep (3000);
	    	    
		String ProductIdReOrder = driver.findElement(By.cssSelector("span.color-dim")).getText();
		System.out.println(ProductIdReOrder);
		Thread.sleep(2000);   
	    driver.findElement(By.xpath(".//*[@id='"+ProductIdReOrder+"']")).click(); //Clicking the ATC button
		Thread.sleep (5000);
		
	    String ProductIdReOrder1 = driver.findElement(By.cssSelector("div.pdp-product-nos")).getText();
		System.out.println(ProductIdReOrder1);
		Thread.sleep(2000);   
	    driver.findElement(By.xpath(".//*[@id='"+ProductIdReOrder1+"']")).click(); //Clicking the ATC button
	    logger.log(LogStatus.PASS, "Validated re-order from order history");
	    Thread.sleep (5000);
		
	    //**Back to main Shopping Cart
	    driver.findElement(By.id("cartItemCount")).click();
	    Thread.sleep (7000);
	    
	    //**Move items from Cart to List
	    driver.findElement(By.cssSelector(".itemval>span")).click();
	    Thread.sleep (1000);
	    
	    driver.findElement(By.cssSelector(".undefined.anOption[data-val='Shopping List 02/26/2016']")).click();
	    Assert.assertEquals(driver.findElements(By.cssSelector(".alert.positive")).isEmpty(),false);
	    logger.log(LogStatus.PASS, "Validated item moved from cart to list");
	    Thread.sleep (5000);
	}
}