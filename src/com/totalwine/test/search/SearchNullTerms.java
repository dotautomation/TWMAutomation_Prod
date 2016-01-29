package com.totalwine.test.search;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.LogStatus;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;


public class SearchNullTerms extends Browser {
	
	public String IP = "71.193.51.0";
	
	@BeforeMethod
	  public void setUp() throws Exception {
		driver.manage().window().maximize();
		logger=report.startTest("Search Null Terms Test");
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	    driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
	    Thread.sleep(5000);
	  } 
	
	@DataProvider(name="SearchParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"Search", "nullsearch");
        return(retObjArr);
    }
	
	@Test (dataProvider = "SearchParameters")
	public void SearchTest (String searchTerm) throws InterruptedException {
		driver.findElement(By.id("header-search-text")).clear();
	    driver.findElement(By.id("header-search-text")).sendKeys(searchTerm);
	    driver.findElement(By.cssSelector("a[class=\"search-right-cont-mini-search-logo analyticsSearch\"]")).click();
	    Thread.sleep(3000);
	    String allStoreCount = driver.findElement(By.cssSelector("ul.plp-product-tabs-wrapper > li:nth-child(2) > h2 > a.analyticsPLPDisp")).getText();
	    System.out.println(searchTerm+":"+allStoreCount.substring(allStoreCount.indexOf("(") + 1, allStoreCount.indexOf(")")));
    	logger.log(LogStatus.INFO, searchTerm+":"+allStoreCount.substring(allStoreCount.indexOf("(") + 1, allStoreCount.indexOf(")")));
	}
}
