package com.totalwine.test.performance;

/* Workflow:
 * 1. Navigate to each page that is expected to be served by the CDN (cached)
 * 2. Refresh to ensure a cache hit
 * 3. Validate, from the header, that a cache hit is registered.
 * 4. Example Header Contents for a cache hit:
 * 		X-Cache:HIT
 *		X-Cache-Hits:2
 *		X-POOL:USER
 *		X-Served-By:cache-jfk1026-JFK
 *		X-Timer:S1455304184.916392,VS0,VE0
 *  
 * Technical Modules:
 * 	1. BeforeMethod (Test Pre-requisites):
 * 			Invoke webdriver
 * 			Maximize browser window
 * 	2. Test (Workflow)
 * 	3. AfterMethod
 * 			Take screenshot, in case of failure
 * 			Close webdriver
 * 	4. AfterClass
 * 			Quit webdriver
 */

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.pages.PageSignInModal;
import com.totalwine.test.trials.Browser;

public class CachedPages extends Browser {
	
	//private String IP="71.193.51.0";
	
	@DataProvider(name="CachedPages")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"CachedPages", "url");
        return(retObjArr);
    } 
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	  }  
	
	@Test (dataProvider = "CachedPages")
	public void CachePagesTest (String pageURL,String loginFlag,String IP,String x_cache) throws InterruptedException, IOException {
		logger=report.startTest("Cached Pages Test");
		
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(PageGlobal.AgeGateYes).click();
		Thread.sleep(5000);
	    if (driver.findElement(PageGlobal.NewSiteIntroClose).isDisplayed())
	    		driver.findElement(PageGlobal.NewSiteIntroClose).click();
	    Thread.sleep(5000);
	    
	    if(loginFlag.equals("Y")) {
	    	//Access the sign in modal
		    driver.findElement(PageGlobal.TopNavAccount).click();
		    Thread.sleep(2000);
		    driver.findElement(By.cssSelector("a.btn.btn-red.acc-link.analyticsSignIn")).click();
		    
		    //Enter valid credentials for an account having an online and in-store order history
		    driver.switchTo().frame("iframe-signin-overlay");
		    driver.findElement(PageSignInModal.ModalUsername).clear();
		    driver.findElement(PageSignInModal.ModalUsername).sendKeys(ConfigurationFunctions.TESTLOGIN);
		    driver.findElement(PageSignInModal.ModalPassword).clear();
		    driver.findElement(PageSignInModal.ModalPassword).sendKeys(ConfigurationFunctions.TESTPWD);
		    driver.findElement(PageSignInModal.ModalSigninButton).click();
		    Thread.sleep(6000);
	    }
	    driver.get(ConfigurationFunctions.accessURL+pageURL);
	    Thread.sleep(2000);
	    URL obj = new URL(ConfigurationFunctions.accessURL+pageURL);
		URLConnection conn = obj.openConnection();
		conn = obj.openConnection();
		conn = obj.openConnection(); //HIT 
		conn = obj.openConnection(); //HIT 
		conn = obj.openConnection(); //HIT 
		//Map<String, List<String>> map = conn.getHeaderFields();
		String cacheCookie = driver.manage().getCookieNamed("cacheCookie").getValue();
		System.out.println("cache cookie: "+cacheCookie);
		String cacheControl = conn.getHeaderField("Cache-Control");
		String xcache = conn.getHeaderField("X-Cache");
		String xcachehits = conn.getHeaderField("X-Cache-Hits");
		String vary = conn.getHeaderField("Vary");
		String xserver = conn.getHeaderField("X-Served-By");
		System.out.println("header: "+pageURL+":"+cacheControl+"|"+xcache+"|"+xcachehits+"|"+vary+"|"+xserver);
		Assert.assertTrue(x_cache.equals(xcache)); //Actual=Expected
	}
		
}
