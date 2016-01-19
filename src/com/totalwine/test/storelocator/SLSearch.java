package com.totalwine.test.storelocator;

/*
 * Store Locator (Search)
 * Workflow:
 * 1. Access the site and navigate to the Store Locator page
 * 2. Search using most-frequently used search terms
 * 3. Validate result
 *  
 * Technical Modules:
 * 	1. BeforeMethod (Test Pre-requisites):
 * 			Maximize browser window
 * 	2. Test (Workflow)
 * 	3. AfterMethod
 * 			Take screenshot, in case of failure
 * 			Close webdriver
 * 	4. AfterClass
 * 			Quit webdriver
 */

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

public class SLSearch extends Browser {
	
	String IP = "71.193.51.0";
	String StoreLink = "ul.header-classes > li > a[href*=\\/store-finder]";
	
	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	 } 
	
	@Test //Search by City
	public void SLCitySearchTest () throws InterruptedException {
		//Access the site using the remoteTestIPAddress URL parameter for all test IPs
		String [] CitySearch = {"las vegas","orlando","phoenix","laurel md","fort myers"};
		String [] ClosestStores = {"Las Vegas (Summerlin)","Orlando (Colonial Plaza)","Phoenix (Camelback)","Laurel (Corridor)","Fort Myers"};
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
		Thread.sleep(5000);
		
		 //Navigate to the Store Locator page
	    driver.findElement(By.cssSelector(StoreLink)).click();
	    Thread.sleep(3000);
	    
	    //for (String SearchTerm : CitySearchTerms) {
	    for (int i=0;i<CitySearch.length;i++) {	
	    	//Enter search term and search
	    	driver.findElement(By.id("storelocator-query")).click();
	        driver.findElement(By.id("storelocator-query")).clear();
	        driver.findElement(By.id("storelocator-query")).sendKeys(CitySearch[i]);
	        driver.findElement(By.id("storeFinderBtn")).click();
	        Thread.sleep(3000);
	        
		    //Click the first "Did you mean?" link, if it appears
		    if (driver.findElements(By.cssSelector("button.btn > em.icon-rightarrow")).size()!=0) {
		    	driver.findElement(By.cssSelector("button.btn > em.icon-rightarrow")).click();
		    }
		    //Validate search results
		    Assert.assertEquals(driver.findElement(By.xpath("//li[1]/div/address/a/p")).getText(),ClosestStores[i]);
	    }
	}
		
	@Test //Search by State
	public void SLStateSearchTest () throws InterruptedException {
		String [] ZipSearch = {"95630","33186"};
		String [] ClosestStores = {"Folsom","Kendall"};
		//Access the site using the remoteTestIPAddress URL parameter for all test IPs
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
		Thread.sleep(5000);
		
		 //Navigate to the Store Locator page
	    driver.findElement(By.cssSelector(StoreLink)).click();
	    Thread.sleep(3000);
	    
	    //for (String SearchTerm : CitySearchTerms) {
	    for (int i=0;i<ZipSearch.length;i++) {	
	    	//Enter search term and search
	    	driver.findElement(By.id("storelocator-query")).click();
	        driver.findElement(By.id("storelocator-query")).clear();
	        driver.findElement(By.id("storelocator-query")).sendKeys(ZipSearch[i]);
	        driver.findElement(By.id("storeFinderBtn")).click();
	        Thread.sleep(3000);
		   
		    //Validate search results
		    Assert.assertEquals(driver.findElement(By.xpath("//li[1]/div/address/a/p")).getText(),ClosestStores[i]);
	    }
	}
	
	@Test //Search by Zip
	public void SLZipSearchTest () throws InterruptedException {
		String [] StateSearch = {"delaware"};
		String [] ClosestStores = {"Wilmington"};
		//Access the site using the remoteTestIPAddress URL parameter for all test IPs
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
		Thread.sleep(5000);
		
		 //Navigate to the Store Locator page
	    driver.findElement(By.cssSelector(StoreLink)).click();
	    Thread.sleep(3000);
	    //for (String SearchTerm : CitySearchTerms) {
	    for (int i=0;i<StateSearch.length;i++) {	
	    	//Enter search term and search
	    	driver.findElement(By.id("storelocator-query")).click();
	        driver.findElement(By.id("storelocator-query")).clear();
	        driver.findElement(By.id("storelocator-query")).sendKeys(StateSearch[i]);
	        driver.findElement(By.id("storeFinderBtn")).click();
	        Thread.sleep(3000);
	        
		    //Click the first "Did you mean?" link, if it appears
		    if (driver.findElements(By.cssSelector("button.btn > em.icon-rightarrow")).size()!=0) {
		    	driver.findElement(By.cssSelector("button.btn > em.icon-rightarrow")).click();
		    }
		    //Validate search results
		    Assert.assertEquals(driver.findElement(By.xpath("//li[1]/div/address/a/p")).getText(),ClosestStores[i]);
	    }
	}
}
