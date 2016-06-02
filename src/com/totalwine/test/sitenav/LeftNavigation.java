package com.totalwine.test.sitenav;

/*
 * Shopping List (Browse)
 * Workflow:
 * 	1. Access CLP/SCLP/SRP page 
 * 	2. Validate the presence of facets in the left navigation 
 * 	3. Click through facets and validate results
 */

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class LeftNavigation extends Browser {
	
	public String IP = "71.193.51.0";

	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	  } 
	
	
	//CLP Left Navigation
	@Test
	public void CLPLeftNavTest () throws InterruptedException {
		logger=report.startTest("Site Left Navigation Test");
		SiteAccess.ActionAccessSite(driver, IP);//Access site
	    
	    //Navigate to Wine CLP and validate left nav
	    //String[] wineclplinks = {"/c/000009","/c/000002","/c/000005","/c/000024","/c/000063","/c/000262","/c/000270","/c/000278","/c/000263","/c/000279"};
	    String[] wineclplinks = {"/c/000009","/c/000002","/c/000005","/c/000024","/c/000063","/c/000262"};
	    Actions action=new Actions(driver);
	    WebElement toplevelnav = driver.findElement(By.xpath("//a[contains(@href,'/c/c0020')]")); //Wine
		action.moveToElement(toplevelnav).build().perform(); //Hover over Wine
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", toplevelnav); //Click on Wine to display CLP
		Thread.sleep(5000);
		
//		Assert.assertEquals(driver.findElements(By.cssSelector("a.analyticsHeroLink")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div.clpviewall-view")).isEmpty(),false);
		
		String viewMoreLink = "li:nth-child(2) > div > ul > li.show-more.nav-links > a > span";
		
		for (String wineclplink : wineclplinks) {
			System.out.println("Wine: "+wineclplink);
			Assert.assertEquals(driver.findElements(By.cssSelector("div.inner-items-wrapper > ul > li > a[href*=\""+wineclplink+"\"]")).isEmpty(),false);
			driver.findElement(By.cssSelector("div.inner-items-wrapper > ul > li > a[href*=\""+wineclplink+"\"]")).click();
			Thread.sleep(3000);
			Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains(wineclplink.toLowerCase()));//Validate URL contains link category
			toplevelnav = driver.findElement(By.xpath("//a[contains(@href,'/c/c0020')]")); //Wine
			action.moveToElement(toplevelnav).build().perform();
			js.executeScript("arguments[0].click();", toplevelnav);
			Thread.sleep(3000);
		}
		
	    //Navigate to Beer CLP and validate left nav
		//String[] beerclplinks = {"/c/001172","/c/001169","/c/001165","/c/001283","/c/001161","/c/001304","/c/001358","/c/001353","/c/001366","/c/001351"};
		String[] beerclplinks = {"/c/001172","/c/001169","/c/001165","/c/001283","/c/001161","/c/001304"};
		action=new Actions(driver);
		toplevelnav = driver.findElement(By.xpath("//a[contains(@href,'/c/c0010')]")); //Beer
		action.moveToElement(toplevelnav).build().perform();
		js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", toplevelnav);
		Thread.sleep(5000);
		
//		Assert.assertEquals(driver.findElements(By.cssSelector("a.analyticsHeroLink")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div.clpviewall-view")).isEmpty(),false);
		
		for (String beerclplink : beerclplinks) {
			System.out.println("Beer: "+beerclplink);
			Assert.assertEquals(driver.findElements(By.cssSelector("div.inner-items-wrapper > ul > li > a[href*=\""+beerclplink+"\"]")).isEmpty(),false);
			driver.findElement(By.cssSelector("div.inner-items-wrapper > ul > li > a[href*=\""+beerclplink+"\"]")).click();
			Thread.sleep(3000);
			Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains(beerclplink.toLowerCase()));//Validate URL contains link category
			toplevelnav = driver.findElement(By.xpath("//a[contains(@href,'/c/c0010')]")); //Beer
			action.moveToElement(toplevelnav).build().perform();
			js.executeScript("arguments[0].click();", toplevelnav);
			Thread.sleep(3000);
		}
	    
	    //Navigate to Spirits CLP and validate left nav
		//String[] spiritsclplinks = {"/c/000897","/c/000885","/c/000871","/c/000778","/c/000773","/c/001016","/c/001011","/c/001068","/c/001024","/c/001050"};
		String[] spiritsclplinks = {"/c/000897","/c/000885","/c/000871","/c/000778","/c/000773","/c/001016"};
		action=new Actions(driver);
		toplevelnav = driver.findElement(By.xpath("//a[contains(@href,'/c/c0030')]")); //Spirits
		action.moveToElement(toplevelnav).build().perform();
		js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", toplevelnav);
		Thread.sleep(5000);
		
//		Assert.assertEquals(driver.findElements(By.cssSelector("a.analyticsHeroLink")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div.clpviewall-view")).isEmpty(),false);
		
		for (String spiritsclplink : spiritsclplinks) {
			System.out.println("Spirits: "+spiritsclplink);
			Assert.assertEquals(driver.findElements(By.cssSelector("div.inner-items-wrapper > ul > li > a[href*=\""+spiritsclplink+"\"]")).isEmpty(),false);
			driver.findElement(By.cssSelector("div.inner-items-wrapper > ul > li > a[href*=\""+spiritsclplink+"\"]")).click();
			Thread.sleep(3000);
			Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains(spiritsclplink.toLowerCase()));//Validate URL contains link category
			toplevelnav = driver.findElement(By.xpath("//a[contains(@href,'/c/c0030')]")); //Spirits
			action.moveToElement(toplevelnav).build().perform();
			js.executeScript("arguments[0].click();", toplevelnav);
			Thread.sleep(3000);
		}
		
		
	    //Navigate to Accessories CLP and validate left nav
		String[] accclplinks = {"/c/001481","/c/001432","/c/001586","/c/001442","/c/001493"};
	    action=new Actions(driver);
		toplevelnav = driver.findElement(By.xpath("//a[contains(@href,'/c/c0050')]")); //Accessories
		action.moveToElement(toplevelnav).build().perform();
		js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", toplevelnav);
		Thread.sleep(5000);
		
		Assert.assertEquals(driver.findElements(By.cssSelector("a.analyticsHeroLink")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div.clpviewall-view")).isEmpty(),false);
		
		for (String accclplink : accclplinks) {
			System.out.println("Accessory: "+accclplink);
			Assert.assertEquals(driver.findElements(By.cssSelector("div.inner-items-wrapper > ul > li > a[href*=\""+accclplink+"\"]")).isEmpty(),false);
			driver.findElement(By.cssSelector("div.inner-items-wrapper > ul > li > a[href*=\""+accclplink+"\"]")).click();
			Thread.sleep(3000);
			Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains(accclplink.toLowerCase()));//Validate URL contains link category
			toplevelnav = driver.findElement(By.xpath("//a[contains(@href,'/c/c0050')]")); //Accessories
			action.moveToElement(toplevelnav).build().perform();
			js.executeScript("arguments[0].click();", toplevelnav);
			Thread.sleep(3000);
		}
	    
	}
	/*
	//SCLP Left Navigation - Deferred in Production (No SCLP implementations)
	@Test
	public void SCLPLeftNavTest () throws InterruptedException {
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	    driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
	    Thread.sleep(5000);
	}
	
	//PLP Left Navigation (Filters)
	//Covered in com.totalwine.test.productlist.Filter
	*/
	//SRP Left Navigation (Filters)
	@Test
	public void SRPLeftNavTest () throws InterruptedException {
		
		String[] searchTerms = {"red wine","walker"};
		String[] srpfacets = {"/search/product?","/search/event?","/search/content?"};
		SiteAccess.ActionAccessSite(driver, IP);
	    
	    for (String searchTerm : searchTerms) {
		    //Search for wine
	    	driver.findElement(By.id("header-search-text")).clear();
		    driver.findElement(By.id("header-search-text")).sendKeys(searchTerm);
		    driver.findElement(By.cssSelector("a[class=\"search-right-cont-mini-search-logo analyticsSearch\"]")).click();
		    Thread.sleep(3000);
		    Assert.assertEquals(driver.findElements(By.linkText("Search categories")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.linkText("Stores")).isEmpty(),false);
		    Assert.assertEquals(driver.findElements(By.linkText("Department")).isEmpty(),false);

		    //Validate the left nav on the SRP
		    for (String srpfacet : srpfacets) {
		    	Assert.assertEquals(driver.findElements(By.cssSelector("div.inner-items-wrapper > ul > li > a[href*=\""+srpfacet+"\"]")).isEmpty(),false);//Check for presence of filter
		    	driver.findElement(By.cssSelector("div.inner-items-wrapper > ul > li > a[href*=\""+srpfacet+"\"]")).click(); //Click on facet
		    	Thread.sleep(3000);
		    	Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains(srpfacet.toLowerCase())); //Validate the URL
		    	driver.navigate().back();//Go back to SRP
		    	Thread.sleep(3000);
		    }
	    }
	}
}