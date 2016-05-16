package com.totalwine.test.aml;

/*
 * Order History Workflow
 * Workflow:
 * 1. Login to an account that has an online and in store order history
 * 2. Navigate to Online order history
 * 3. Validate the elements contained in a historical online order
 * 4. Navigate to In store purchase history
 * 5. Validate the elements contained in a historical in store order.
 *		
 * Technical Modules:
 * 	1. BeforeMethod (Test Pre-requisites):
 * 			Invoke webdriver
 * 			Maximize browser window
 * 	2. Test (Workflow)
 * 	3. AfterMethod
 * 			Take screenshot
 * 			Close webdriver
 * 	4. AfterClass
 * 			Quit webdriver
 */

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;

import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;
import com.totalwine.test.pages.*;

public class OrderHistory extends Browser {
	
	private String IP="71.193.51.0";
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	  }  
	
	@Test
	public void OrderHistoryTest () throws InterruptedException {
		logger=report.startTest("AML Order History Test (In-store and Shipping Orders)");
		SiteAccess.ActionAccessSite(driver, IP);
	    
	    //Access the sign in modal
	    driver.findElement(PageGlobal.TopNavAccount).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("a.btn.btn-red.acc-link.analyticsSignIn")).click();
	    
	    //Enter valid credentials for an account having an online and in-store order history
	    driver.switchTo().frame("iframe-signin-overlay");
	    driver.findElement(PageSignInModal.ModalUsername).clear();
	    driver.findElement(PageSignInModal.ModalUsername).sendKeys("mhossain@totalwine.com");
	    driver.findElement(PageSignInModal.ModalPassword).clear();
	    driver.findElement(PageSignInModal.ModalPassword).sendKeys("grapes123");
	    driver.findElement(PageSignInModal.ModalSigninButton).click();
	    Thread.sleep(6000);
	    
	    //Check for presence of merge cart modal
	    if (driver.findElements(By.cssSelector("button.btn.btn-red.cartMergeBtn")).size()!=0) {
	    	driver.findElement(By.cssSelector("button.btn.btn-red.cartMergeBtn")).click();
	    	Thread.sleep(2000);
	    }
	    
	    //Navigate to the Online order history
	    driver.findElement(PageOrderHistory.OnlineOrderHistory).click();
	    Thread.sleep(3000);
	    
	    //Select option to view order history within Last 24 months
	    driver.findElement(PageOrderHistory.OrderPlacedWithinDropdown).click();
	    Thread.sleep(2000);
	    driver.findElement(PageOrderHistory.Last24Months).click();
	    Thread.sleep(3000);
	    
	    //Validate online order history
	    Assert.assertEquals(driver.findElements(PageOrderHistory.OrderHistoryAccordian).isEmpty(),false);
	    driver.findElement(PageOrderHistory.OrderDetailAccess).click();
	    Thread.sleep(2000);
	    Assert.assertEquals(driver.findElements(PageOrderHistory.OrderHistoryDetailAddress).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(PageOrderHistory.OrderHistoryDetailTotal).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(PageOrderHistory.OrderHistoryDetailProduct).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(PageOrderHistory.OrderHistoryDetailUnitPrice).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(PageOrderHistory.OrderHistoryDetailItemTotal).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(PageOrderHistory.OrderHistoryDetailReorder).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(PageOrderHistory.OrderHistoryPagination).isEmpty(),false);
	    
	    //Navigate to the In-store purchase history
	    driver.findElement(PageOrderHistory.InStorePurchaseHistory).click();
	    Thread.sleep(3000);
	    
	    //Select option to view order history within Last 24 months
	    driver.findElement(PageOrderHistory.OrderPlacedWithinDropdown).click();
	    Thread.sleep(2000);
	    driver.findElement(PageOrderHistory.Last24Months).click();
	    Thread.sleep(3000);
	}

}
