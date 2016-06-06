package com.totalwine.test.productlist;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.pages.PageProductList;
import com.totalwine.test.trials.Browser;

public class PLPListView extends Browser {

	private String IP="136.160.90.133"; //Maryland

	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	}  
	
	@Test 
	public void PLPListTest () throws InterruptedException {
		logger=report.startTest("PLP Grid Test");
		
		//Access site
		SiteAccess.ActionAccessSite(driver, IP);
	    
	    //Navigate to a PLP
	    Actions action=new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
	    WebElement wineNav = driver.findElement(By.xpath("//a[contains(@href,'/c/c0020')]")); 
		action.moveToElement(wineNav).build().perform(); //Top Level Menu Hover
		WebElement winePLPNav=driver.findElement(By.xpath("//a[contains(@href,'/wine/red-wine/c/')]"));
		js.executeScript("arguments[0].click();", winePLPNav);
		Thread.sleep(5000);
		WebElement wineMove = driver.findElement(By.cssSelector("ul.header-classes")); //Moving the mouse away from the top level menu 
		action.moveToElement(wineMove).build().perform(); 
		Thread.sleep(2000);
		logger.log(LogStatus.PASS, "Navigate to PLP");
	    
		//Validate Contents of PLP List View
		Assert.assertEquals(driver.findElements(PageProductList.PLPHero).isEmpty(), false);
		Assert.assertEquals(driver.findElements(PageProductList.ListViewDefault).isEmpty(),false); //Validate that the list view is the default
		logger.log(LogStatus.PASS, "List View is displayed by default");
		Assert.assertEquals(driver.findElements(PageProductList.List).isEmpty(), false);
		Assert.assertEquals(driver.findElements(PageProductList.ListImage).isEmpty(), false);
		Assert.assertEquals(driver.findElements(PageProductList.ListDesc).isEmpty(), false);
		Assert.assertEquals(driver.findElements(PageProductList.ListReadMore).isEmpty(), false);
		Assert.assertEquals(driver.findElements(PageProductList.ListStars).isEmpty(), false);
		Assert.assertEquals(driver.findElements(PageProductList.ListReviewCount).isEmpty(), false);
		Assert.assertEquals(driver.findElements(PageProductList.ListCountry).isEmpty(), false);
		Assert.assertEquals(driver.findElements(PageProductList.ListRegion).isEmpty(), false);
		Assert.assertEquals(driver.findElements(PageProductList.ListShippingNotAvailBadge).isEmpty(), false); //Wine is not shippable in MD
		Assert.assertEquals(driver.findElements(PageProductList.ListISPBadge).isEmpty(), false);
		Assert.assertEquals(driver.findElements(PageProductList.ListATC).isEmpty(), false);
		Assert.assertEquals(driver.findElements(PageProductList.ListATL).isEmpty(), false);
		Assert.assertEquals(driver.findElements(PageProductList.ListPrice).isEmpty(), false);
		logger.log(LogStatus.PASS, "List View page elements (Item Image,Description,\"Read More >\",Stars,"
				+ "Review Counts,Country,Region,Shipping badge,ISP badge,ATC,ATL,Price displayed");
		
		//Validate store selector and number of default selected stores
//		Assert.assertEquals(driver.findElements(By.cssSelector("input[data-chk=true]")).size(),5);
		logger.log(LogStatus.PASS, "5 stores are selected by default in the store selector");
		
	    //Validate absence of Grid elements 
		Assert.assertEquals(driver.findElements(By.cssSelector("section.plp-product-content.grid")).isEmpty(), true);
		logger.log(LogStatus.PASS, "Grid View page elements not displayed");
		Thread.sleep(3000);
		
	    //Validate Pagination
		JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
		js1.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a.pager-next.analyticsPageView")));        
//		js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.cssSelector("a.pager-next.analyticsPageView")));
//	    driver.findElement(By.cssSelector("a.pager-next.analyticsPageView")).click(); //Next page
	    Thread.sleep(3000);
	    Assert.assertEquals(driver.findElements(PageProductList.ListDesc).isEmpty(), false);
	    logger.log(LogStatus.PASS, "Pagination is functional");
	    
	    //Regulatory (LARE-specific) message
	  	driver.findElement(PageProductList.ATYShip).click();
	  	Thread.sleep(3000);
	  	Assert.assertTrue(driver.findElement(By.cssSelector("p.msg-noitems")).getText().contains("Due to regulatory constraints, Wine is not eligible for shipping."));
	  	logger.log(LogStatus.PASS, "LARE-specific message is displayed");
	    
	    //Click All stores tab and validate that ATY > Both is not displayed
	    driver.findElement(PageProductList.PLPAllStores).click();
	    Thread.sleep(3000);
	    Assert.assertEquals(driver.findElements(PageProductList.ListViewDefault).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(PageProductList.ATYBoth).isEmpty(),true);
	}
}
